package org.opensingular.singular.form.showcase.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionDataSource extends BasicDataSource{

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionDataSource.class); 
	private Map<String, BasicDataSource> internalPoolDS =  new HashMap<String, BasicDataSource>();

	public SessionDataSource() {
		super();
		setDriverClassName("org.h2.Driver");
		setUrl("jdbc:h2:file:./singulardb;AUTO_SERVER=TRUE;mode=ORACLE;CACHE_SIZE=2048;INIT=CREATE SCHEMA IF NOT EXISTS DBSINGULAR;MVCC=TRUE;LOCK_TIMEOUT=15000;");
		setUsername("sa");
		setPassword("sa");
		setRemoveAbandoned(true);
		setInitialSize(5);
		setMaxActive(10);
		setMinIdle(1);
	}
	
	public SessionDataSource(String sessionId){
		this();
		setUrl(String.format("jdbc:h2:file:./singulardb_%s;AUTO_SERVER=TRUE;mode=ORACLE;CACHE_SIZE=2048;INIT=CREATE SCHEMA IF NOT EXISTS DBSINGULAR;MVCC=TRUE;LOCK_TIMEOUT=15000;", sessionId));
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		return super.getConnection();
	}
	
	
	public void destroyDataSource(String sessionId){
		//TODO remover os arquivos do banco
//		SessionDataSource ds =  ApplicationContextProvider.get().getBean("dataSource", SessionDataSource.class);

	}

	

	
}
