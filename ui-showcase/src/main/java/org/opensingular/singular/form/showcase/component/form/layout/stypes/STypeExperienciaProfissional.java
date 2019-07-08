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
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeYearMonth;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypeExperienciaProfissional extends STypeComposite<SIComposite> {

    public STypeYearMonth inicio;
    public STypeYearMonth fim;
    public STypeString    empresa;
    public STypeString    cargo;
    public STypeString    atividades;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        inicio = addField("inicio", STypeYearMonth.class, true);
        fim = addField("fim", STypeYearMonth.class);
        empresa = addFieldString("empresa", true);
        cargo = addFieldString("cargo", true);
        atividades = addFieldString("atividades");

        inicio
                .asAtr().label("Data inicial")
                .asAtrBootstrap().colPreference(3);

        fim
                .asAtr().label("Data final")
                .asAtrBootstrap().colPreference(3);

        empresa
                .asAtr().label("Empresa")
                .asAtrBootstrap().newRow().colPreference(6);

        cargo
                .asAtr().label("Cargo")
                .asAtrBootstrap().colPreference(6);

        atividades
                .withTextAreaView()
                .asAtr().label("Atividades Desenvolvidas").help("Descrição breve")
                .asAtrBootstrap().colPreference(12);

        this.asAtr().label("Experiências profissionais");

        this.asAtr()
                //@destacar
                .displayString("<#if cargo??> Cargo: ${cargo!} </#if>")
                .dependsOn(cargo);
    }
}
