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

package org.opensingular.singular.form.showcase.view.page;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.opensingular.lib.wicket.util.tab.BSTabPanel;
import org.opensingular.singular.form.showcase.ShowCaseException;
import org.opensingular.singular.form.showcase.component.CaseBase;
import org.opensingular.singular.form.showcase.component.CaseBaseForm;
import org.opensingular.singular.form.showcase.component.CaseBaseStudio;
import org.opensingular.singular.form.showcase.component.CaseBaseWicket;
import org.opensingular.singular.form.showcase.component.ShowCaseTable;
import org.opensingular.singular.form.showcase.component.ShowCaseType;
import org.opensingular.singular.form.showcase.view.page.form.FormItemCasePanel;
import org.opensingular.singular.form.showcase.view.page.studio.StudioItemCasePanel;
import org.opensingular.singular.form.showcase.view.page.wicket.WicketItemCasePanel;
import org.opensingular.singular.form.showcase.view.template.ShowcaseTemplate;
import org.wicketstuff.annotation.mount.MountPath;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static org.opensingular.lib.wicket.util.util.WicketUtils.$m;

@MountPath("component")
@SuppressWarnings("serial")
public class ComponentPage extends ShowcaseTemplate {

    @Inject
    private ShowCaseTable showCaseTable;

    private ShowCaseTable.ShowCaseItem showCaseItem;

    public ComponentPage(PageParameters parameters) {
        String componentName = parameters.get(ShowCaseType.COMPONENT_NAME).toString();
        if (componentName == null) {
            throw new RestartResponseAtInterceptPageException(getApplication().getHomePage());
        }
        showCaseItem = showCaseTable.findCaseItemByComponentName(componentName);
        add(buildItemCases());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("$('#_menuItemShowCase').addClass('active');"));
    }

    private WebMarkupContainer buildItemCases() {

        WebMarkupContainer casesContainer = new WebMarkupContainer("casesContainer");

        if (caseUrlIsWrong()) {
            return casesContainer;
        }
        if (showCaseItem.getCases().size() > 1) {

            BSTabPanel bsTabPanel = new BSTabPanel("cases");

            showCaseItem.getCases().forEach(c -> {
                String name = c.getSubCaseName();
                if (name == null) {
                    name = c.getComponentName();
                }
                bsTabPanel.addTab(name, c.getSubCaseName(), createHandlerPanel(BSTabPanel.TAB_PANEL_ID, c));
            });
            casesContainer.add(bsTabPanel);

        } else if (!showCaseItem.getCases().isEmpty()) {
            casesContainer.add(createHandlerPanel("cases", showCaseItem.getCases().get(0)));
        }
        return casesContainer;
    }

    /** Finds the panel that is able to display the example case. */
    @Nonnull
    private Panel createHandlerPanel(@Nonnull String id, @Nonnull CaseBase<?> c) {
        if (c instanceof CaseBaseForm) {
            return new FormItemCasePanel(id, $m.ofValue((CaseBaseForm) c));
        } else if (c instanceof CaseBaseStudio) {
            return new StudioItemCasePanel(id, $m.ofValue((CaseBaseStudio) c));
        } else if (c instanceof CaseBaseWicket) {
            return new WicketItemCasePanel(id, $m.ofValue((CaseBaseWicket) c));
        }
        throw new ShowCaseException("There is no handler panel defined for " + c.getClass());
    }

    private boolean caseUrlIsWrong() {
        return showCaseItem == null || showCaseItem.getCases() == null;
    }

    @Override
    protected IModel<String> getContentTitle() {
        if (showCaseItem != null) {
            return $m.ofValue(showCaseItem.getComponentName());
        } else {
            return new ResourceModel("label.content.title", "");
        }
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return $m.ofValue("");
    }
}