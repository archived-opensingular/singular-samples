/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.form.wicket.mapper;

import static br.net.mirante.singular.util.wicket.util.Shortcuts.$b;
import static br.net.mirante.singular.util.wicket.util.Shortcuts.$m;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import com.google.common.base.Strings;

import br.net.mirante.singular.commons.lambda.IFunction;
import br.net.mirante.singular.form.mform.SIComposite;
import br.net.mirante.singular.form.mform.SIList;
import br.net.mirante.singular.form.mform.SISimple;
import br.net.mirante.singular.form.mform.SInstance;
import br.net.mirante.singular.form.mform.SType;
import br.net.mirante.singular.form.mform.STypeComposite;
import br.net.mirante.singular.form.mform.STypeSimple;
import br.net.mirante.singular.form.mform.SingularFormException;
import br.net.mirante.singular.form.mform.basic.ui.SPackageBasic;
import br.net.mirante.singular.form.mform.basic.view.SView;
import br.net.mirante.singular.form.mform.basic.view.SViewBreadcrumb;
import br.net.mirante.singular.form.wicket.UIBuilderWicket;
import br.net.mirante.singular.form.wicket.WicketBuildContext;
import br.net.mirante.singular.form.wicket.enums.ViewMode;
import br.net.mirante.singular.form.wicket.mapper.components.MetronicPanel;
import br.net.mirante.singular.form.wicket.model.MTipoModel;
import br.net.mirante.singular.form.wicket.model.SInstanceCampoModel;
import br.net.mirante.singular.form.wicket.model.SInstanceItemListaModel;
import br.net.mirante.singular.util.wicket.ajax.ActionAjaxButton;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSContainer;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSGrid;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSRow;
import br.net.mirante.singular.util.wicket.bootstrap.layout.TemplatePanel;
import br.net.mirante.singular.util.wicket.datatable.BSDataTable;
import br.net.mirante.singular.util.wicket.datatable.BSDataTableBuilder;
import br.net.mirante.singular.util.wicket.datatable.BaseDataProvider;
import br.net.mirante.singular.util.wicket.datatable.column.BSActionPanel;
import br.net.mirante.singular.util.wicket.metronic.breadcrumb.MetronicBreadcrumbBar;
import br.net.mirante.singular.util.wicket.resource.Icone;
import br.net.mirante.singular.util.wicket.util.WicketUtils;

public class ListBreadcrumbMapper extends AbstractListaMapper {

    @Override
    public void buildView(WicketBuildContext ctx) {

        final IModel<? extends SInstance> model = ctx.getModel();
        final ViewMode viewMode = ctx.getViewMode();

        if (!(ctx.getView() instanceof SViewBreadcrumb)) {
            throw new SingularFormException("Error: Mapper " + ListBreadcrumbMapper.class.getSimpleName()
                    + " must be associated with a view  of type" + SViewBreadcrumb.class.getName() + ".", model.getObject());
        }

        final SViewBreadcrumb view = (SViewBreadcrumb) ctx.getView();

        final IModel<String> listaLabel = newLabelModel(ctx, model);

        BreadCrumbPanel breadcrumbPanel = ctx.getBreadCrumbPanelInTree();
        if (breadcrumbPanel == null) {
            breadcrumbPanel = new BreadCrumbPanel("panel", model, listaLabel, ctx, viewMode, view, ctx.getUiBuilderWicket());
            ctx.setBreadCrumbPanel(breadcrumbPanel);
        }

        ctx.getContainer().appendTag("div", true, null, breadcrumbPanel);
    }

    /*
     * DATA TABLE
     */

