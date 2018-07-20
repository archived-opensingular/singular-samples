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

package org.opensingular.singular.form.showcase.component.form.interaction;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewListByForm;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.interaction.form.SIItem;
import org.opensingular.singular.form.showcase.component.form.interaction.form.STItem;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Listener que é executado ao criar uma nova instância de um tipo
 */
/*hidden*/@CaseItem(componentName = "Listeners", subCaseName = "Init Listener", group = Group.INTERACTION,
/*hidden*/        resources = {@Resource(STItem.class), @Resource(SIItem.class), @Resource(CaseInteractionPackage.class)})
@SInfoType(spackage = CaseInteractionPackage.class, name = "InitListener")
public class CaseInitListenerSType extends STypeComposite<SIComposite> {

    public STypeList<STItem, SIItem> itens;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        itens = this.addFieldListOf("itens", STItem.class);

        itens
                .withView(new SViewListByForm().disableDelete().disableNew())
                .asAtr().label("Itens");

        //@destacar
        this.withInitListener(this::initForm);
    }

    private void initForm(SInstance instance) {
        final SIList<SIItem> itensList = instance.findNearestOrException(itens);

        for (String nomeItem : Arrays.asList("Mauro", "Laura")) {
            SIItem siItem = itensList.addNew();
            siItem.nome().setValue(nomeItem);
        }
    }
}
