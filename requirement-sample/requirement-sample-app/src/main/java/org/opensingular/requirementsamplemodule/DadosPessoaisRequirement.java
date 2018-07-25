package org.opensingular.requirementsamplemodule;

import org.opensingular.requirement.module.FormFlowSingularRequirement;
import org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow;
import org.sample.form.RequirementsampleForm;

public class DadosPessoaisRequirement extends FormFlowSingularRequirement {
    public DadosPessoaisRequirement() {
        super("Formulario dados pessoais", RequirementsampleForm.class, RequirementSampleFlow.class);
    }
}