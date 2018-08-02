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

package org.opensingular.requirementsamplemodule.flow;

import org.opensingular.flow.core.DefinitionInfo;
import org.opensingular.flow.core.FlowInstance;
import org.opensingular.flow.core.ITaskDefinition;
import org.opensingular.flow.core.defaults.PermissiveTaskAccessStrategy;
import org.opensingular.requirement.module.flow.builder.RequirementFlowBuilder;
import org.opensingular.requirement.module.flow.builder.RequirementFlowDefinition;
import org.opensingular.requirement.module.wicket.view.form.FormPage;
import org.opensingular.requirementsamplemodule.RequirementsampleModule;

import javax.annotation.Nonnull;

import static org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow.RequirementSampleTasks.AJUSTAR;
import static org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow.RequirementSampleTasks.ANALISAR;
import static org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow.RequirementSampleTasks.APROVADO;
import static org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow.RequirementSampleTasks.REPROVADO;


@DefinitionInfo(RequirementsampleModule.REQUIREMENT_SAMPLE)
public class RequirementSampleFlow extends RequirementFlowDefinition<FlowInstance> {

    public enum RequirementSampleTasks implements ITaskDefinition {

        ANALISAR("Analisar"),
        AJUSTAR("Solicitar Ajustes"),
        APROVADO("Aprovado"),
        REPROVADO("Reprovado");

        private String taskName;

        RequirementSampleTasks(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String getName() {
            return taskName;
        }
    }

    public RequirementSampleFlow() {
        super(FlowInstance.class);
        this.setName(RequirementsampleModule.REQUIREMENT_SAMPLE, "Sample");

    }


    @Override
    protected void buildFlow(@Nonnull RequirementFlowBuilder builder) {

        builder.addHumanTask(ANALISAR)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(SampleFormPage.class);

        builder.addHumanTask(AJUSTAR)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(FormPage.class);

        builder.addEndTask(REPROVADO);
        builder.addEndTask(APROVADO);


        builder.setStartTask(ANALISAR);

        builder.from(ANALISAR).go("Solicitar ajustes", AJUSTAR);
        builder.from(ANALISAR).go("Aprovar", APROVADO);
        builder.from(ANALISAR).go("Reprovar", REPROVADO);

        builder.from(AJUSTAR).go("Concluir Pendência", ANALISAR);
    }

}