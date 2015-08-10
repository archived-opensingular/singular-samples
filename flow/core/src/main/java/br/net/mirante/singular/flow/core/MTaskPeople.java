package br.net.mirante.singular.flow.core;

import com.google.common.base.Preconditions;

public class MTaskPeople extends MTaskExecutavel<MTaskPeople> {

    private EstrategiaAlertaTarefa estrategiaAlerta;

    private boolean podeRealocar = true;

    public MTaskPeople(FlowMap mapa, String nome) {
        super(mapa, nome);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.People;
    }

    @Override
    public boolean canReallocate() {
        return podeRealocar;
    }

    public MTaskPeople setPodeRealocar(boolean podeRealocar) {
        this.podeRealocar = podeRealocar;
        return this;
    }

    public MTaskPeople setAlerta(EstrategiaAlertaTarefa estrategiaAlerta) {
        this.estrategiaAlerta = estrategiaAlerta;
        return this;
    }

    public EstrategiaAlertaTarefa getAlerta() {
        return estrategiaAlerta;
    }

    @Override
    void verifyConsistency() {
        super.verifyConsistency();
        Preconditions.checkNotNull(getExecutionPage(), "Não foi definida a estratégia da página para execução da tarefa.");
        Preconditions.checkNotNull(getAccessStrategy(), "Não foi definida a estrategia de verificação de acesso da tarefa");
    }
}