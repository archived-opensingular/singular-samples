/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.SType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.wicket.IWicketComponentMapper;
import org.opensingular.form.wicket.WicketBuildContext;
import org.opensingular.form.wicket.model.SInstanceFieldModel;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSContainer;
import org.opensingular.lib.wicket.util.model.IReadOnlyModel;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom String Mapper
 */
@CaseItem(componentName = "Custom Mapper", subCaseName = "Tabbed Panel", group = Group.CUSTOM, resources = {
    @Resource(value = CaseCustomTabbedPanelMapperSType.CustomAjaxTabbedPanel.class, extension = "html"),@Resource(STypeContainer.class),
        @Resource(STypeContainerString.class), @Resource(STypeContainerInteger.class), @Resource(STypeContainerBoolean.class)})
@SInfoType(spackage = CaseCustomPackage.class, name = "TabbedPanel")
public class CaseCustomTabbedPanelMapperSType extends STypeComposite<SIComposite> {

    public STypeContainer mainComposite;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        mainComposite = this.addField("mainComposite", STypeContainer.class);

        //@destacar
        mainComposite.setAspect(IWicketComponentMapper.ASPECT_WICKET_MAPPER, CustomTabMapper::new);
    }

    // Mapper recursivo
    static final class CustomTabMapper implements IWicketComponentMapper {

        @Override
        @SuppressWarnings("unchecked")
        public void buildView(WicketBuildContext ctx) {
            final IModel<? extends SInstance> model = ctx.getModel();
            final STypeComposite<SIComposite> stype = (STypeComposite<SIComposite>) model.getObject().getType();

            final BSContainer<?> container = ctx.getContainer();
            final List<CustomTab> tabs = new ArrayList<>();
            for (SType<?> field : stype.getFields()) {
                tabs.add(new CustomTab(ctx, new SInstanceFieldModel<>(model, field.getNameSimple())));
            }
            container.appendComponent(id -> new CustomAjaxTabbedPanel(id, tabs));
        }
    }

    // Implementação de Tab que cria o contexto e o container de cada filho, e chama o builder para construí-los.
    static final class CustomTab implements ITab {
        private final SInstanceFieldModel<SInstance> model;
        private final WicketBuildContext             parentCtx;
        public CustomTab(WicketBuildContext parentCtx, SInstanceFieldModel<SInstance> model) {
            this.parentCtx = parentCtx;
            this.model = model;
        }
        @Override
        public IModel<String> getTitle() {
            return IReadOnlyModel.of(() -> model.getObject().asAtr().getLabel());
        }
        @Override
        public WebMarkupContainer getPanel(String containerId) {
            // container filho
            BSContainer<?> childContainer = new BSContainer<>(containerId);
            // contexto filho
            WicketBuildContext childCtx = parentCtx.createChild(childContainer, parentCtx.getExternalContainer(), model);

            // chamada recursiva ao UIBuilderWicket
            childCtx.build();

            return childContainer;
        }
        @Override
        public boolean isVisible() {
            return true;
        }
    }

    // Utilizando AjaxTabbedPanel built-in no wicket-extensions, customizado para funcionar com o bootstrap. 
    static final class CustomAjaxTabbedPanel extends AjaxTabbedPanel<CustomTab> {
        private CustomAjaxTabbedPanel(String id, List<CustomTab> tabs) {
            super(id, tabs);
        }
        @Override
        protected String getSelectedTabCssClass() {
            return "active";
        }
        @Override
        protected String getTabContainerCssClass() {
            return "nav nav-tabs";
        }
    }
}
