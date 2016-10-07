/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.type.core;

import org.opensingular.form.STypeSimple;
import org.opensingular.form.SInfoType;
import org.apache.commons.lang3.StringUtils;

@SInfoType(name = "Long", spackage = SPackageCore.class)
public class STypeLong extends STypeSimple<SILong, Long> {

    public STypeLong() {
        super(SILong.class, Long.class);
    }

    protected STypeLong(Class<? extends SILong> classeInstancia) {
        super(classeInstancia, Long.class);
    }

    @Override
    protected Long convertNotNativeNotString(Object valor) {
        if (valor instanceof Number) {
            long longValue = ((Number) valor).longValue();
            if (longValue > Long.MAX_VALUE) {
                throw createConversionError(valor, Long.class, " Valor muito grande.", null);
            }
            if (longValue < Long.MIN_VALUE) {
                throw createConversionError(valor, Long.class, " Valor muito pequeno.", null);
            }
            return ((Number) valor).longValue();
        }
        throw createConversionError(valor);
    }

    @Override
    public Long fromString(String valor) {
        valor = StringUtils.trimToNull(valor);
        if (valor == null) {
            return null;
        }
        try {
            return Long.parseLong(valor);
        } catch (Exception e) {
            throw createConversionError(valor, Long.class, null, e);
        }
    }
}
