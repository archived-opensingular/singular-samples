/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.flow.core.authorization;

public enum AccessLevel {
    /**
     * Listar
     */
    LIST("Listar"),
    /**
     * Detalhar
     */
    DETAIL("Detalhar");
    
    private final String name;

    private AccessLevel(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
