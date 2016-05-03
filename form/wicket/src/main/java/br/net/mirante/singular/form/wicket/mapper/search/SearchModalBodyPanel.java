package br.net.mirante.singular.form.wicket.mapper.search;

import br.net.mirante.singular.commons.lambda.IConsumer;
import br.net.mirante.singular.form.mform.*;
import br.net.mirante.singular.form.mform.basic.view.SViewSearchModal;
import br.net.mirante.singular.form.mform.context.SFormConfig;
import br.net.mirante.singular.form.mform.converter.SInstanceConverter;
import br.net.mirante.singular.form.mform.converter.SimpleSInstanceConverter;
import br.net.mirante.singular.form.mform.document.RefType;
import br.net.mirante.singular.form.mform.provider.filter.FilterDefinition;
import br.net.mirante.singular.form.mform.provider.filter.FilterDefinition.Column;
import br.net.mirante.singular.form.mform.provider.filter.FilterDefinitionBuilder;
import br.net.mirante.singular.form.wicket.WicketBuildContext;
import br.net.mirante.singular.form.wicket.panel.SingularFormPanel;
import br.net.mirante.singular.util.wicket.datatable.BSDataTableBuilder;
import br.net.mirante.singular.util.wicket.datatable.BaseDataProvider;
import br.net.mirante.singular.util.wicket.datatable.IBSAction;
import br.net.mirante.singular.util.wicket.datatable.column.BSActionPanel;
import br.net.mirante.singular.util.wicket.resource.Icone;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Iterator;

@SuppressWarnings("unchecked")
class SearchModalBodyPanel extends Panel {

    public static final String FILTER_BUTTON_ID = "filterButton";
    public static final String FORM_PANEL_ID    = "formPanel";
    public static final String RESULT_TABLE_ID  = "resultTable";

    private final WicketBuildContext           ctx;
    private final SViewSearchModal             view;
    private final IConsumer<AjaxRequestTarget> selectCallback;

    private SingularFormPanel innerSingularFormPanel;
    private MarkupContainer   resultTable;

    SearchModalBodyPanel(String id, WicketBuildContext ctx, IConsumer<AjaxRequestTarget> selectCallback) {
        super(id);
        this.ctx = ctx;
        this.view = (SViewSearchModal) ctx.getView();
        this.selectCallback = selectCallback;
        validate();
    }

    private void validate() {
        if (getInstance().asAtrProvider().getFilteredPagedProvider() == null) {
            throw new SingularFormException("O provider não foi informado");
        }
        if (getInstance().asAtrProvider().getConverter() == null
                && (getInstance() instanceof SIComposite || getInstance() instanceof SIList)) {
            throw new SingularFormException("O tipo não é simples e o converter não foi informado.");
        }
    }

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        final FilterDefinitionBuilder definitionBuilder = new FilterDefinitionBuilder();
        getInstance().asAtrProvider().getFilteredPagedProvider().defineFilter(definitionBuilder);
        final FilterDefinition filterDefinition = definitionBuilder.build();

        add(innerSingularFormPanel = buildInnerSingularFormPanel(filterDefinition));
        add(buildFilterButton());
        add(resultTable = buildResultTable(filterDefinition));
    }

    private AjaxButton buildFilterButton() {
        return new AjaxButton(FILTER_BUTTON_ID) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                target.add(resultTable);
            }
        };
    }

    private WebMarkupContainer buildResultTable(FilterDefinition filterDefinition) {

        final BSDataTableBuilder<Object, ?, ?> builder = new BSDataTableBuilder(new BaseDataProvider() {
            @Override
            public long size() {
                return getInstance().asAtrProvider().getFilteredPagedProvider()
                        .getSize(ctx.getRootContext().getCurrentInstance(), (SInstance) innerSingularFormPanel.getRootInstance().getObject());
            }

            @Override
            public Iterator iterator(int first, int count, Object sortProperty, boolean ascending) {
                return getInstance().asAtrProvider().getFilteredPagedProvider()
                        .load(ctx.getRootContext().getCurrentInstance(), (SInstance) innerSingularFormPanel.getRootInstance().getObject(), first, count).iterator();
            }
        });

        builder.setRowsPerPage(view.getPageSize());

        for (Object o : filterDefinition.getColumns()) {
            final Column column = (Column) o;
            builder.appendPropertyColumn(Model.of(column.getLabel()), object -> {
                try {
                    if (column.getProperty() != null) {
                        final Method getter = object.getClass().getMethod("get" + WordUtils.capitalize(column.getProperty()));
                        getter.setAccessible(true);
                        return getter.invoke(object);
                    } else {
                        return object;
                    }
                } catch (Exception ex) {
                    throw new SingularFormException("Não foi possivel recuperar a propriedade '" + column.getProperty() + "' via metodo get na classe " + object.getClass());
                }
            });
        }

        builder.appendActionColumn(Model.of(), (actionColumn) -> actionColumn
                .appendAction(new BSActionPanel.ActionConfig<>().iconeModel(Model.of(Icone.HAND_UP)).title(Model.of("Filtrar")),
                        (IBSAction<Object>) (target, model) ->
                        {
                            SInstanceConverter converter = getInstance().asAtrProvider().getConverter();
                            if (converter == null && !(getInstance() instanceof SIComposite || getInstance() instanceof SIList)) {
                                converter = new SimpleSInstanceConverter<>();
                            }
                            if (converter != null) {
                                converter.fillInstance(getInstance(), (Serializable) model.getObject());
                            }
                            selectCallback.accept(target);
                        })
        );

        return builder.build(RESULT_TABLE_ID);
    }

    private SingularFormPanel buildInnerSingularFormPanel(FilterDefinition filterDefinition) {

        final SingularFormPanel parentSingularFormPanel = this.visitParents(SingularFormPanel.class, new IVisitor<SingularFormPanel, SingularFormPanel>() {
            @Override
            public void component(SingularFormPanel parent, IVisit<SingularFormPanel> visit) {
                visit.stop(parent);
            }
        });

        return new SingularFormPanel(FORM_PANEL_ID, parentSingularFormPanel.getSingularFormConfig()) {
            @Override
            protected SInstance createInstance(SFormConfig singularFormConfig) {
                RefType filterRefType = new RefType() {
                    @Override
                    protected SType<?> retrieve() {
                        final STypeComposite<SIComposite> filter = SDictionary.create()
                                .createNewPackage("filterPackage")
                                .createCompositeType("filter");
                        filterDefinition.getFilterBuilder().accept(filter);
                        return filter;
                    }
                };
                return singularFormConfig.getDocumentFactory().createInstance(filterRefType);
            }
        };
    }

    private SInstance getInstance() {
        return ctx.getModel().getObject();
    }

}