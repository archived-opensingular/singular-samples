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

package org.opensingular.singular.form.showcase.component.form.core;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SPackage;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDecimal;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;

/**
 * Campo para inserção de dados decimais.
 */
@CaseItem(componentName = "Numeric", subCaseName = "Decimal", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "Decimal")
public class CaseInputCoreDecimalSType extends STypeComposite<SIComposite> {

    public STypeDecimal decimalPadrao;
    public STypeDecimal decimalLongo;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        decimalPadrao = this.addFieldDecimal("decimalPadrao");
        decimalPadrao
                .asAtr().label("Número decimal default");

        decimalLongo = this.addFieldDecimal("decimalLongo");
        decimalLongo
                .asAtr().label("Decimal com 15 inteiros e 10 dígitos")
                .integerMaxLength(15)
                .fractionalMaxLength(10);
    }
}
