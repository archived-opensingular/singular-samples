package org.opensingular.requirementsamplemodule;

import org.opensingular.requirement.module.FormFlowSingularRequirement;
import org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow;
import org.sample.form.RequirementsampleForm;

public class SampleRequirement extends FormFlowSingularRequirement {
    public SampleRequirement() {
        super("Requirementsample", RequirementsampleForm.class, RequirementSampleFlow.class);
    }
}