package org.opensingular.requirementsampleserver.config;

import org.opensingular.requirementsampleserver.spring.PersistenceConfiguration;
import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;

public class ServerInitializer extends org.opensingular.server.core.config.ServerInitializer {

    @Override
    protected Class<? extends SingularDefaultPersistenceConfiguration> persistenceConfiguration() {
        return PersistenceConfiguration.class;
    }

    @Override
    protected String[] springPackagesToScan() {
        return new String[]{"org.opensingular"};
    }
}
