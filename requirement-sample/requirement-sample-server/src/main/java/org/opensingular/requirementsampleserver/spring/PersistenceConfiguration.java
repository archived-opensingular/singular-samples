package org.opensingular.requirementsampleserver.spring;

import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement(
        proxyTargetClass = true
)
public class PersistenceConfiguration extends SingularDefaultPersistenceConfiguration {

    @Override
    protected String getUrlConnection() {
        return "jdbc:h2:file:./singularserverdb;AUTO_SERVER=TRUE;mode=ORACLE;CACHE_SIZE=4096;EARLY_FILTER=1;LOCK_TIMEOUT=15000;";
    }


    @Override
    protected boolean isDatabaseInitializerEnabled() {
        return false;
    }
}
