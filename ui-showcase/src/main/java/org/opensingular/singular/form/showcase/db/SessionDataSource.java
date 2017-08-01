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
package org.opensingular.singular.form.showcase.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.singular.form.showcase.db.job.DBCollectorJob;

/**
 * Datasource capaz de criar um pool de datasources para cada sessão http.<br>
 * Os banco são criado dentro da uma pasta "db" e ao encerrar a sessão serão
 * apagados.<br>
 * <br>
 * Existe o job {@link DBCollectorJob} que é executado a cada 12h, que verifica
 * se o arquivo do banco tem mais que 24h de criado, caso seja positivo, o
 * arquivo é deletado.
 * 
 */
public class SessionDataSource extends BasicDataSource implements Loggable {

    public static final String DATABASE_FOLDER = "db";

    private static Map<String, BasicDataSource> internalPoolDS = new HashMap<String, BasicDataSource>();

    public SessionDataSource() {
        super();
    }

    public SessionDataSource(String sessionId) {
        this();
    }

    public BasicDataSource createNewDb(String sessionId) {
        BasicDataSource ddss = new BasicDataSource();
        ddss.setUrl(String.format("jdbc:h2:file:./" + DATABASE_FOLDER + "/singulardb_%s;", sessionId));
        ddss.setDriverClassName("org.h2.Driver");
        ddss.setUsername("sa");
        ddss.setPassword("sa");
        ddss.setRemoveAbandoned(true);
        ddss.setInitialSize(5);
        ddss.setMaxActive(10);
        ddss.setMinIdle(1);
        internalPoolDS.put(sessionId, ddss);
        return ddss;
    }

    public void removeDB(String sessionId) {
        BasicDataSource basicDataSource = internalPoolDS.get(sessionId);
        try {
            if (basicDataSource != null) {
                basicDataSource.close();
            }
        } catch (SQLException e) {
            getLogger().error("Erro ao fechar a conexão com o banco");
        }
        internalPoolDS.remove(sessionId);
    }

    @Override
    public Connection getConnection() throws SQLException {

        String sessionId = getSessionId();

        if (sessionId != null) {
            BasicDataSource ds = internalPoolDS.get(sessionId);
            if (ds == null) {
                getLogger().info("criou um novo Banco para a sessionId {} ", sessionId);
                ds = createNewDb(sessionId);
            }

            getLogger().info("Utilizou o DS da SesionID {}", sessionId);
            return ds.getConnection();
        } else {
            getLogger().info("Não tem sessão HTTP criada ! Utiliza o datasource original");
            return super.getConnection();
        }
    }

    private String getSessionId() {
        return IdSessionLocator.get().getSessionId();
    }

}
