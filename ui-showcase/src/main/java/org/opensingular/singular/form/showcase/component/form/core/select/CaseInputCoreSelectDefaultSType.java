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
import org.opensingular.form.type.core.STypeString;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

/**
 * Se a view não for definida, então define o componente dependendo da quantidade de dados e da obrigatoriedade.
 */
/*hidden*/@CaseItem(componentName = "Select", subCaseName = "Default", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "SelectDefault")
public class CaseInputCoreSelectDefaultSType extends STypeComposite<SIComposite> {

    public STypeString selecaoComTresOpcoes;
    public STypeString selecaoComTresOpcoesRequired;
    public STypeString selecaoComDezOpcoes;

    public STypeString favoriteFruit;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        selecaoComTresOpcoes = this.addFieldString("selecaoComTresOpcoes");
        selecaoComTresOpcoesRequired = this.addFieldString("selecaoComTresOpcoesRequired");
        selecaoComDezOpcoes = this.addFieldString("selecaoComDezOpcoes");
        favoriteFruit = this.addFieldString("favoriteFruit");

        selecaoComTresOpcoes.selectionOf(createOptions(3));
        selecaoComTresOpcoes.asAtr().required()
                .label("Seleção de 3");

        selecaoComTresOpcoesRequired.selectionOf(createOptions(3));
        selecaoComTresOpcoesRequired.asAtr().label("Seleção de 3");

        selecaoComDezOpcoes.selectionOf(createOptions(10));
        selecaoComDezOpcoes.asAtr().label("Seleção de 10");

        favoriteFruit.selectionOf("Maçã", "Laranja", "Banana", "Goiaba");
        favoriteFruit
                .withSelectView()
                .asAtr().label("Fruta Favorita");
    }

    private static String[] createOptions(int sizeOptions) {
        String[] options = new String[sizeOptions];
        for (int i = 1; i <= sizeOptions; i++) {
            options[i - 1] = "Opção " + i;
        }
        return options;
    }
}
