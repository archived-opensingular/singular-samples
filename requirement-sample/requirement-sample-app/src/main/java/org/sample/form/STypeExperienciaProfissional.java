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

package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeYearMonth;

import javax.annotation.Nonnull;

@SInfoType(spackage = RequirementsamplePackage.class)
public class STypeExperienciaProfissional extends STypeComposite<SIComposite> {

    public STypeYearMonth inicio;
    public STypeDate fim;
    public STypeString    empresa;
    public STypeString    cargo;
    public STypeBoolean sTypeBoolean;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        inicio = addField("inicio", STypeYearMonth.class, true);
        fim = addFieldDate("fim");
        empresa = addFieldString("empresa", true);
        cargo = addFieldString("cargo", true);
        sTypeBoolean = addFieldBoolean("sTypeBoolean");

        inicio
                .asAtr().label("Data inicial")
                .asAtrAnnotation().setAnnotated();

        fim
                .asAtr().label("Data final")
                .asAtrAnnotation().setAnnotated();

        empresa
                .asAtr().label("Empresa")
                .asAtrAnnotation().setAnnotated();

        cargo
                .asAtr().label("Cargo").help("TESTE CARACTER ESPECIAL ÇÃO.")
                .asAtrAnnotation().setAnnotated();

        sTypeBoolean
                .asAtrAnnotation().setAnnotated()
                .asAtr().label("teste").visible(false);

        this.asAtr().label("Experiências profissionais");
        this.asAtr().displayString("Empresa: ${empresa!} ").dependsOn(empresa);

    }
}
