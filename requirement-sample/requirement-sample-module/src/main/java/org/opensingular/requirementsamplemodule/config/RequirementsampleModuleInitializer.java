package org.opensingular.requirementsamplemodule.config;

import org.opensingular.requirementsamplemodule.spring.PersistenceConfiguration;
import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;
import org.opensingular.server.module.config.ModuleInitializer;


public class RequirementsampleModuleInitializer extends ModuleInitializer {


    @Override
    protected String moduleCod() {
        return "REQUIREMENTSAMPLE";
    }

    @Override
    protected String[] springPackagesToScan() {
        return new String[]{"org.opensingular"};
    }

    @Override
    protected Class<? extends SingularDefaultPersistenceConfiguration> persistenceConfiguration() {
        return PersistenceConfiguration.class;
    }
}
