package org.opensingular.requirementsamplemodule;

import org.opensingular.requirement.module.RequirementConfigurationBuilder;
import org.opensingular.requirement.module.RequirementDefinition;
import org.opensingular.requirement.module.RequirementDefinitionConfiguration;
import org.opensingular.requirement.module.service.RequirementInstance;
import org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow;
import org.sample.form.RequirementsampleForm;

public class DadosPessoaisRequirement extends RequirementDefinition<RequirementInstance> {

    public DadosPessoaisRequirement() {
        super("Requirementsample");
    }

    @Override
    public RequirementDefinitionConfiguration configure(RequirementConfigurationBuilder conf) {
        return conf.name("Formulario dados pessoais")
                .mainForm(RequirementsampleForm.class)
                .flowDefintion(RequirementSampleFlow.class)
                .build();
    }
}