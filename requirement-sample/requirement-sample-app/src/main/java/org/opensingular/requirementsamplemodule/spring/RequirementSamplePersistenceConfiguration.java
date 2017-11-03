/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.requirementsamplemodule.spring;

import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableTransactionManagement(
        proxyTargetClass = true
)
public class RequirementSamplePersistenceConfiguration extends SingularDefaultPersistenceConfiguration {

    @Value("classpath:db/dml/insert-requirementsample-module.sql")
    private Resource requirementsampleModule;


    @Value("classpath:/db/ddl/create-tables-form-extra.sql")
    private Resource createTablesFormExtra;

    @Value("classpath:/db/ddl/create-sequences-form-extra.sql")
    private Resource createSequencesFormExtra;


    @Override
    protected String getUrlConnection() {
        return "jdbc:h2:file:./singularserverdb;AUTO_SERVER=TRUE;mode=ORACLE;CACHE_SIZE=4096;EARLY_FILTER=1;LOCK_TIMEOUT=15000;";
    }

    @Override
    protected String[] hibernatePackagesToScan() {
        List<String> packages = new ArrayList<>(Arrays.asList(super.hibernatePackagesToScan())) ;
        packages.add("com.opensingular.form");
        return packages.toArray(new String[packages.size()]);
    }

    @Override
    protected ResourceDatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = super.databasePopulator();
        populator.addScript(requirementsampleModule);
        populator.addScript(createTablesFormExtra);
        populator.addScript(createSequencesFormExtra);
        return populator;
    }
}