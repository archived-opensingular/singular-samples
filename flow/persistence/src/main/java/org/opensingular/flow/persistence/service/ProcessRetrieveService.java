/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.flow.persistence.service;

import org.hibernate.Hibernate;
import org.opensingular.flow.persistence.entity.util.SessionLocator;
import org.springframework.transaction.annotation.Transactional;

import org.opensingular.flow.persistence.entity.ProcessInstanceEntity;

@Transactional(readOnly = true)
public class ProcessRetrieveService extends AbstractHibernateService {

    public void setSessionLocator(SessionLocator sessionLocator) {
        this.sessionLocator = sessionLocator;
    }

    /**
     *
     * @param cod
     * @return
     * @deprecated
     * Transformar em DTO essa busca da vários problemas de lazy para o historico (HistoricoContent)
     */
    @Deprecated
    public ProcessInstanceEntity retrieveProcessInstanceByCod(Integer cod) {
        ProcessInstanceEntity pi =  getSession().retrieve(ProcessInstanceEntity.class, cod);
        pi.getTasks().forEach(t -> {
            Hibernate.initialize(t.getTask());
            Hibernate.initialize(t.getAllocatedUser());
        });
        return pi;
    }

}
