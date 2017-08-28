package org.opensingular.sample.studio.cfg;

import org.opensingular.studio.app.spring.StudioPersistenceConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

public class StudioSampleSpringPersistenceConfig extends StudioPersistenceConfiguration {
    @Value("classpath:ddl/toxicologia-ddl.sql")
    private Resource toxicologiaDdl;

    @Value("classpath:dml/toxicologia-dml.sql")
    private Resource toxicologiaDml;

    protected ResourceDatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding(StandardCharsets.UTF_8.name());
        populator.addScript(toxicologiaDdl);
        populator.addScript(toxicologiaDml);
        return populator;
    }

    @Bean
    public DataSourceInitializer scriptsInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    @Override
    protected String[] hibernatePackagesToScan() {
        return new String[]{"org.opensingular.sample.studio.entity"};
    }
}