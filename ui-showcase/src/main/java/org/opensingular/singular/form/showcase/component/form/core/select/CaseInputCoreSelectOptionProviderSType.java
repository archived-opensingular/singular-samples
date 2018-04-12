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

import static org.apache.commons.lang3.StringUtils.*;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeOption;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByTable;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

@CaseItem(componentName = "Select", subCaseName = "Provedor de Opções", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "SelectOptionProvider")
public class CaseInputCoreSelectOptionProviderSType extends STypeComposite<SIComposite> {

    @SInfoType(spackage = CaseInputCorePackage.class, name = "SelectOptionProvider_STypePessoa")
    public static class STypePessoa extends STypeComposite<SIComposite> {
        public STypeString nome;
        @Override
        protected void onLoadType(TypeBuilder tb) {
            nome = this.addFieldString("nome");
            nome.asAtr().label("Nome");
        }
    }

    public STypeList<STypePessoa, SIComposite> pessoas;
    public STypePessoa                         pessoa;
    public STypeOption<SIComposite>            pessoaSelecionada;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        pessoas = this.addFieldListOf("pessoas", STypePessoa.class);
        pessoa = pessoas.getElementsType();
        pessoaSelecionada = addFieldOption("pessoaSelecionada", STypePessoa.class);

        pessoas.withView(new SViewListByTable())
            .asAtr().label("Pessoas");

        pessoaSelecionada.withSelectionFromOptionProvider(pessoas, it -> defaultString(it.getFieldValue(pessoa.nome)))
            .asAtr().label("Seleção");
    }
}
