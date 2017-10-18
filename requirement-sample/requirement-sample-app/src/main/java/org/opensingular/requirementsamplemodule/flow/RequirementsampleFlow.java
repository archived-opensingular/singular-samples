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

import org.opensingular.requirementsamplemodule.RequirementsampleModule;
import org.opensingular.flow.core.DefinitionInfo;
import org.opensingular.flow.core.ITaskDefinition;
import org.opensingular.flow.core.FlowInstance;
import org.opensingular.flow.core.defaults.PermissiveTaskAccessStrategy;
import org.opensingular.server.commons.flow.builder.RequirementFlowBuilder;
import org.opensingular.server.commons.wicket.view.form.FormPage;
import org.opensingular.server.commons.flow.builder.RequirementFlowDefinition;

import javax.annotation.Nonnull;

import static org.opensingular.requirementsamplemodule.flow.RequirementsampleFlow.RequirementsampleTasks.ANALISAR;
import static org.opensingular.requirementsamplemodule.flow.RequirementsampleFlow.RequirementsampleTasks.APROVADO;
import static org.opensingular.requirementsamplemodule.flow.RequirementsampleFlow.RequirementsampleTasks.REPROVADO;


@DefinitionInfo("requirementsample")
public class RequirementsampleFlow extends RequirementFlowDefinition<FlowInstance> {

    public enum RequirementsampleTasks implements ITaskDefinition {

        ANALISAR("Analisar"),
        APROVADO("Aprovado"),
        REPROVADO("Reprovado");

        private String taskName;

        RequirementsampleTasks(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String getName() {
            return taskName;
        }
    }

    public RequirementsampleFlow() {
        super(FlowInstance.class);
        this.setName(RequirementsampleModule.REQUIREMENTSAMPLE, "Requirementsample");

    }


    @Override
    protected void buildFlow(@Nonnull RequirementFlowBuilder builder) {

        builder.addHumanTask(ANALISAR)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(FormPage.class);

        builder.addEndTask(REPROVADO);
        builder.addEndTask(APROVADO);


        builder.setStartTask(ANALISAR);

        builder.from(ANALISAR).go("Aprovar", APROVADO);
        builder.from(ANALISAR).go("Reprovar", REPROVADO);
    }

}