/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.util.wicket.datatable.column;

import static org.opensingular.singular.util.wicket.util.WicketUtils.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import org.opensingular.singular.commons.lambda.IBiFunction;
import org.opensingular.singular.commons.lambda.IConsumer;
import org.opensingular.singular.commons.lambda.IFunction;
import org.opensingular.singular.util.wicket.datatable.IBSAction;
import org.opensingular.singular.util.wicket.datatable.column.BSActionPanel.ActionConfig;
import org.opensingular.singular.util.wicket.resource.Icone;

public class BSActionColumn<T, S> extends BSAbstractColumn<T, S> {

    private final List<ActionItem<T>> actions = new ArrayList<>();

    public BSActionColumn() {
        super($m.ofValue(""));
    }

    public BSActionColumn(IModel<String> displayModel) {
        super(displayModel);
    }

    @Override
    public String getCssClass() {
        return " action-column " + super.getCssClass();
    }

    @Override
    public final void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        BSActionPanel<T> actionPanel = newActionPanel(componentId, rowModel);
        cellItem.add(actionPanel);
        onPopulateActions(rowModel, actionPanel);
    }

    protected BSActionPanel<T> newActionPanel(String componentId, IModel<T> rowModel) {
        return new BSActionPanel<>(componentId, rowModel);
    }

    protected void onPopulateActions(IModel<T> rowModel, BSActionPanel<T> actionPanel) {
        for (ActionItem<T> item : actions) {
            if (item.actionConfig.showActionItemFor(rowModel)) {
                actionPanel.appendAction(item.actionConfig, item.action);
            }
        }
    }

    public final BSActionColumn<T, S> appendAction(IModel<?> labelModel, Icone icone, IBSAction<T> action) {
        return appendAction(labelModel, $m.ofValue(icone), action);
    }

    public final BSActionColumn<T, S> appendAction(IModel<?> labelModel, Icone icone, IBSAction<T> action, IFunction<IModel<T>, Boolean> visibleFunction) {
        return appendAction(labelModel, icone, action, visibleFunction, IConsumer.noop());
    }
    public final BSActionColumn<T, S> appendAction(IModel<?> labelModel, Icone icone, IBSAction<T> action, IFunction<IModel<T>, Boolean> visibleFunction, IConsumer<ActionConfig<T>> configCustomizer) {
        actions.add(new ActionItem<>(new ActionConfig<T>()
            .labelModel(labelModel)
            .iconeModel($m.ofValue(icone), null, $m.ofValue("fa-lg"))
            .visibleFor(visibleFunction)
            .configure(configCustomizer), action));
        return this;
    }

    public final BSActionColumn<T, S> appendAction(IModel<?> labelModel, IBSAction<T> action) {
        return appendAction(labelModel, (IModel<Icone>) null, action);
    }

    public final BSActionColumn<T, S> appendAction(IModel<?> labelModel, IModel<Icone> iconeModel, IBSAction<T> action) {
        actions.add(new ActionItem<>(new ActionConfig<T>()
            .labelModel(labelModel)
            .iconeModel(iconeModel, null, $m.ofValue("fa-lg")), action));
        return this;
    }

    public final BSActionColumn<T, S> appendAction(ActionConfig<T> config, IBSAction<T> action) {
        actions.add(new ActionItem<>(config, action));
        return this;
    }

    @Override
    public BSActionColumn<T, S> setRowMergeIdFunction(IFunction<T, ?> rowMergeIdFunction) {
        return (BSActionColumn<T, S>) super.setRowMergeIdFunction(rowMergeIdFunction);
    }

    public BSActionColumn<T, S> appendStaticAction(IModel<?> labelModel, Icone icone, IBiFunction<String, IModel<T>, MarkupContainer> linkFactory) {
        actions.add(new ActionItem<>(new ActionConfig<T>()
            .labelModel(labelModel)
            .iconeModel($m.ofValue(icone), null, $m.ofValue("fa-lg"))
            .linkFactory(linkFactory), null));
        return this;
    }

    public BSActionColumn<T, S> appendStaticAction(IModel<?> labelModel, Icone icone, IBiFunction<String, IModel<T>, MarkupContainer> linkFactory, IFunction<IModel<T>, Boolean> visibleFunction) {
        return appendStaticAction(labelModel, icone, linkFactory, visibleFunction, IConsumer.noop());
    }
    public BSActionColumn<T, S> appendStaticAction(IModel<?> labelModel, Icone icone, IBiFunction<String, IModel<T>, MarkupContainer> linkFactory, IFunction<IModel<T>, Boolean> visibleFunction, IConsumer<ActionConfig<T>> configCustomizer) {
        actions.add(new ActionItem<>(new ActionConfig<T>().labelModel(labelModel).iconeModel($m.ofValue(icone), null, $m.ofValue("fa-lg")).linkFactory(linkFactory).visibleFor(visibleFunction).configure(configCustomizer), null));
        return this;
    }

    public BSActionColumn<T, S> appendStaticActionWithDefaultIcon(IModel<?> labelModel, Icone icone, IBiFunction<String, IModel<T>, MarkupContainer> linkFactory) {
        actions.add(new ActionItem<>(new ActionConfig<T>().labelModel(labelModel).iconeModel($m.ofValue(icone)).linkFactory(linkFactory), null));
        return this;
    }

    private static class ActionItem<T> implements Serializable {
        final ActionConfig<T> actionConfig;
        final IBSAction<T>    action;

        public ActionItem(ActionConfig<T> actionConfig, IBSAction<T> action) {
            this.actionConfig = actionConfig;
            this.action = action;
        }

    }
}