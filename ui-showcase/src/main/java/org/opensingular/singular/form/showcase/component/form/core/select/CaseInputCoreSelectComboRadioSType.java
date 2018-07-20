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
import org.opensingular.form.view.SViewSelectionByRadio;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

/**
 * Seleção de itens por Combo e Radio
 */
/*hidden*/@CaseItem(componentName = "Select", subCaseName = "Combo e Radio", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "ComboRadio")
public class CaseInputCoreSelectComboRadioSType extends STypeComposite<SIComposite> {

    public STypeString tipoContato1;
    public STypeString tipoContato2;
    public STypeString tipoContato3;

    //@formatter:off
    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        tipoContato1 = this.addFieldString("tipoContato1");
        tipoContato2 = this.addFieldString("tipoContato2");
        tipoContato3 = this.addFieldString("tipoContato3");

        tipoContato1.selectionOf("Endereço", "Email", "Telefone", "Celular", "Fax");
        //View por Select
        tipoContato1
                //@destacar
                .withSelectView()
                .asAtr().label("Tipo Contato (Combo)");

        //View por Radio
        tipoContato2.selectionOf("Endereço", "Email", "Telefone", "Celular", "Fax");
        tipoContato2
                //@destacar
                .withRadioView()
                .asAtr().label("Tipo Contato (Radio) - Horizontal");

        //View por Radio com layout vertical
        tipoContato3.selectionOf("Endereço", "Email", "Telefone", "Celular", "Fax");
        tipoContato3
                //@destacar
                .withView(new SViewSelectionByRadio().verticalLayout())
                .asAtr().label("Tipo Contato (Radio) - Vertical");
    }
}
