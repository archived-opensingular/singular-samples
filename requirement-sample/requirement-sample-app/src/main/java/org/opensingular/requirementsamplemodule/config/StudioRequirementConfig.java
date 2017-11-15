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

package org.opensingular.requirementsamplemodule.config;

import org.opensingular.form.persistence.FormRespository;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.studio.core.config.StudioConfig;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.definition.StudioTableDefinition;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioRequirementConfig implements StudioConfig {

    @Override
    public StudioMenu getAppMenu() {
        return StudioMenu.Builder.newPortalMenu()
                .addHTTPEndpoint(DefaultIcons.GLOBE, "Requerimento", "/requirement")
                .addHTTPEndpoint(DefaultIcons.COMMENT, "Análise", "/worklist")
                .addHTTPEndpoint(DefaultIcons.FILE_TEXT, "Relatórios", "/reports")
                .addSidebarGroup(DefaultIcons.MAGIC, "Cadastros", cadastros -> {
                    cadastros.addStudioItem("Engenheiros", new EngenheiroFormDefinition());
                })
                .getStudioMenu();
    }


    public static class EngenheiroFormDefinition implements StudioDefinition {
        @Override
        public Class<? extends FormRespository> getRepositoryClass() {
            return EngenheiroRepository.class;
        }

        @Override
        public void configureStudioDataTable(StudioTableDefinition studioDataTable) {
            studioDataTable.add("Nome", "nome");
        }

        @Override
        public String getTitle() {
            return "Cadastro de Engenheiros";
        }
    }
}