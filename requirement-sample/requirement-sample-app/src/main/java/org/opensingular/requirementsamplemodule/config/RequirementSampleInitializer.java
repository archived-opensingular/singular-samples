package org.opensingular.requirementsamplemodule.config;

import org.opensingular.requirementsamplemodule.spring.RequirementSamplePersistenceConfiguration;
import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;
import org.opensingular.server.studio.init.ServerStudioAppInitializer;

public class RequirementSampleInitializer implements ServerStudioAppInitializer {
    @Override
    public String moduleCod() {
        return "REQUIREMENTSAMPLE";
    }

    @Override
    public String[] springPackagesToScan() {
        return new String[]{"org.opensingular"};
    }

    @Override
    public Class<? extends SingularDefaultPersistenceConfiguration> persistenceConfiguration() {
        return RequirementSamplePersistenceConfiguration.class;
    }
}