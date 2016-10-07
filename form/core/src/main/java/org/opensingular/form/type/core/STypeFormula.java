/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.type.core;

import org.opensingular.form.TypeBuilder;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;

@SInfoType(name = "Formula", spackage = SPackageCore.class)
public class STypeFormula extends STypeComposite<SIFormula> {

    public static final String CAMPO_SCRIPT = "script";
    public static final String CAMPO_TIPO_SCRIPT = "tipoScript";

    public STypeFormula() {
        super(SIFormula.class);
    }

    @Override
    protected void onLoadType(TypeBuilder tb) {
        addFieldString(CAMPO_SCRIPT);
        STypeString tipo = addFieldString(CAMPO_TIPO_SCRIPT);
        tipo.selectionOfEnum(TipoScript.class);
    }

    public static enum TipoScript {
        JS;
    }
}
