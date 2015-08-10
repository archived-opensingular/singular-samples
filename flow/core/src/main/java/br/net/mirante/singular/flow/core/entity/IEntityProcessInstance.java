package br.net.mirante.singular.flow.core.entity;

import java.util.Date;
import java.util.List;

import br.net.mirante.singular.flow.core.MUser;

public interface IEntityProcessInstance extends IEntityByCod {

    String getDescricao();

    void setDescricao(String descricao);

    MUser getPessoaCriadora();

    void setPessoaCriadora(MUser pessoaCriadora);

    Date getDataInicio();

    void setDataInicio(Date dataInicio);

    Date getDataFim();

    void setDataFim(Date dataFim);

    Date getDataSituacaoAtual();

    void setDataSituacaoAtual(Date dataSituacaoAtual);

    IEntityTaskInstance getTarefaPai();

    List<? extends IEntityVariableInstance> getVariaveis();

    List<? extends IEntityVariable> getHistoricoVariaveis();

    List<? extends IEntityRole> getPapeis();

    IEntityProcessInstance getDemandaPai();

    IEntityTaskDefinition getSituacao();

    List<? extends IEntityTaskInstance> getTarefas();

    IEntityProcess getDefinicao();

    List<? extends IEntityProcessInstance> getDemandasFilhas();

    default IEntityRole getPapelDemandaComSigla(String siglaPapel) {
        for (IEntityRole dadosPapelInstancia : getPapeis()) {
            if (siglaPapel.equalsIgnoreCase(dadosPapelInstancia.getPapel().getSigla())) {
                return dadosPapelInstancia;
            }
        }
        return null;
    }

    default IEntityTaskInstance getTarefa(String siglaSituacao) {
        return getTarefa(getDefinicao().getSituacao(siglaSituacao));
    }

    default IEntityTaskInstance getTarefa(IEntityTaskDefinition situacaoAlvo) {
        List<? extends IEntityTaskInstance> tarefas = getTarefas();
        for (int i = tarefas.size() - 1; i > -1; i--) {
            if (tarefas.get(i).getSituacao().equals(situacaoAlvo)) {
                return tarefas.get(i);
            }
        }
        return null;
    }

    default IEntityTaskInstance getTarefaAtiva(IEntityTaskDefinition situacaoAlvo) {
        List<? extends IEntityTaskInstance> lista = getTarefas();
        for (int i = lista.size() - 1; i != -1; i--) {
            IEntityTaskInstance tarefa = lista.get(i);
            if (tarefa.getDataFim() == null && tarefa.getSituacao().equals(situacaoAlvo)) {
                return tarefa;
            }
        }
        return null;
    }

    default IEntityTaskInstance getTarefaAtiva() {
        List<? extends IEntityTaskInstance> lista = getTarefas();
        for (int i = lista.size() - 1; i != -1; i--) {
            IEntityTaskInstance tarefa = lista.get(i);
            if (tarefa.getDataFim() == null) {
                return tarefa;
            }
        }
        return null;
    }
}