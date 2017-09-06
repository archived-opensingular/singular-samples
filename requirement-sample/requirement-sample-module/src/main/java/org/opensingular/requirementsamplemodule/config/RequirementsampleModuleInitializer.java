package org.opensingular.requirementsamplemodule.config;

import org.opensingular.requirementsamplemodule.spring.PersistenceConfiguration;
import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;
import org.opensingular.server.module.config.ModuleInitializer;


public class RequirementsampleModuleInitializer implements ModuleInitializer {

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
        return PersistenceConfiguration.class;
    }
}
