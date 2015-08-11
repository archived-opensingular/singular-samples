package br.net.mirante.singular.flow.core;

import java.util.List;
import java.util.Objects;

import br.net.mirante.singular.flow.util.props.PropRef;
import br.net.mirante.singular.flow.util.vars.VarInstance;
import br.net.mirante.singular.flow.util.vars.VarInstanceMapImpl;
import br.net.mirante.singular.flow.core.entity.IEntityProcessInstance;
import br.net.mirante.singular.flow.core.entity.IEntityVariableInstance;

public class VarInstanceTableProcess extends VarInstanceMapImpl {

    private static final PropRef<Integer> PROP_DB_COD = new PropRef<>("persitence.dbCod", Integer.class);

    // TODO transformar o valor abaixo em RefProcessInstance (igual a
    // RefProcessDefinition)
    private ProcessInstance instancia;

    VarInstanceTableProcess(ProcessDefinition<?> definition) {
        super(definition.getVariaveis());
    }

    VarInstanceTableProcess(ProcessInstance instancia) {
        this(instancia.getDefinicao());
        bind(instancia.getEntity());
        this.instancia = instancia;
    }

    private void bind(IEntityProcessInstance iModelProcessInstance) {
        List<? extends IEntityVariableInstance> variaveis_ = iModelProcessInstance.getVariaveis();
        if (variaveis_ != null) {
            for (IEntityVariableInstance dadosVariavel : variaveis_) {
                VarInstance v = getVariavel(dadosVariavel.getNome());
                if (v == null) {
                    v = addDefinicao(getVarService().newDefinitionString(dadosVariavel.getNome(), dadosVariavel.getNome(), null));
                }
                v.setValor(dadosVariavel.getTextoValor());
                v.props().set(PROP_DB_COD, dadosVariavel.getCod());
            }
        }
    }

    boolean isBinded() {
        return instancia != null;
    }

    @Override
    protected boolean wantToKnowAboutChanges() {
        return true;
    }

    @Override
    public void onValueChanged(VarInstance changedVar) {
        if (isBinded()) {
            Integer dbCod = changedVar.props().get(PROP_DB_COD);
            Integer dbCod2 = instancia.getPersistenceService().updateVariableValue(instancia, changedVar, dbCod);
            if (!Objects.equals(dbCod, dbCod2)) {
                changedVar.props().set(PROP_DB_COD, dbCod2);
            }
        }
    }
}
