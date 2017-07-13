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
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByTable;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;

/**
 * List by Table
 */
@CaseItem(componentName = "List by Table", subCaseName = "Simple Type", group = Group.LAYOUT)
@SInfoType(spackage = CaseLayoutPackage.class, name = "SimpleTypeTable")
public class CaseListByTableSimpleTypeSType extends STypeComposite<SIComposite> {

    public STypeList<STypeString, SIString> nomes;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nomes = this.addFieldListOf("nomes", STypeString.class);

        nomes.withView(SViewListByTable::new);
        nomes.withMiniumSizeOf(2);
        nomes.asAtrBootstrap().colPreference(6);
        nomes.asAtr().label("Nomes");

        nomes.getElementsType().asAtr().required();
    }
}