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

package org.opensingular.singular.form.showcase.component.form.core.subtitle;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDecimal;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class)
public class STypeDadosProfissionais extends STypeComposite<SIComposite> {

    public STypeString profissao;
    public STypeString funcao;
    public STypeDecimal rendimento;
    public STypeDecimal redimentoFamilia;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        profissao = this.addFieldString("profissao");
        funcao = this.addFieldString("funcao");
        rendimento = this.addFieldDecimal("rendimento");
        redimentoFamilia = this.addFieldDecimal("redimentoFamilia");

        profissao.asAtr().label("Profissão");
        funcao.asAtr().label("Função");

        rendimento.asAtr().label("Rendimento");
        rendimento.asAtrBootstrap().colPreference(6);

        redimentoFamilia
                .asAtr().label("Rendimento Familiar")
                // @destacar
                .subtitle("O somatório de todo rendimento da mesma família.")
                .asAtrBootstrap().colPreference(6);

        this.asAtr().label("Dados Profissionais");
    }
}
