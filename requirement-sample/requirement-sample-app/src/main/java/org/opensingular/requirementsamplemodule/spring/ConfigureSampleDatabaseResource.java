package org.opensingular.requirementsamplemodule.spring;

import java.util.List;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.SQLServer2008Dialect;
import org.opensingular.server.commons.spring.ConfigureDatabaseResource;

public class ConfigureSampleDatabaseResource extends ConfigureDatabaseResource {

    private String MODE_ORACLE = "ORACLE";
    private String MODE_SQLSERVER = "SQLSERVER";


    @Override
    protected String getUrlConnection() {
        return "jdbc:h2:file:./singularserverdb;AUTO_SERVER=TRUE;mode=" + MODE_ORACLE + ";CACHE_SIZE=4096;EARLY_FILTER=1;LOCK_TIMEOUT=15000;";
    }

    @Nonnull
    @Override
    public Class<? extends Dialect> getHibernateDialect() {
        return SQLServer2008Dialect.class;
    }

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

}
