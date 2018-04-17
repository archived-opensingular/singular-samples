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
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeOption;
import org.opensingular.form.view.SViewListByTable;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

import static org.apache.commons.lang3.StringUtils.*;

@CaseItem(componentName = "Select", subCaseName = "Provedor de Opções", group = Group.INPUT, resources = {@Resource(STypePessoa.class)})
@SInfoType(spackage = CaseInputCorePackage.class, name = "SelectOptionProvider")
public class CaseInputCoreSelectOptionProviderSType extends STypeComposite<SIComposite> {

    public STypeList<STypePessoa, SIComposite> pessoas;
    public STypeOption<SIComposite>            pessoaSelecionada;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        pessoas = this.addFieldListOf("pessoas", STypePessoa.class);
        pessoaSelecionada = addFieldOption("pessoaSelecionada", STypePessoa.class);

        pessoas.withView(new SViewListByTable())
                .asAtr()
                .label("Pessoas");

        STypePessoa pessoa = pessoas.getElementsType();
        pessoaSelecionada
                .withSelectionFromOptionProvider(pessoas, it -> defaultString(it.getFieldValue(pessoa.nome)))
                .asAtr()
                .label("Seleção")
                .dependsOn(pessoas, pessoa.nome);
    }
}
