/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form;

import org.opensingular.form.function.IBehavior;
import org.opensingular.form.type.basic.SPackageBasic;

@SInfoType(name = "Behavior", spackage = SPackageBasic.class)
public class STypeBehavior extends STypeCode<SIBehavior, IBehavior<SInstance>> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public STypeBehavior() {
        super((Class) SIBehavior.class, (Class) IBehavior.class);
    }
}