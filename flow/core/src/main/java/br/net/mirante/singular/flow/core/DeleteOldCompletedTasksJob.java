package br.net.mirante.singular.flow.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;

import br.net.mirante.singular.flow.core.entity.IEntityTaskDefinition;
import br.net.mirante.singular.flow.core.entity.IEntityTaskDefinition;
import br.net.mirante.singular.flow.schedule.IScheduleData;
import br.net.mirante.singular.flow.schedule.IScheduledJob;

public class DeleteOldCompletedTasksJob implements IScheduledJob {

    private final IScheduleData scheduleData;

    public DeleteOldCompletedTasksJob(IScheduleData scheduleData) {
        super();
        this.scheduleData = scheduleData;
    }

    @Override
    public String getId() {
        return "DeleteOldCompletedTasks";
    }

    @Override
    public IScheduleData getScheduleData() {
        return scheduleData;
    }

    @Override
    public Object run() {

        final AbstractMbpmBean mbpmBean = MBPM.getMbpmBean();
        final List<String> resultados = new ArrayList<>();

        for (final ProcessDefinition<?> definicao : mbpmBean.getDefinicoes()) {
            if (definicao.getFluxo().getCleanupStrategy() != null) {
                final FlowMap flow = definicao.getFluxo();
                final List<IEntityTaskDefinition> situacoes = flow.getEndTasks().stream().map(definicao::obterSituacaoPara).collect(Collectors.toList());

                final int qtd = definicao.getPersistenceService().apagarInstanciasProcesso(situacoes, flow.getCleanupStrategy().getTime(), flow.getCleanupStrategy().getTimeUnit());
                resultados.add(definicao.getSigla() + ": deleted " + qtd);
            }
        }
        return Joiner.on("\n").join(resultados);
    }

}