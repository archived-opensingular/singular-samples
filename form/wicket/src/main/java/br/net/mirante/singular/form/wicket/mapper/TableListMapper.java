/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.form.wicket.mapper;

import static br.net.mirante.singular.form.wicket.mapper.components.MetronicPanel.*;
import static br.net.mirante.singular.util.wicket.util.Shortcuts.*;

import java.util.Set;

import org.apache.wicket.ClassAttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

import com.google.common.base.Strings;

import br.net.mirante.singular.form.SIComposite;
import br.net.mirante.singular.form.SIList;
import br.net.mirante.singular.form.SInstance;
import br.net.mirante.singular.form.SType;
import br.net.mirante.singular.form.STypeComposite;
import br.net.mirante.singular.form.SingularFormException;
import br.net.mirante.singular.form.type.basic.SPackageBasic;
import br.net.mirante.singular.form.view.SView;
import br.net.mirante.singular.form.view.SViewListByTable;
import br.net.mirante.singular.form.wicket.ISValidationFeedbackHandlerListener;
import br.net.mirante.singular.form.wicket.SValidationFeedbackHandler;
import br.net.mirante.singular.form.wicket.UIBuilderWicket;
import br.net.mirante.singular.form.wicket.WicketBuildContext;
import br.net.mirante.singular.form.wicket.enums.ViewMode;
import br.net.mirante.singular.form.wicket.mapper.components.MetronicPanel;
import br.net.mirante.singular.form.wicket.model.SInstanceCampoModel;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSContainer;
import br.net.mirante.singular.util.wicket.bootstrap.layout.IBSGridCol.BSGridSize;
import br.net.mirante.singular.util.wicket.bootstrap.layout.TemplatePanel;
import br.net.mirante.singular.util.wicket.bootstrap.layout.table.BSTDataCell;
import br.net.mirante.singular.util.wicket.bootstrap.layout.table.BSTRow;
import br.net.mirante.singular.util.wicket.bootstrap.layout.table.BSTSection;

public class TableListMapper extends AbstractListaMapper {

    @Override
    public void buildView(WicketBuildContext ctx) {

        if (!(ctx.getView() instanceof SViewListByTable)) {
            throw new SingularFormException("TableListMapper deve ser utilizado com MTableListaView", ctx.getCurrentInstance());
        }

        if (!(ctx.getCurrentInstance() instanceof SIList)) {
            return;
        }

        ctx.setHint(ControlsFieldComponentAbstractMapper.NO_DECORATION, true);
        ctx.getContainer().appendComponent((String id) -> buildPannel(ctx, id));
    }

    private MetronicPanel buildPannel(WicketBuildContext ctx, String id){
        final IModel<SIList<SInstance>> list = $m.get(ctx::getCurrentInstance);
        final SViewListByTable view = (SViewListByTable) ctx.getView();
        final Boolean isEdition = ctx.getViewMode() == null || ctx.getViewMode().isEdition();
        final SIList<SInstance> iLista = list.getObject();
        final SType<?> currentType = ctx.getCurrentInstance().getType();

        addMinimumSize(currentType, iLista);

        MetronicPanel panel = MetronicPanelBuilder.build(id,
                (h, form) -> buildHeader(h, form, list, ctx, view, isEdition),
                (c, form) -> builContent(c, form, list, ctx, view, isEdition),
                (f, form) -> f.setVisible(false));
        return panel;
    }

    private void buildHeader(BSContainer<?> header, Form<?> form, IModel<SIList<SInstance>> list,
                             WicketBuildContext ctx, SViewListByTable view, boolean isEdition) {

        final IModel<String> label = $m.ofValue(ctx.getCurrentInstance().getType().asAtr().getLabel());
        final Label title = new Label("_title", label);

        ctx.configureContainer(label);
        header.appendTag("span", title);
        header.add($b.visibleIf($m.get(() -> !Strings.isNullOrEmpty(label.getObject()))));

        if (view.isNewEnabled() && isEdition) {
            appendAddButton(list, form, header, false);
        }

        final SType<SInstance> elementsType = list.getObject().getElementsType();

        if (!(elementsType instanceof STypeComposite) && elementsType.getAttributeValue(SPackageBasic.ATR_REQUIRED)) {
            title.add($b.classAppender("singular-form-required"));
        }

    }

