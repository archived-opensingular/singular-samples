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