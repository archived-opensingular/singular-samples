/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.exemplos.emec.credenciamentoescolagoverno.form;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

@SInfoType(name = "Sexo", spackage = SPackageCredenciamentoEscolaGoverno.class)
public class STypeSexo extends STypeString {

    @Override
    protected void onLoadType(TypeBuilder tb) {
        super.onLoadType(tb);
        asAtr().label("Sexo");
        selectionOf("Masculino", "Feminino").withSelectView();
    }
}