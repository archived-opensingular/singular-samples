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

package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseCustomPackage.class)
public class STypeContainer extends STypeComposite<SIComposite> {

    public STypeContainerString  tab1;
    public STypeContainerInteger tab2;
    public STypeContainerBoolean tab3;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        tab1 = this.addField("tab1", STypeContainerString.class);
        tab2 = this.addField("tab2", STypeContainerInteger.class);
        tab3 = this.addField("tab3", STypeContainerBoolean.class);

        tab1.asAtr().label("Aba 1");
        tab2.asAtr().label("Aba 2");
        tab3.asAtr().label("Aba 3");
    }
}
