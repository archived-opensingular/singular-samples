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

package org.opensingular.sample.studio.definition;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.opensingular.form.SInstance;
import org.opensingular.sample.studio.repository.EstudoResiduoRepository;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.definition.StudioTableDefinition;
import org.opensingular.studio.core.panel.CrudEditContent;
import org.opensingular.studio.core.panel.CrudShellContent;
import org.opensingular.studio.core.panel.CrudShellManager;

public class EstudoResiduoStudioDefinition implements StudioDefinition {

    @Override
    public Class<EstudoResiduoRepository> getRepositoryClass() {
        return EstudoResiduoRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioTableDefinition studioDataTable) {
        studioDataTable.add("Cultura", "cultura.display");
        studioDataTable.add("Modalidade de Emprego", "modalidadeDeEmprego.display");
        studioDataTable.add("Norma", "norma.display");
        studioDataTable.add("Tipo de Dose", "tipoDose.display");
        studioDataTable.add("Intervalo de Segurança", "intervaloSeguranca");
    }

    @Override
    public String getTitle() {
        return "Cadastro de Estudos de Residuos";
    }


    @Override
    public CrudEditContent buildEditContent(CrudShellManager crudShellManager, CrudShellContent previousContent, IModel<SInstance> instance) {
        CrudEditContent crudEditContent = new CrudEditContent(crudShellManager, previousContent, instance);
        crudEditContent.setSaveButtonFactory(new CrudEditContent.SaveButtonFactory(crudShellManager, crudEditContent) {
            @Override
            public Button make(String id, IModel<SInstance> instanceModel) {
                return new CrudEditContent.StudioSaveButton(id, instanceModel, crudShellManager, crudEditContent) {
                    @Override
                    protected void onValidationSuccess(AjaxRequestTarget target, Form<?> form, IModel<? extends SInstance> instanceModel) {
                        crudShellManager.addConfirm("Tem certeza que deseja salvar?", target, newTarget -> {
                            super.onValidationSuccess(newTarget, form, instanceModel);
                        });
                    }
                };
            }
        });
        return crudEditContent;
    }

}
