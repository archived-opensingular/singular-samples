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

package org.opensingular.singular.form.showcase.component.form.core.country;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SPackage;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.type.country.brazil.STypeCEP;
import org.opensingular.form.type.country.brazil.STypeCNPJ;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

/**
 * Campos de endereço - Brasil
 */

@CaseItem(componentName = "Brasil", subCaseName = "Endereço", group = Group.COUNTRY)
public class CaseBrazilAddressPackage extends SPackage {

    @Override
    protected void onLoadPackage(PackageBuilder pb) {
        
    	STypeComposite<?> tipoMyForm = pb.createCompositeType("testForm");
        
        tipoMyForm.asAtr().label("Dados Cadastrais:").asAtrBootstrap();
        tipoMyForm.addField("Endereco", STypeAddress.class);

        super.onLoadPackage(pb);
    }
}
