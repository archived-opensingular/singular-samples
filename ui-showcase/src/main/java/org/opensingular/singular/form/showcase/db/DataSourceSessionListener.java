package org.opensingular.singular.form.showcase.db;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebListener
public class DataSourceSessionListener implements HttpSessionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceSessionListener.class);
	
	public DataSourceSessionListener() {
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		LOGGER.info("****** Criou uma sessao !!!! " + se.getSession().getId());
		SessionDataSource ds = generateNewDatasource(se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		LOGGER.info("****** Destruiu a sessao !!!! " + se.getSession().getId());
		// TODO destruir o banco

	}

	private SessionDataSource generateNewDatasource(String sessionId) {
		return new SessionDataSource(sessionId);
	}

}
