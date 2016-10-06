/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.view;

import org.opensingular.form.SType;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SView implements Serializable {

    public static final SView DEFAULT = new SView();

    public boolean isApplicableFor(SType<?> type) {
        return true;
    }
}