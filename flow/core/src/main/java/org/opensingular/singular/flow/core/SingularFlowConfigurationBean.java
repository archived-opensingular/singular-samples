/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.flow.core;

import org.opensingular.singular.commons.base.SingularException;
import org.opensingular.singular.flow.core.defaults.NullViewLocator;
import org.opensingular.singular.flow.core.entity.IEntityProcessInstance;
import org.opensingular.singular.flow.core.renderer.IFlowRenderer;
import org.opensingular.singular.flow.core.renderer.YFilesFlowRenderer;
import org.opensingular.singular.flow.core.service.IPersistenceService;
import org.opensingular.singular.flow.core.service.IProcessDataService;
import org.opensingular.singular.flow.core.service.IProcessDefinitionEntityService;
import org.opensingular.singular.flow.core.service.IUserService;
import org.opensingular.singular.flow.core.view.IViewLocator;
import org.opensingular.singular.flow.schedule.IScheduleService;
import org.opensingular.singular.flow.schedule.ScheduleDataBuilder;
import org.opensingular.singular.flow.schedule.ScheduledJob;
import org.opensingular.singular.flow.schedule.quartz.QuartzScheduleService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class SingularFlowConfigurationBean {

    public static final String PREFIXO = "SGL";

    private String processGroupCod;
    
    private List<ProcessNotifier> notifiers = new ArrayList<>();

    /**
     * @param processGroupCod - chave do sistema cadastrado no em <code>TB_GRUPO_PROCESSO</code>
     */
    protected SingularFlowConfigurationBean(String processGroupCod) {
        super();
        this.processGroupCod = processGroupCod;
    }

    protected SingularFlowConfigurationBean() {
        super();
    }

    final void start() {
        for (final ProcessDefinition<?> processDefinition : getDefinitions()) {
            for (final MTaskJava task : processDefinition.getFlowMap().getJavaTasks()) {
                if (!task.isImmediateExecution()) {
                    getScheduleService().schedule(new ScheduledJob(task.getCompleteName(), task.getScheduleData(), () -> executeTask(task)));
                }
            }
            for (ProcessScheduledJob scheduledJob : processDefinition.getScheduledJobs()) {
                getScheduleService().schedule(scheduledJob);
            }
        }
        configureWaitingTasksJobSchedule();
        init();
    }

    protected void configureWaitingTasksJobSchedule() {
        getScheduleService().schedule(new ExecuteWaitingTasksJob(ScheduleDataBuilder.buildHourly(1)));
    }


    protected void init() {

    }

    public final void setProcessGroupCod(String processGroupCod) {
        this.processGroupCod = processGroupCod;
    }
    
    public final String getProcessGroupCod() {
//        Objects.requireNonNull(processGroupCod);
        if (processGroupCod == null) {
            throw new SingularFlowException("Não foi definido o ProcessGroupCod");
        }
        return processGroupCod;
    }
    
    // ------- Método de recuperação de definições --------------------

    protected ProcessDefinitionCache getDefinitionCache() {
        return ProcessDefinitionCache.get(getDefinitionsPackages());
    }

    protected abstract String[] getDefinitionsPackages();

    public <K extends ProcessDefinition<?>> K getProcessDefinition(Class<K> processClass) {
        return ProcessDefinitionCache.getDefinition(processClass);
    }

    /**
     * @throws SingularFlowException <code> if there is no ProcessDefinition associated with key</code>
     */
    protected ProcessDefinition<?> getProcessDefinition(String key) {
        return getDefinitionCache().getDefinition(key);
    }

    /**
     * <code> this method does not throw a exception if there is no ProcessDefinition associated with key</code>
     * @param key
     * @return
     */
    protected ProcessDefinition<?> getProcessDefinitionUnchecked(String key) {
        return getDefinitionCache().getDefinitionUnchecked(key);
    }

    protected List<ProcessDefinition<?>> getDefinitions() {
        return getDefinitionCache().getDefinitions();
    }

    // ------- Método de recuperação de instâncias --------------------

    private ProcessInstance getProcessInstanceByEntityCod(Integer cod) {
        IEntityProcessInstance dadosInstanciaProcesso = getPersistenceService().retrieveProcessInstanceByCod(cod);
        ProcessDefinition<?> def = getProcessDefinition(dadosInstanciaProcesso.getProcessVersion().getAbbreviation());
        return def.convertToProcessInstance(dadosInstanciaProcesso);
    }

    protected ProcessInstance getProcessInstance(IEntityProcessInstance entityProcessInstance) {
        return getProcessInstanceByEntityCod(entityProcessInstance.getCod());
    }

    protected final <X extends ProcessInstance, T extends ProcessDefinition<X>> X getProcessInstance(Class<T> instanceClass, IEntityProcessInstance entityProcessInstance) {
        return (X) getProcessInstanceByEntityCod(entityProcessInstance.getCod());
    }

    protected final <X extends ProcessInstance, T extends ProcessDefinition<X>> X getProcessInstance(Class<T> processClass, Integer cod) {
        return getProcessDefinition(processClass).getDataService().retrieveInstance(cod);
    }

    protected final <X extends ProcessInstance, T extends ProcessDefinition<X>> X getProcessInstanceOrException(Class<T> processClass, String id) {
        X instance = getProcessInstance(processClass, id);
        if (instance == null) {
            throw new SingularFlowException("Não foi encontrada a instancia '" + id + "' do tipo " + processClass.getName());
        }
        return instance;
    }

    protected final <X extends ProcessInstance, T extends ProcessDefinition<X>> X getProcessInstance(Class<T> processClass, String id) {
        if (StringUtils.isNumeric(id)) {
            return getProcessInstance(processClass, Integer.parseInt(id));
        } else {
            return (X) getProcessInstance(id);
        }
    }

    protected ProcessInstance getProcessInstance(String processInstanceID) {
        if (processInstanceID == null) {
            return null;
        }
        MappingId mapeamento = parseId(processInstanceID);
        if (mapeamento.abbreviation == null) {
            return getProcessInstanceByEntityCod(mapeamento.cod);
        } else {
            final ProcessDefinition<?> def = getProcessDefinition(mapeamento.abbreviation);
            if (def == null) {
                throw new SingularFlowException("Não existe definição de processo '" + mapeamento.abbreviation + "'");
            }
            return def.getDataService().retrieveInstance(mapeamento.cod);
        }
    }

    protected ProcessInstance getProcessInstanceOrException(String processInstanceID) {
        ProcessInstance instance = getProcessInstance(processInstanceID);
        if (instance == null) {
            throw new SingularFlowException("Não foi encontrada a instancia '" + processInstanceID + "'");
        }
        return instance;
    }

    // ------- Manipulação de ID --------------------------------------

    // TODO rever generateID e parseId, deveria ser tipado, talvez nem devesse
    // estar nesse lugar
    protected String generateID(ProcessInstance instancia) {
        return new StringBuilder(50)
            .append(PREFIXO)
            .append('.')
            .append(instancia.getProcessDefinition().getKey())
            .append('.')
            .append(instancia.getId()).toString();
    }

    // TODO rever generateID e parseId, deveria ser tipado, talvez nem devesse
    // estar nesse lugar

    protected String generateID(TaskInstance instanciaTarefa) {
        ProcessInstance instanciaProcesso = instanciaTarefa.getProcessInstance();
        return new StringBuilder(generateID(instanciaProcesso))
            .append('.')
            .append(instanciaTarefa.getId())
            .toString();
    }

    // TODO rever generateID e parseId, deveria ser tipado, talvez nem devesse
    // estar nesse lugar
    protected MappingId parseId(String instanciaID) {
        if (instanciaID == null || instanciaID.length() < 1) {
            throw new SingularException("O ID da instância não pode ser nulo ou vazio");
        }
        String parts[] = instanciaID.split("\\.");
        String sigla = parts[parts.length - 2];
        String id = parts[parts.length - 1];
        return new MappingId(sigla, Integer.parseInt(id));
    }

    // TODO rever generateID e parseId, deveria ser tipado, talvez nem devesse
    // estar nesse lugar
    protected static class MappingId {
        public final String abbreviation;
        public final Integer cod;

        public MappingId(String abbreviation, int cod) {
            this.abbreviation = abbreviation;
            this.cod = cod;
        }
    }

    // ------- Geração de link ----------------------------------------
    protected IViewLocator getViewLocator() {
        return new NullViewLocator();
    }

    // ------- Manipulação de Usuário ---------------------------------
    public abstract IUserService getUserService();

    /**
     * Notifica os listeners registrados sobre um evento.
     * 
     * @param operation
     */
    public void notifyListeners(Consumer<ProcessNotifier> operation) {
        for (ProcessNotifier n : notifiers) {
            operation.accept(n);
        }
    }

    /**
     * Registra um listener para receber notificações do Engine
     * 
     * @param p
     */
    public void addListener(ProcessNotifier p) {
        notifiers.add(p);
    }

    // ------- Consultas ----------------------------------------------

    public final List<? extends ProcessDefinition<?>> getEnabledProcessForCreationBy(MUser user) {
        return getDefinitions().stream().filter(d -> d.canBeCreatedBy(user)).sorted().collect(Collectors.toList());
    }

    // ------- Outros -------------------------------------------------

    public IFlowRenderer getFlowRenderer() {
        return YFilesFlowRenderer.getInstance();
    }

    protected abstract IPersistenceService<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> getPersistenceService();

    protected abstract IProcessDefinitionEntityService<?, ?, ?, ?, ?, ?, ?, ?> getProcessEntityService();

    protected IScheduleService getScheduleService() {
        return new QuartzScheduleService();
    }

    public final Object executeTask(MTaskJava task) {
        final IProcessDataService<?> dataService = task.getFlowMap().getProcessDefinition().getDataService();
        final Collection<? extends ProcessInstance> instancias = dataService.retrieveAllInstancesIn(task);
        if (task.isCalledInBlock()) {
            return task.executarByBloco(instancias);
        } else {
            for (final ProcessInstance instanciaProcessoMBPM : instancias) {
                FlowEngine.executeScheduledTransition(task, instanciaProcessoMBPM);
            }
            return null;
        }
    }
}