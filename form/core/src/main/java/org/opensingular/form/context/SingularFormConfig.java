/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.context;

import org.opensingular.form.document.ServiceRegistry;

public interface SingularFormConfig {


    public void setServiceRegistry(ServiceRegistry serviceRegistry);

    /**
     * Método factory para criar novo contexto de montagem ou manipulação de
     * formulário.
     */
    public SingularFormContext createContext();

}
