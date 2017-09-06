package org.opensingular.requirementsampleserver.config;

import org.opensingular.requirementsampleserver.spring.PersistenceConfiguration;
import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;

public class ServerInitializer implements org.opensingular.server.core.config.ServerInitializer {
    @Override
    public Class<? extends SingularDefaultPersistenceConfiguration> persistenceConfiguration() {
        return PersistenceConfiguration.class;
    }

    @Override
    public String[] springPackagesToScan() {
        return new String[]{"org.opensingular"};
    }
}