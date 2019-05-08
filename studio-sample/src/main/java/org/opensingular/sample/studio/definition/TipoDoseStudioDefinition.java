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

package org.opensingular.sample.studio.definition;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.opensingular.form.SInstance;
import org.opensingular.lib.wicket.util.datatable.column.BSActionPanel;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.sample.studio.repository.TipoDoseRepository;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.definition.StudioTableDefinition;
import org.opensingular.studio.core.panel.CrudShellManager;
import org.opensingular.studio.core.panel.action.ListAction;

public class TipoDoseStudioDefinition implements StudioDefinition {
    @Override
    public Class<TipoDoseRepository> getRepositoryClass() {
        return TipoDoseRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioTableDefinition studioDataTable) {
        studioDataTable.add("Tipo de Dose", "nome");
        studioDataTable.add(new ListAction() {
            @Override
            public void configure(BSActionPanel.ActionConfig<SInstance> config) {
                config.iconeModel(Model.of(DefaultIcons.HAND_UP));
            }

            @Override
            public void onAction(AjaxRequestTarget target, IModel<SInstance> model, CrudShellManager crudShellManager) {
                target.appendJavaScript("alert('Ola Mundo!!!');");
            }
        });
    }

    @Override
    public String getTitle() {
        return "Cadastro de Tipos de Doses";
    }

}