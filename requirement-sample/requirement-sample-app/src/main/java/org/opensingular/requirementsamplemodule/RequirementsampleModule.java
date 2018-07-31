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

package org.opensingular.requirementsamplemodule;

import org.opensingular.requirement.module.RequirementRegistry;
import org.opensingular.requirement.module.WorkspaceConfiguration;
import org.opensingular.requirement.module.config.DefaultContexts;
import org.opensingular.requirement.module.workspace.DefaultDonebox;
import org.opensingular.requirement.module.workspace.DefaultDraftbox;
import org.opensingular.requirement.module.workspace.DefaultInbox;
import org.opensingular.requirement.module.workspace.DefaultOngoingbox;
import org.opensingular.requirement.module.workspace.WorkspaceRegistry;
import org.opensingular.requirement.studio.init.StudioSingularModule;

public class RequirementsampleModule implements StudioSingularModule {

    public static final String REQUIREMENT_SAMPLE = "SAMPLE";

    @Override
    public String abbreviation() {
        return REQUIREMENT_SAMPLE;
    }

    @Override
    public String name() {
        return "Módulo Exemplo de Requerimentos";
    }

    @Override
    public void requirements(RequirementRegistry requirementRegistry) {
        requirementRegistry
                .add(DadosPessoaisRequirement.class)
                .add(EngRequirement.class);
    }

    @Override
    public void workspace(WorkspaceRegistry workspaceRegistry) {
        workspaceRegistry
                .add(RequirementSampleModuleRequirementContext.class)
                .add(RequirementSampleWorklistContext.class);
    }

    public static class RequirementSampleWorklistContext extends DefaultContexts.WorklistContext {
        @Override
        public void setup(WorkspaceConfiguration workspaceConfiguration) {
            workspaceConfiguration
                    .addBox(DefaultInbox.class)
                    .addBox(DefaultDonebox.class);
        }
    }

    public static class RequirementSampleModuleRequirementContext extends DefaultContexts.RequirementContext {
        @Override
        public void setup(WorkspaceConfiguration workspaceConfiguration) {
            workspaceConfiguration
                    .addBox(DefaultDraftbox.class).newFor(DadosPessoaisRequirement.class, EngRequirement.class)
                    .addBox(DefaultOngoingbox.class);
        }
    }

}