    /**
     * @param ctx
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
    private IModel<String> newLabelModel(WicketBuildContext ctx, IModel<? extends SInstance> model) {
        IModel<SIList<SInstance>> listaModel = $m.get(() -> (SIList<SInstance>) model.getObject());
        SIList<?> iLista = listaModel.getObject();
        IModel<String> labelModel = $m.ofValue(trimToEmpty(iLista.as(SPackageBasic.aspect()).getLabel()));
        ctx.configureContainer(labelModel);
        return labelModel;
    }

    public static class ColumnType {

        private SType<?> type;
        private String customLabel;
        private IFunction<SInstance, String> displayValueFunction = SInstance::toStringDisplay;

        public ColumnType() {
        }

        public ColumnType(SType<?> type, String customLabel, IFunction<SInstance, String> displayValueFunction) {
            this.type = type;
            this.customLabel = customLabel;
            if (displayValueFunction != null) {
                this.displayValueFunction = displayValueFunction;
            }
        }

        public ColumnType(SType<?> type, String customLabel) {
            this.type = type;
            this.customLabel = customLabel;
        }

        public SType<?> getType() {
            return type;
        }

        public String getCustomLabel() {
            return customLabel;
        }

        public IFunction<SInstance, String> getDisplayValueFunction() {
            return displayValueFunction;
        }
    }

    public static class BreadCrumbPanel extends MetronicPanel {

        private Deque<BreadCrumbStatus> breadcrumbs;
        private BreadCrumbStatus selectedBreadcrumb;

        private MetronicBreadcrumbBar breadCrumbBar;
        private String instanceBackupXml;

        @SuppressWarnings("unchecked")
        public BreadCrumbPanel(String id,
                               IModel<? extends SInstance> model,
                               IModel<String> listaLabel,
                               WicketBuildContext ctx,
                               ViewMode viewMode,
                               SViewBreadcrumb view,
                               UIBuilderWicket wicketBuilder) {
            super(id);

            this.breadcrumbs = new LinkedList<>();
            selectedBreadcrumb = new BreadCrumbStatus(
                    $m.get(() -> (SIList<SInstance>) model.getObject()),
                    listaLabel,
                    ctx,
                    viewMode,
                    view
            );
            breadcrumbs.push(selectedBreadcrumb);

        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

        }

        @Override
        protected void buildHeading(BSContainer<?> heading, Form<?> form) {
            heading.appendTag("span", new Label("_title", selectedBreadcrumb.listaLabel));
            heading.add($b.visibleIf($m.get(() -> !Strings.isNullOrEmpty(selectedBreadcrumb.listaLabel.getObject()))));
            if (selectedBreadcrumb.viewMode.isEdition() && selectedBreadcrumb.view.isNewEnabled()) {
                appendAddButton(heading, selectedBreadcrumb.ctx.getModel(), selectedBreadcrumb.ctx);
            }
        }

        protected void appendAddButton(BSContainer<?> container, IModel<? extends SInstance> m, WicketBuildContext ctx) {
            container
                    .newTemplateTag(t -> ""
                            + "<button"
                            + " wicket:id='_add'"
                            + " class='btn blue btn-sm pull-right'"
                            + " style='" + MapperCommons.BUTTON_STYLE + "'><i style='" + MapperCommons.ICON_STYLE + "' class='" + Icone.PLUS + "'></i>"
                            + "</button>")
                    .add(new AjaxLink<Void>("_add") {
                        @Override
                        public void onClick(AjaxRequestTarget target) {
                            final SInstance si = m.getObject();
                            if (si instanceof SIList) {
                                final SIList sil = (SIList) si;
                                if (sil.getType().getMaximumSize() != null && sil.getType().getMaximumSize() == sil.size()) {
                                    target.appendJavaScript(";bootbox.alert('A Quantidade máxima de valores foi atingida.');");
                                } else {
                                    SInstance sInstance = sil.addNew();
                                    IModel<? extends SInstance> itemModel = new SInstanceCampoModel<>(ctx.getRootContext().getModel(), sInstance.getPathFromRoot());
                                    showCrud(ctx, target, itemModel);
                                }
                            }
                        }
                    });
        }

        private void showCrud(WicketBuildContext ctx, AjaxRequestTarget target, IModel<? extends SInstance> itemModel) {
            final IModel<SIList<SInstance>> listaModel = $m.get(ctx::getCurrentInstance);
            final SView view = ctx.getView();

            MetronicPanel panel = ctx.getContainer().visitChildren(new IVisitor<Component, MetronicPanel>() {
                @Override
                public void component(Component object, IVisit<MetronicPanel> visit) {
                    if (object.getId().equalsIgnoreCase("panel")) {
                        visit.stop((MetronicPanel) object);
                    }
                }
            });

            List<String> breadcrumbs = breadCrumbBar.getBreadcrumbs();

            panel.replaceContent( (content, form) -> {
                breadcrumbs.add((String) itemModel.getObject().getType().getAttributeValue(SPackageBasic.ATR_LABEL.getNameFull()));
                content.newTagWithFactory("ul", true, "class='page-breadcrumb breadcrumb'", (id) -> buildBreadCrumbBar(id, breadcrumbs));

                TemplatePanel list = content.newTemplateTag(t -> ""
                        + "    <ul class='list-group'>"
                        + "      <li wicket:id='_e' class='list-group-item'>"
                        + "        <div wicket:id='_r'></div>"
                        + "      </li>"
                        + "    </ul>");
                list.add($b.onConfigure(c -> c.setVisible(!listaModel.getObject().isEmpty())));

                WebMarkupContainer crudPanel = new WebMarkupContainer("_e");
                list.add(crudPanel);
                final BSGrid grid = new BSGrid("_r");
                final BSRow row = grid.newRow();
                final ViewMode viewMode = ctx.getViewMode();

                WicketBuildContext childCtx = ctx.createChild(row.newCol(11), true, itemModel);
                ctx.getUiBuilderWicket().build(childCtx, viewMode);

                crudPanel.add(grid);
                content.add($b.attrAppender("style", "padding: 15px 15px 10px 15px", ";"));

                final BSRow buttonsRow = grid.newRow();
                appendButtons(ctx, buttonsRow.newCol(11));

            });

            target.add(panel.getForm());
        }

        @Override
        protected void buildFooter(BSContainer<?> footer, Form<?> form) {
            footer.setVisible(false);
        }

        @Override
        protected void buildContent(BSContainer<?> content, Form<?> form) {
            breadCrumbBar = content.newTagWithFactory("ul", true, "class='page-breadcrumb breadcrumb'", (id) -> buildBreadCrumbBar(id, Collections.singletonList(selectedBreadcrumb.listaLabel.getObject())));

            content.appendTag("table", true, null, (id) -> {
                BSDataTable<SInstance, ?> bsDataTable = buildTable(id, selectedBreadcrumb.listModel, selectedBreadcrumb.view, selectedBreadcrumb.ctx, selectedBreadcrumb.viewMode);
                bsDataTable.add(new Behavior() {
                    @Override
                    public void onConfigure(Component component) {
                        super.onConfigure(component);
                        if (selectedBreadcrumb.ctx.getCurrentInstance() instanceof SIList) {
                            component.setVisible(!((SIList<?>) selectedBreadcrumb.ctx.getCurrentInstance()).isEmpty());
                        }
                    }
                });
                return bsDataTable;
            });

        }

        BSDataTable<SInstance, ?> buildTable(String id, IModel<? extends SInstance> model, SViewBreadcrumb view, WicketBuildContext ctx, ViewMode viewMode) {

            BSDataTableBuilder<SInstance, ?, ?> builder = new BSDataTableBuilder<>(newDataProvider(model)).withNoRecordsToolbar();

            configureColumns(view.getColumns(), builder, model, ctx, viewMode, view);

            return builder.build(id);
        }

        @SuppressWarnings("unchecked")
        private BaseDataProvider<SInstance, ?> newDataProvider(final IModel<? extends SInstance> model) {
            return new BaseDataProvider<SInstance, Object>() {

                @Override
                public Iterator<SInstance> iterator(int first, int count, Object sortProperty, boolean ascending) {
                    return ((SIList<SInstance>) model.getObject()).iterator();
                }

                @Override
                public long size() {
                    return ((SIList<SInstance>) model.getObject()).size();
                }

                @Override
                public IModel<SInstance> model(SInstance object) {
                    IModel<SIList<SInstance>> listaModel = $m.get(() -> (SIList<SInstance>) model.getObject());
                    return new SInstanceItemListaModel<>(listaModel, listaModel.getObject().indexOf(object));
                }
            };
        }

        private void configureColumns(
                List<SViewBreadcrumb.Column> mapColumns,
                BSDataTableBuilder<SInstance, ?, ?> builder,
                IModel<? extends SInstance> model,
                WicketBuildContext ctx,
                ViewMode viewMode,
                SViewBreadcrumb view) {


            List<ColumnType> columnTypes = new ArrayList<>();

            if (mapColumns.isEmpty()) {
                SType<?> tipo = ((SIList<?>) model.getObject()).getElementsType();
                if (tipo instanceof STypeSimple) {
                    columnTypes.add(new ColumnType(tipo, null));
                }
                if (tipo instanceof STypeComposite) {
                    ((STypeComposite<?>) tipo)
                            .getFields()
                            .stream()
                            .filter(mtipo -> mtipo instanceof STypeSimple)
                            .forEach(mtipo -> columnTypes.add(new ColumnType(mtipo, null)));

                }
            } else {
                mapColumns.forEach((col) -> {
                    SType<?> type = model.getObject().getDictionary().getType(col.getTypeName());
                    ColumnType columnType = new ColumnType(type, col.getCustomLabel(), col.getDisplayValueFunction());
                    columnTypes.add(columnType);
                });
            }

            for (ColumnType columnType : columnTypes) {

                IModel<String> labelModel;
                String label = columnType.getCustomLabel();

                if (label != null) {
                    labelModel = $m.ofValue(label);
                } else {
                    labelModel = $m.ofValue((String) columnType.getType().getAttributeValue(SPackageBasic.ATR_LABEL.getNameFull()));
                }

                propertyColumnAppender(builder, labelModel, new MTipoModel(columnType.getType()), columnType.getDisplayValueFunction());
            }

            actionColumnAppender(builder, model, ctx, viewMode, view);

        }

        private void actionColumnAppender(BSDataTableBuilder<SInstance, ?, ?> builder,
                                          IModel<? extends SInstance> model,
                                          WicketBuildContext ctx,
                                          ViewMode viewMode,
                                          SViewBreadcrumb view) {

            builder.appendActionColumn($m.ofValue(""), actionColumn -> {
                if (viewMode.isEdition() && view.isDeleteEnabled()) {
                    actionColumn.appendAction(new BSActionPanel.ActionConfig<>()
                                    .iconeModel(Model.of(Icone.MINUS), Model.of(MapperCommons.ICON_STYLE))
                                    .buttonModel(Model.of("red"))
                                    .style($m.ofValue(MapperCommons.BUTTON_STYLE)),
                            (target, rowModel) -> {
                                SIList<?> sList = ((SIList<?>) model.getObject());
                                sList.remove(sList.indexOf(rowModel.getObject()));
                                target.add(ctx.getContainer());
                            });
                }
                final Icone openModalIcon = viewMode.isEdition() && view.isEditEnabled() ? Icone.PENCIL_SQUARE : Icone.EYE;
                actionColumn.appendAction(
                        new BSActionPanel.ActionConfig<>()
                                .iconeModel(Model.of(openModalIcon), Model.of(MapperCommons.ICON_STYLE))
                                .buttonModel(Model.of("blue-madison"))
                                .style($m.ofValue(MapperCommons.BUTTON_STYLE)),
                        (target, rowModel) -> {
                            showCrud(ctx, target, rowModel);
                        });
            });
        }

        private void hideCrud(WicketBuildContext ctx, AjaxRequestTarget target) {
            MetronicPanel panel = ctx.getContainer().visitChildren(new IVisitor<Component, MetronicPanel>() {
                @Override
                public void component(Component object, IVisit<MetronicPanel> visit) {
                    if (object.getId().equalsIgnoreCase("panel")) {
                        visit.stop((MetronicPanel) object);
                    }
                }
            });

            final List<String> breadcrumbs = breadCrumbBar.getBreadcrumbs();

            panel.replaceContent((content, form) -> {
                breadcrumbs.remove(breadcrumbs.size() - 1);
                content.newTagWithFactory("ul", true, "class='page-breadcrumb breadcrumb'", (id) -> buildBreadCrumbBar(id, breadcrumbs));

                content.appendTag("table", true, null, (id) -> {
                    BSDataTable<SInstance, ?> bsDataTable = buildTable(id, ctx.getModel(), (SViewBreadcrumb) ctx.getView(), ctx, ctx.getViewMode());
                    bsDataTable.add(new Behavior() {
                        @Override
                        public void onConfigure(Component component) {
                            super.onConfigure(component);
                            if (ctx.getCurrentInstance() instanceof SIList) {
                                component.setVisible(!((SIList<?>) ctx.getCurrentInstance()).isEmpty());
                            }
                        }
                    });
                    return bsDataTable;
                });
            });

            target.add(panel.getForm());
        }

        private MetronicBreadcrumbBar buildBreadCrumbBar(String id, List<String> oldBreadCrumbBar) {
            MetronicBreadcrumbBar newBreadCrumbBar = new MetronicBreadcrumbBar(id);
            oldBreadCrumbBar.forEach(newBreadCrumbBar::addBreadCrumb);

            return newBreadCrumbBar;
        }

        /**
         * property column isolado em outro método para isolar o escopo de
         * serialização do lambda do appendPropertyColumn
         */
        private void propertyColumnAppender(BSDataTableBuilder<SInstance, ?, ?> builder,
                                            IModel<String> labelModel, IModel<SType<?>> mTipoModel,
                                            IFunction<SInstance, String> displayValueFunction) {
            builder.appendPropertyColumn(labelModel, o -> {
                SIComposite composto = (SIComposite) o;
                STypeSimple<?, ?> mtipo = (STypeSimple<?, ?>) mTipoModel.getObject();
                SISimple<?> instancia = composto.findDescendant(mtipo).get();
                return displayValueFunction.apply(instancia);
            });
        }

