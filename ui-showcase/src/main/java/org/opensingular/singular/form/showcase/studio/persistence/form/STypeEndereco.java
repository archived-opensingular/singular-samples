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

package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeCEP;

import javax.annotation.Nonnull;

@SInfoType(name = "Endereco", spackage = SPackageStudioPersistenceForm.class)
public class STypeEndereco extends STypeComposite<SIComposite> {
    STypeCEP cep;
    STypeUF uf;
    STypeString endereco;
    STypeInteger numero;
    STypeString bairro;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        createType();
        cep.asAtr().label("CEP").asAtrBootstrap().colPreference(2);
        uf.asAtr().label("UF").asAtrBootstrap().colPreference(3);
        endereco.asAtr().label("Endereço").asAtrBootstrap().newRow().colPreference(6);
        numero.asAtr().label("Numero").asAtrBootstrap().colPreference(2);
        bairro.asAtr().label("Bairro").asAtrBootstrap().colPreference(4);
    }

    private void createType() {
        cep = addField("cep", STypeCEP.class);
        uf = addField("uf", STypeUF.class);
        endereco = addField("endereco", STypeString.class);
        numero = addField("numero", STypeInteger.class);
        bairro = addField("bairro", STypeString.class);
    }
}
