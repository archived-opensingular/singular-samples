package org.opensingular.requirementsamplemodule;

import org.opensingular.requirementsamplemodule.spring.ConfigureSampleDatabaseResource;
import org.opensingular.server.commons.test.SingularSchemaExportTest;


public class ExportSchemaTest extends SingularSchemaExportTest {

    @Override
    public void generateScriptByDialect() {
        ConfigureSampleDatabaseResource config = new ConfigureSampleDatabaseResource();
        generateScript(null, null ,config.getHibernateDialect(), config.getScriptsPath());
    }
}
