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

package org.opensingular.singular.form.showcase.component.form.core.multiselect;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.enums.PhraseBreak;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SMultiSelectionByPicklistView;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

/**
 * Permite a seleção múltipla no formato de uma pick list.
 */
@CaseItem(componentName = "Multi Select", subCaseName = "Pick List", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "PickList")
public class STCaseInputCoreMultiSelectPickList extends STypeComposite<SIComposite> {

    public STypeList<STypeString, SIString> frutas;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        frutas = this.addFieldListOf("frutas", STypeString.class);

        frutas.asAtr().phraseBreak(PhraseBreak.BREAK_LINE);
        frutas.selectionOf(String.class, new SMultiSelectionByPicklistView())
                .selfIdAndDisplay()
                .simpleProviderOf("Amora", "Banana", "Maçã", "Laranja", "Manga", "Melão", "Morango");
    }
}
