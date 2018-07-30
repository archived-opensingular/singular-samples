package org.opensingular.requirementsamplemodule;

import org.opensingular.requirement.module.FormFlowSingularRequirement;
import org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow;
import org.sample.form.EngenheiroForm;
import org.sample.form.RequirementsampleForm;

public class EngRequirement extends FormFlowSingularRequirement {
    public EngRequirement() {
        super("Formulario Engenheiro", EngenheiroForm.class, RequirementSampleFlow.class);
    }
}