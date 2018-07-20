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

package org.opensingular.singular.form.showcase.component.form.core.numeric;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDecimal;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

/**
 * Campo para inserção de dados decimais.
 */
/*hidden*/@CaseItem(componentName = "Numeric", subCaseName = "Decimal", group = Group.INPUT, resources = @Resource(CaseInputCorePackage.class))
@SInfoType(spackage = CaseInputCorePackage.class, name = "Decimal")
public class CaseInputCoreDecimalSType extends STypeComposite<SIComposite> {

    public STypeDecimal decimalPadrao;
    public STypeDecimal decimalLongo;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        decimalPadrao = this.addFieldDecimal("decimalPadrao");
        decimalLongo = this.addFieldDecimal("decimalLongo");

        decimalPadrao
                .asAtr().label("Número decimal default")
                .required();

        decimalLongo
                .asAtr().label("Decimal com 15 inteiros e 10 dígitos")
                .required()
                .integerMaxLength(15)
                .fractionalMaxLength(10);
    }
}
