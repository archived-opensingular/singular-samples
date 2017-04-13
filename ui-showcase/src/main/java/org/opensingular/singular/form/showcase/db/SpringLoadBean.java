package org.opensingular.singular.form.showcase.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringLoadBean implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
