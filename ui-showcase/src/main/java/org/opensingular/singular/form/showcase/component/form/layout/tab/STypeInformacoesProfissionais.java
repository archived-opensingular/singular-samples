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

package org.opensingular.singular.form.showcase.component.form.layout.tab;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypeInformacoesProfissionais extends STypeComposite<SIComposite> {

    public STypeString nomeEmpresa;
    public STypeDate dataInicio;
    public STypeString cargo;
    public STypeString atividadesDesenvolvidas;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nomeEmpresa = this.addFieldString("nomeEmpresa");
        dataInicio = this.addFieldDate("dataInicio");
        cargo = this.addFieldString("cargo");
        atividadesDesenvolvidas = this.addFieldString("atividadesDesenvolvidas");

        nomeEmpresa
                .asAtr().label("Empresa")
                .asAtrBootstrap().colPreference(4);

        dataInicio
                .asAtr().label("Data de início")
                .asAtrBootstrap().colPreference(4);

        cargo
                .asAtr().label("Cargo")
                .asAtrBootstrap().colPreference(2);

        atividadesDesenvolvidas
                .withTextAreaView()
                .asAtr().label("Atividades desenvolvidas")
                .asAtrBootstrap().colPreference(12);
    }
}
