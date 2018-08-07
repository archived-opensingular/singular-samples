/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.singular.form.showcase.db.job;

import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.singular.form.showcase.db.SessionDataSource;
import org.springframework.stereotype.Component;

/**
 * 
 * O DBCollectorJob é responsável por remover os bancos de dados gerados a mais
 * de um dia.
 *
 */
@Component("DBCollectorJob")
public class DBCollectorJob implements Loggable {

    public void run() {
        SessionDataSource.gc();
    }

}
