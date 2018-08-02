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

package org.opensingular.singular.form.showcase.component.form.custom.comment;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.view.SViewBooleanSwitch;
import org.opensingular.form.view.SViewListByMasterDetail;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseCustomPackage.class)
public class STypeRequest extends STypeComposite<SIComposite> {

    public STypeBoolean paraPresente;
    public STypeBoolean entregaUrgente;
    public STypeList<STypeComposite<SIComposite>, SIComposite> itens;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        paraPresente = this.addFieldBoolean("paraPresente");
        entregaUrgente = this.addFieldBoolean("entregaUrgente");

        paraPresente
                .asAtr().label("Para presente")
                .asAtrAnnotation().setAnnotated();

        entregaUrgente
                .withView(SViewBooleanSwitch::new)
                .asAtr().label("Entrega urgente")
                .asAtrAnnotation().setAnnotated();

        itens = this.addFieldListOfComposite("itens", "item");
        itens.asAtr().label("Itens");
        itens.asAtrAnnotation().setAnnotated();

        STypeComposite<SIComposite> item = itens.getElementsType();
        item.addFieldString("descricao").asAtr().label("Descrição");
        item.addFieldString("obs").asAtr().label("Observações");
        item.asAtrAnnotation().setAnnotated();

        itens.withView(SViewListByMasterDetail::new);
    }
}
