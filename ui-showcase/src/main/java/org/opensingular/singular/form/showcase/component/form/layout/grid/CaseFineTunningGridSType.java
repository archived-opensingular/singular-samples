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

package org.opensingular.singular.form.showcase.component.form.layout.grid;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeEMail;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

/**
 * Permite a configuração fina do tamanho das colunas, sendo possível especificar o tamanho para telas de qualquer tamanho.
 */
/*hidden*/@CaseItem(componentName = "Grid", subCaseName = "Fine Tunning", group = Group.LAYOUT,
/*hidden*/        resources = @Resource(CaseLayoutPackage.class))
@SInfoType(spackage = CaseLayoutPackage.class, name = "FineTuning")
public class CaseFineTunningGridSType extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeInteger idade;
    public STypeEMail email;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        idade = this.addFieldInteger("idade");
        email = this.addFieldEmail("email");

        nome
                .asAtr().label("Nome")
                //@destacar
                .asAtrBootstrap().colLg(7).colMd(8).colSm(9).colXs(12);

        idade
                .asAtr().label("Idade")
                //@destacar
                .asAtrBootstrap().colLg(3).colMd(4).colSm(3).colXs(6);

        email
                .asAtr().label("E-mail")
                //@destacar
                .asAtrBootstrap().colLg(10).colMd(12).colSm(12).colXs(12);
    }
}
