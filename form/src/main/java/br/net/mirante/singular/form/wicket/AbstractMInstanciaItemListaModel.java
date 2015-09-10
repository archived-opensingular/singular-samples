package br.net.mirante.singular.form.wicket;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IChainingModel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

import br.net.mirante.singular.form.mform.MILista;
import br.net.mirante.singular.form.mform.MInstancia;

public abstract class AbstractMInstanciaItemListaModel<I extends MInstancia>
    extends AbstractReadOnlyModel<I>
    implements IChainingModel<I> {

    private Object rootTarget;

    public AbstractMInstanciaItemListaModel(Object rootTarget) {
        this.rootTarget = rootTarget;
    }

    public int getIndex() {
        return index();
    }

    protected abstract int index();

    @Override
    @SuppressWarnings("unchecked")
    public I getObject() {
        MILista<I> iLista = getRootTarget();
        if (getIndex() >= iLista.size())
            return null;
        return (I) iLista.get(getIndex());
    }

    @SuppressWarnings("unchecked")
    public MILista<I> getRootTarget() {
        return (MILista<I>) ((rootTarget instanceof IModel<?>)
            ? ((IModel<?>) rootTarget).getObject()
            : rootTarget);
    }

    @Override
    public void detach() {
        if (rootTarget instanceof IDetachable) {
            ((IDetachable) rootTarget).detach();
        }
    }

    @Override
    public void setChainedModel(IModel<?> rootModel) {
        this.rootTarget = rootModel;
    }
    @Override
    public IModel<?> getChainedModel() {
        return (rootTarget instanceof IModel) ? (IModel<?>) rootTarget : null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rootTarget == null) ? 0 : rootTarget.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractMInstanciaItemListaModel<?> other = (AbstractMInstanciaItemListaModel<?>) obj;
        if (rootTarget == null) {
            if (other.rootTarget != null)
                return false;
        } else if (!rootTarget.equals(other.rootTarget))
            return false;
        return true;
    }
}