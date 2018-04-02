package org.opensingular.requirementsamplemodule.spring;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.opensingular.app.commons.spring.persistence.database.SingularPersistenceConfiguration;

import java.util.List;

public class SamplePersistenceConfiguration implements SingularPersistenceConfiguration {


    @Override
    public void configureHibernatePackagesToScan(List<String> packagesToScan) {
        packagesToScan.add("com.opensingular.form");

    }

    @Override
    public void configureInitSQLScripts(List<String> scripts) {
        scripts.add("db/dml/import.sql");
    }

    @Override
    public Class<? extends Dialect> getHibernateDialect() {
        return H2Dialect.class;
    }


}
