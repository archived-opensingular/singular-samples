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

package org.opensingular.singular.form.showcase.component.form.layout.stypes;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewBreadcrumb;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypeComponente extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeList<STypeSubComponente, SIComposite> subComponentes;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        subComponentes = this.addFieldListOf("subComponentes", STypeSubComponente.class);

        nome.asAtr().label("Nome");

        subComponentes
                //@destacar
                .withView(SViewBreadcrumb::new)
                .asAtr().label("Testes de componente");

        this.withView(SViewByBlock::new);
    }
}
