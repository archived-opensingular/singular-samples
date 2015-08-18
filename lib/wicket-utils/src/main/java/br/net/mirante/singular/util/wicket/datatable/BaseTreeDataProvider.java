package br.net.mirante.singular.util.wicket.datatable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class BaseTreeDataProvider<T, S> extends SortableDataProvider<T, S> {

    public BaseTreeDataProvider() {}
    public BaseTreeDataProvider(S defaultSort) {
        super.setSort(defaultSort, SortOrder.ASCENDING);
    }

    public abstract List<? extends T> list(int first, int count, S sortProperty, boolean ascending);

    public BaseTreeDataProvider<T, S> setSortAsc(S property) {
        super.setSort(property, SortOrder.ASCENDING);
        return this;
    }
    public BaseTreeDataProvider<T, S> setSortDesc(S property) {
        super.setSort(property, SortOrder.DESCENDING);
        return this;
    }

    @Override
    public Iterator<? extends T> iterator(long first, long count) {
        SortParam<S> sort = getSort();
        return list((int) first, (int) count,
            (sort == null) ? null : sort.getProperty(),
            (sort == null) ? true : sort.isAscending())
            .iterator();
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public IModel<T> model(T object) {

//        if (object instanceof IEntity && ((IEntity) object).getId() != null) {
//            return new EntityModel((IEntity) object);
//        } else //
        if (object instanceof Serializable) {
            return new Model((Serializable) object);
        }

        throw new IllegalArgumentException("Model do objeto não pode ser resolvido: " + object);
    }

}
