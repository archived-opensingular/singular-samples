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
package org.opensingular.singular.form.showcase.db.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.lib.support.spring.util.ApplicationContextProvider;
import org.opensingular.singular.form.showcase.db.SessionDataSource;

/**
 * 
 * Listener para criar e destruir banco de dados h2.
 *
 */
@WebListener
public class SessionDataSourceLifecycleWebListener implements HttpSessionListener, ServletRequestListener, Loggable {

    public SessionDataSourceLifecycleWebListener() {}

    //////////////////////////////////////////
    // ServletRequestListener
    //////////////////////////////////////////
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        final HttpServletRequest httpReq = (HttpServletRequest) sre.getServletRequest();
        final HttpSession session = httpReq.getSession();
        final String sessionId = session.getId();

        SessionDataSource.setSessionId(sessionId);
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        SessionDataSource.clearSessionId();
    }

    //////////////////////////////////////////
    // HttpSessionListener
    //////////////////////////////////////////
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        final String sessionId = se.getSession().getId();
        getLogger().info(String.format("****** (%s) Criou uma sessao !!!! id=> %s",
            se.getSession().getMaxInactiveInterval(),
            sessionId));

        // SessionDataSource.generateDB(sessionId); // não precisa mais, o banco é gerado sob demanda
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        final String sessionId = se.getSession().getId();
        final SessionDataSource sds = ApplicationContextProvider.get().getBean("dataSource", SessionDataSource.class);

        getLogger().info("****** Destruiu a sessao !!!! {}", sessionId);

        //remove banco da sessão
        sds.removeDB(sessionId);

        //remove arquivo
        // SessionDataSource.destroyDB(sessionId); // não precisa mais, o banco é destruído ao ser removido
    }
}
