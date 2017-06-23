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

package org.opensingular.singular.form.showcase.component.form.layout;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;


/**
 * Configura automaticamente o tamanho das colunas do bootstrap para telas menores,
 * multiplicando pelo fator de 2, 3 e 4 para colunas md (médium), sm (small) e xs (extra small),
 * mantendo o máximo de 12.
 * Por exemplo, ao configurar o tamanho para 3, o tamanho md será 6, sm 12 e xs 12.
 */
@CaseItem(componentName = "Grid", subCaseName = "Simple", group = Group.LAYOUT)
@SInfoType(spackage = CaseLayoutPackage.class, name = "Simple")
public class CaseSimpleGridSType extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeInteger idade;
    public STypeEMail email;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        nome
                .asAtr().label("Nome")
                .asAtrBootstrap().colPreference(6);

        idade = this.addFieldInteger("idade");
        idade
                .asAtr().label("Idade")
                .asAtrBootstrap().colPreference(2);

        email = this.addFieldEmail("email");
        email
                .asAtr().label("E-mail")
                .asAtrBootstrap().colPreference(8);
    }
}
