/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.singular.form.showcase.view.page.studio;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Bytes;
import org.opensingular.form.wicket.component.SingularFormWicket;
import org.opensingular.singular.form.showcase.component.CaseBaseStudio;
import org.opensingular.singular.form.showcase.view.SingularWicketContainer;
import org.opensingular.singular.form.showcase.view.page.ItemCasePanel;
import org.opensingular.studio.core.panel.CrudShell;

public class StudioItemCasePanel extends ItemCasePanel<CaseBaseStudio> implements SingularWicketContainer<StudioItemCasePanel, Void> {

    private static final long serialVersionUID = 1L;


    public StudioItemCasePanel(String id, IModel<CaseBaseStudio> caseBase) {
        super(id, caseBase);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        SingularFormWicket<Void> form = new SingularFormWicket<>("form");
        form.setMultiPart(true);
        form.setFileMaxSize(Bytes.MAX);
        form.setMaxSize(Bytes.MAX);
        form.add(buildSingularBasePanel());
        add(form);
    }

    private CrudShell buildSingularBasePanel() {
        return new CrudShell("singularFormPanel", getCaseBase().getObject().getStudioDefinition());
    }


}
