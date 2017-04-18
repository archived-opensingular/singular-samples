package org.opensingular.singular.form.showcase.db;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class SessionIdRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        IdSessionLocator.get().setSessionId(((HttpServletRequest) sre.getServletRequest()).getSession().getId());   
    }

}
