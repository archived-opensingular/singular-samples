/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.flow.core.entity;

public interface IEntityProcessGroup extends IEntityByCod<String> {

    String getName();

    String getConnectionURL();

}