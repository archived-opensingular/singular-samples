package org.opensingular.singular.form.showcase.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.opensingular.lib.commons.util.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionDataSource extends BasicDataSource implements Loggable {

    private static Map<String, BasicDataSource> internalPoolDS = new HashMap<String, BasicDataSource>();

    public SessionDataSource() {
        super();
    }

    public SessionDataSource(String sessionId) {
        this();
    }

    public BasicDataSource createNewDb(String sessionId) {
        BasicDataSource ddss = new BasicDataSource();
        ddss.setUrl(String.format("jdbc:h2:file:./db/singulardb_%s;", sessionId));
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
            if(basicDataSource != null){
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
                getLogger().info("criou um novo Banco para a sessionId " + sessionId);
                ds = createNewDb(sessionId);
            }

            getLogger().info("Utilizou o DS da SesionID " + sessionId);
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

// SessionDataSource ds = ApplicationContextProvider.get().getBean("dataSource",
// SessionDataSource.class);