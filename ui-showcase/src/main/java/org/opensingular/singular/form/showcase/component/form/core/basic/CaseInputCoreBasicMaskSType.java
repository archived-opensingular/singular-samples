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

package org.opensingular.singular.form.showcase.component.form.core.basic;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

/**
 * Campos básicos para uso nos formulários do singular
 */
@CaseItem(componentName = "Basic", subCaseName = "Mask", group = Group.INPUT, resources = @Resource(CaseInputCorePackage.class))
@SInfoType(spackage = CaseInputCorePackage.class, name = "BasicMask")
public class CaseInputCoreBasicMaskSType extends STypeComposite<SIComposite> {

    public STypeString mask1;
    public STypeString mask2;
    public STypeString mask3;
    public STypeString mask4;
    public STypeString mask5;
    public STypeString mask6;
    public STypeString mask7;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        //  Default masking definitions
        //    9 : numeric
        //    a : alphabetical
        //    * : alphanumeric

        mask1 = this.addFieldString("mask1");
        mask2 = this.addFieldString("mask2");
        mask3 = this.addFieldString("mask3");
        mask4 = this.addFieldString("mask4");
        mask5 = this.addFieldString("mask5");
        mask6 = this.addFieldString("mask6");
        mask7 = this.addFieldString("mask7");

        mask1.asAtr().label("Número Mask (9999-9999-99)");
        //@destacar
        mask1.asAtr().basicMask("9999-9999-99");

        mask2.asAtr().label("Mix (aaa-9999)");
        //@destacar
        mask2.asAtr().basicMask("aaa-9999");

        mask3.asAtr().label("Número opcional (9999-9999[9])");
        //@destacar
        mask3.asAtr().basicMask("9999-9999[9]");

        mask4.asAtr().label("Dinâmica 1 (aa-9{4})");
        //@destacar
        mask4.asAtr().basicMask("aa-9{4}");

        mask5.asAtr().label("Dinâmica 2 (aa-9{1,4})");
        //@destacar
        mask5.asAtr().basicMask("aa-9{1,4}");

        mask6.asAtr().label("Alfanumérico (9999-****])");
        //@destacar
        mask6.asAtr().basicMask("9999-****");

        mask7.asAtr().label("Expressão regular para o campo todo [a-c1-3]");
        //@destacar
        mask7.asAtr().regexMask("[a-c1-3]");
    }
}
