/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.lib.wicket.util.datatable.column;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.IExportableColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import org.opensingular.lib.commons.lambda.IFunction;
import org.opensingular.lib.wicket.util.util.WicketUtils;

public class BSPropertyColumn<T, S>
    extends BSAbstractColumn<T, S>
    implements IExportableColumn<T, S>, IRowMergeableColumn<T> {

    private final String propertyExpression;
    private final IFunction<T, Object> propertyFunction;

    public BSPropertyColumn(IModel<String> displayModel, S sortProperty, String propertyExpression) {
        this(displayModel, sortProperty, propertyExpression, null);
    }
    public BSPropertyColumn(IModel<String> displayModel, String propertyExpression) {
        this(displayModel, null, propertyExpression, null);
    }
    public BSPropertyColumn(IModel<String> displayModel, S sortProperty, IFunction<T, Object> propertyFunction) {
        this(displayModel, sortProperty, null, propertyFunction);
    }
    public BSPropertyColumn(IModel<String> displayModel, IFunction<T, Object> propertyFunction) {
        this(displayModel, null, null, propertyFunction);
    }
    private BSPropertyColumn(IModel<String> displayModel, S sortProperty, String propertyExpression, IFunction<T, Object> propertyFunction) {
        super(displayModel, sortProperty);
        this.propertyExpression = propertyExpression;
        this.propertyFunction = propertyFunction;
    }

    @Override
    public void populateItem(final Item<ICellPopulator<T>> item, final String componentId, final IModel<T> rowModel) {
        item.add(new Label(componentId, getDataModel(rowModel)));
    }

    public String getPropertyExpression() {
        return propertyExpression;
    }
    public IFunction<T, Object> getPropertyFunction() {
        return propertyFunction;
    }

    @Override
    public IModel<Object> getDataModel(IModel<T> rowModel) {
        if (getPropertyFunction() != null) {
            return WicketUtils.$m.get(() -> getPropertyFunction().apply(rowModel.getObject()));
        }
        return WicketUtils.$m.property(rowModel, getPropertyExpression());
    }
}
