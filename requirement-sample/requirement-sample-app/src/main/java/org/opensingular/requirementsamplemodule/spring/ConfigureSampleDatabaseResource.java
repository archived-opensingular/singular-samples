package org.opensingular.requirementsamplemodule.spring;

import java.util.List;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.opensingular.server.commons.spring.ConfigureDatabaseResource;

public class ConfigureSampleDatabaseResource extends ConfigureDatabaseResource {

    @Override
    public String[] getHibernatePackagesToScan() {
        return ArrayUtils.add(super.getHibernatePackagesToScan(), "com.opensingular.form");
    }

    @Override
    public List<String> getScriptsPath() {
        return super.getScriptsPath();
    }

    @Override
    public String getImportFiles(String... directoryAndFile) {
        return super.getImportFiles("/db/dml/import.sql");
    }

    @Nonnull
    @Override
    public Class<? extends Dialect> getHibernateDialect() {
        return Oracle10gDialect.class;
    }
}