    private void builContent(BSContainer<?> content, Form<?> form, IModel<SIList<SInstance>> list,
                             WicketBuildContext ctx, SViewListByTable view, boolean isEdition) {

        final String markup = ""
            + " <table class='table table-condensed table-unstyled' style='margin-bottom:0px'>                   "
            + "      <thead wicket:id='_h'></thead>                                                              "
            + "      <tbody><wicket:container wicket:id='_e'><tr wicket:id='_r'></tr></wicket:container></tbody> "
            + "      <tfoot wicket:id='_ft'>                                                                     "
            + "          <tr><td colspan='99' wicket:id='_fb'></td></tr>                                         "
            + "      </tfoot>                                                                                    "
            + " </table>" +
            "                                                                                         ";
        final TemplatePanel template = content.newTemplateTag(tp -> markup);
        final BSTSection tableHeader = new BSTSection("_h").setTagName("thead");
        final ElementsView tableRows = new TableElementsView("_e", list, ctx, form);
        final WebMarkupContainer tableFooter = new WebMarkupContainer("_ft");
        final BSContainer<?> footerBody = new BSContainer<>("_fb");
        final SType<SInstance> elementsType = list.getObject().getElementsType();

        template.add($b.onConfigure(c -> c.setVisible(!list.getObject().isEmpty())));
        content.add($b.attrAppender("style", "padding: 15px 15px 10px 15px", ";"));

        if (elementsType instanceof STypeComposite) {

            final STypeComposite<?> compositeElementsType = (STypeComposite<?>) elementsType;
            final BSTRow row = tableHeader.newRow();

            if (view.isInsertEnabled()) {
                row.newTHeaderCell($m.ofValue(""));
            }

            int sumWidthPref = compositeElementsType.getFields().stream().mapToInt((x) -> x.asAtrBootstrap().getColPreference(1)).sum();

            for (SType<?> tCampo : compositeElementsType.getFields()) {

                final Integer preferentialWidth = tCampo.asAtrBootstrap().getColPreference(1);
                final IModel<String> headerModel = $m.ofValue(tCampo.as(SPackageBasic.aspect()).getLabel());
                final BSTDataCell cell = row.newTHeaderCell(headerModel);
                final String width = String.format("width:%.0f%%;", (100.0 * preferentialWidth) / sumWidthPref);
                final boolean isCampoObrigatorio = tCampo.as(SPackageBasic.aspect()).isRequired();

                ctx.configureContainer(headerModel);

                cell.setInnerStyle(width);
                cell.add(new ClassAttributeModifier() {
                    @Override
                    protected Set<String> update(Set<String> oldClasses) {
                        if (isCampoObrigatorio && isEdition) {
                            oldClasses.add("singular-form-required");
                        } else {
                            oldClasses.remove("singular-form-required");
                        }
                        return oldClasses;
                    }
                });
            }
        }

        tableFooter.setVisible(!(view.isNewEnabled() && isEdition));

        template.add(tableHeader)
            .add(tableRows)
            .add(tableFooter.add(footerBody));

        content.getParent().add(dependsOnModifier(list));
    }

    private static final class TableElementsView extends ElementsView {

        private final WicketBuildContext ctx;
        private final SView              view;
        private final Form<?>            form;
        private final ViewMode           viewMode;
        private final UIBuilderWicket    wicketBuilder;

        private TableElementsView(String id, IModel<SIList<SInstance>> model, WicketBuildContext ctx, Form<?> form) {
            super(id, model);
            this.wicketBuilder = ctx.getUiBuilderWicket();
            this.ctx = ctx;
            this.view = ctx.getView();
            this.form = form;
            this.viewMode = ctx.getViewMode();
        }

        @Override
        protected void populateItem(Item<SInstance> item) {

            final BSTRow row = new BSTRow("_r", BSGridSize.MD);
            final IModel<SInstance> im = item.getModel();
            final SInstance ins = im.getObject();

            SValidationFeedbackHandler feedbackHandler = SValidationFeedbackHandler.bindTo(row)
                .addInstanceModel(im)
                .addListener(ISValidationFeedbackHandlerListener.withTarget(t -> t.add(row)));
            row.add($b.classAppender("singular-form-table-row can-have-error"));
            row.add($b.classAppender("has-errors", $m.ofValue(feedbackHandler).map(it -> it.containsNestedErrors())));

            if (!(view instanceof SViewListByTable)) {
                return;
            }

            final SViewListByTable viewListByTable = (SViewListByTable) view;

            if (viewListByTable.isInsertEnabled()) {
                final BSTDataCell actionColumn = row.newCol();
                actionColumn.add($b.attrAppender("style", "width:20px", ";"));
                appendInserirButton(this, form, item, actionColumn);
            }

            if (ins instanceof SIComposite) {

                final SIComposite ci = (SIComposite) ins;
                final STypeComposite<?> ct = ci.getType();

                for (SType<?> ft : ct.getFields()) {
                    final IModel<SInstance> fm = new SInstanceCampoModel<>(item.getModel(), ft.getNameSimple());
                    wicketBuilder.build(ctx.createChild(row.newCol(), true, fm), viewMode);
                }

            } else {
                wicketBuilder.build(ctx.createChild(row.newCol(), true, im), viewMode);
            }

            if (viewListByTable.isDeleteEnabled() && viewMode.isEdition()) {
                final BSTDataCell actionColumn = row.newCol();
                actionColumn.add($b.attrAppender("style", "width:20px", ";"));
                appendRemoverButton(this, form, item, actionColumn);
            }

            item.add(row);
        }
    }
}
