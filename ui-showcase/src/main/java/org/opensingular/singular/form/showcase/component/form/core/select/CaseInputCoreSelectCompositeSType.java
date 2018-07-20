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

package org.opensingular.singular.form.showcase.component.form.core.select;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.provider.SSimpleProviderListBuilder;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;
import org.opensingular.singular.form.showcase.component.form.core.select.form.STIngredienteQuimico;

import javax.annotation.Nonnull;

/**
 * Pemite a seleção de valores compostos de varios tipos diferentes.
 */
/*hidden*/@CaseItem(componentName = "Select", subCaseName = "Tipo Composto", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "SelectComposite")
public class CaseInputCoreSelectCompositeSType extends STypeComposite<SIComposite> {

    public STIngredienteQuimico ingredienteQuimico;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        ingredienteQuimico = this.addField("ingredienteQuimico", STIngredienteQuimico.class);

        ingredienteQuimico.selection()
                .id(ingredienteQuimico.formulaQuimica)
                .display("${nome} - ${formulaQuimica}")
                .simpleProvider(this::populateProvider);
    }

    private void populateProvider(SSimpleProviderListBuilder listBuilder) {
        listBuilder.add().set(ingredienteQuimico.formulaQuimica, "H20").set(ingredienteQuimico.nome, "Água");
        listBuilder.add().set(ingredienteQuimico.formulaQuimica, "H2O2").set(ingredienteQuimico.nome, "Água Oxigenada");
        listBuilder.add().set(ingredienteQuimico.formulaQuimica, "O2").set(ingredienteQuimico.nome, "Gás Oxigênio");
        listBuilder.add().set(ingredienteQuimico.formulaQuimica, "C12H22O11").set(ingredienteQuimico.nome, "Açúcar");
    }
}