        private void appendButtons(WicketBuildContext ctx, BSContainer<?> cell) {

            cell.add(WicketUtils.$b.attrAppender("class", "text-center", " "));

            cell
                    .newTemplateTag(t -> "" +
                            "<button wicket:id='okButton' class='btn btn-primary'>" +
                            "<wicket:container wicket:id='label'></wicket:container>" +
                            "</button> ")
                    .add(new ActionAjaxButton("okButton") {
                        @Override
                        protected void onAction(AjaxRequestTarget target, Form<?> form) {
                            hideCrud(ctx, target);
                        }
                    }.add(new Label("label", "OK")));


            cell
                    .newTemplateTag(t -> "" +
                            "<button wicket:id='cancelButton' class='btn'>" +
                            "<wicket:container wicket:id='label'></wicket:container>" +
                            "</button> ")
                    .add(new ActionAjaxButton("cancelButton") {
                        @Override
                        protected void onAction(AjaxRequestTarget target, Form<?> form) {
                            selectedBreadcrumb.listModel.getObject().remove(selectedBreadcrumb.listModel.getObject().size() - 1);
                            hideCrud(ctx, target);
                        }
                    }.add(new Label("label", "Cancelar")));

        }

        private static class BreadCrumbStatus {
            final IModel<SIList<SInstance>> listModel;
            final IModel<String> listaLabel;
            final WicketBuildContext ctx;
            final ViewMode viewMode;
            final SViewBreadcrumb view;

            public BreadCrumbStatus(IModel<SIList<SInstance>> listModel, IModel<String> listaLabel, WicketBuildContext ctx, ViewMode viewMode, SViewBreadcrumb view) {
                this.listModel = listModel;
                this.listaLabel = listaLabel;
                this.ctx = ctx;
                this.viewMode = viewMode;
                this.view = view;
            }
        }

    }

}