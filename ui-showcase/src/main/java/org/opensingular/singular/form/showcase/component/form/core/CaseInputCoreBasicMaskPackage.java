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
import org.opensingular.form.SPackage;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeCEP;
import org.opensingular.form.type.country.brazil.STypeCNPJ;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

/**
 * Campos básicos para uso nos formulários do singular
 */

@CaseItem(componentName = "Basic", subCaseName = "Mask", group = Group.INPUT)
public class CaseInputCoreBasicMaskPackage extends SPackage {

    @Override
    protected void onLoadPackage(PackageBuilder pb) {
        STypeComposite<?> tipoMyForm = pb.createCompositeType("testForm");
        
        
        //  Default masking definitions
        //    9 : numeric
        //    a : alphabetical
        //    * : alphanumeric
        
        

        STypeString mask1 = tipoMyForm.addFieldString("mask1");
        mask1.asAtr().label("Número Mask (9999-9999-99)");
        //@destacar
        mask1.asAtr().basicMask("9999-9999-99");
        
        STypeString mask2 = tipoMyForm.addFieldString("mask2");
        mask2.asAtr().label("Mix (aaa-9999)");
        //@destacar
        mask2.asAtr().basicMask("aaa-9999");

        STypeString mask3 = tipoMyForm.addFieldString("mask3");
        mask3.asAtr().label("Número opcional (9999-9999[9])");
        //@destacar
        mask3.asAtr().basicMask("9999-9999[9]");


        STypeString mask4 = tipoMyForm.addFieldString("mask4");
        mask4.asAtr().label("Dinâmica 1 (aa-9{4})");
        //@destacar
        mask4.asAtr().basicMask("aa-9{4}");

        STypeString mask5 = tipoMyForm.addFieldString("mask5");
        mask5.asAtr().label("Dinâmica 2 (aa-9{1,4})");
        //@destacar
        mask5.asAtr().basicMask("aa-9{1,4}");
        
        STypeString mask6 = tipoMyForm.addFieldString("mask6");
        mask6.asAtr().label("Alfanumérico (9999-****])");
        //@destacar
        mask6.asAtr().basicMask("9999-****");
        
        super.onLoadPackage(pb);
        
    }
}
