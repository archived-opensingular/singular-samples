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

package org.opensingular.requirementsamplemodule;

import org.apache.wicket.Page;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.RequirementRegistry;
import org.opensingular.requirement.module.config.DefaultContexts;
import org.opensingular.requirement.module.config.ServerContext;
import org.opensingular.requirement.module.config.workspace.Workspace;
import org.opensingular.requirement.module.config.workspace.WorkspaceSettings;
import org.opensingular.requirement.module.wicket.SingularRequirementApplication;
import org.opensingular.requirement.module.workspace.DefaultDonebox;
import org.opensingular.requirement.module.workspace.DefaultDraftbox;
import org.opensingular.requirement.module.workspace.DefaultInbox;
import org.opensingular.requirement.module.workspace.DefaultOngoingbox;
import org.opensingular.requirement.module.workspace.WorkspaceRegistry;
import org.opensingular.requirement.studio.init.StudioSingularModule;
import org.opensingular.requirementsamplemodule.config.EngenheiroFormDefinition;
import org.opensingular.requirementsamplemodule.report.SampleReportPage;

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
                .add(EngenheiroRequirement.class);
    }

    @Override
    public void workspace(WorkspaceRegistry workspaceRegistry) {
        workspaceRegistry
                .add(RequirementSampleModuleRequirementContext.class)
                .add(RequirementSampleWorklistContext.class)
                .add(RequirementSampleReportContext.class);
    }

    public static class RequirementSampleWorklistContext extends DefaultContexts.WorklistContext {
        @Override
        public void configure(Workspace workspace) {
            workspace
                    .menu()
                    .addCategory("Worklist", category -> category
                            .icon(DefaultIcons.FOLDER)
                            .addBox(DefaultInbox.class)
                            .addBox(DefaultDonebox.class));
        }
    }

    public static class RequirementSampleModuleRequirementContext extends DefaultContexts.RequirementContext {
        @Override
        public void configure(Workspace workspace) {
            workspace
                    .menu()
                    .addCategory("Requerimentos", reqs -> reqs
                            .icon(DefaultIcons.ROCKET)
                            .addBox(DefaultDraftbox.class, box -> box
                                    .displayCounters(false)
                                    .newFor(DadosPessoaisRequirement.class)
                                    .newFor(EngRequirement.class))
                            .addBox(DefaultOngoingbox.class))
                    .addCategory("Cadastros", cadastros -> cadastros
                            .icon(DefaultIcons.MAGIC)
                            .addItem(SampleMenuItem.class)
                            .addCRUD(EngenheiroFormDefinition.class, engCRUD -> engCRUD.icon(DefaultIcons.USERS3)));

        }
    }

    public static class RequirementSampleReportContext extends ServerContext {
        public RequirementSampleReportContext() {
            super("REPORTS");
        }

        @Override
        public void configure(WorkspaceSettings settings) {
            settings
                    .contextPath("/reports/*")
                    .wicketApplicationClass(ReportWicketApplication.class)
                    .springSecurityConfigClass(null)
                    .checkOwner(true);
        }

        @Override
        public void configure(Workspace workspace) {

        }
    }

    public static class ReportWicketApplication extends SingularRequirementApplication {
        @Override
        public Class<? extends Page> getHomePage() {
            return SampleReportPage.class;
        }
    }

}