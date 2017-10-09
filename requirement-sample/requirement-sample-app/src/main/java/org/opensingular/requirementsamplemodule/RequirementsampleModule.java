package org.opensingular.requirementsamplemodule;

import org.opensingular.requirementsamplemodule.flow.RequirementsampleFlow;
import org.sample.form.RequirementsampleForm;
import org.opensingular.server.commons.requirement.SingularRequirement;
import org.opensingular.server.module.RequirementConfiguration;
import org.opensingular.server.module.SingularModule;
import org.opensingular.server.module.WorkspaceConfiguration;
import org.opensingular.server.module.requirement.FormFlowSingularRequirement;
import org.opensingular.server.module.workspace.DefaultDonebox;
import org.opensingular.server.module.workspace.DefaultInbox;
import org.opensingular.server.p.module.workspace.DefaultDraftbox;
import org.opensingular.server.p.module.workspace.DefaultOngoingbox;

public class RequirementsampleModule implements SingularModule {

    public static final String              REQUIREMENTSAMPLE = "REQUIREMENTSAMPLE";
    private             SingularRequirement requirementsample = new FormFlowSingularRequirement("Requirementsample", RequirementsampleForm.class, RequirementsampleFlow.class);

    @Override
    public String abbreviation() {
        return REQUIREMENTSAMPLE;
    }

    @Override
    public String name() {
        return "Módulo Requirementsample";
    }

    @Override
    public void requirements(RequirementConfiguration config) {
        config
                .addRequirement(requirementsample);
    }

    @Override
    public void workspace(WorkspaceConfiguration config) {
        config
                .addBox(new DefaultDraftbox()).newFor(requirementsample)
                .addBox(new DefaultInbox())
                .addBox(new DefaultOngoingbox())
                .addBox(new DefaultDonebox());
    }
}
