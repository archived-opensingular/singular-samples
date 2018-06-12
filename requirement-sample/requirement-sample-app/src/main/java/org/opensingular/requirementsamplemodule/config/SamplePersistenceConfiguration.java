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

package org.opensingular.requirementsamplemodule.config;

import java.util.List;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.opensingular.app.commons.spring.persistence.database.ConfigurationPackagesToScan;
import org.opensingular.app.commons.spring.persistence.database.SingularPersistenceConfiguration;

public class SamplePersistenceConfiguration implements SingularPersistenceConfiguration {


    @Override
    public void configureHibernatePackagesToScan(ConfigurationPackagesToScan packagesToScan) {

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
