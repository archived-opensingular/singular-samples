/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.singular.form.showcase.view.page.showcase;

import org.apache.wicket.markup.html.form.FormComponent;
import org.junit.Test;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.list.SViewListByForm;
import org.opensingular.form.wicket.helpers.SingularFormBaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class ListenerInitTest extends SingularFormBaseTest {

    private STypeString nome;

    @Override
    protected void buildBaseType(STypeComposite<?> baseType) {

        STypeList<STypeComposite<SIComposite>, SIComposite> itens = baseType.addFieldListOfComposite("itens", "itenm");
        itens.asAtr().label("Itens");
        itens.withView(new SViewListByForm().disableDelete().disableNew());

        final STypeComposite<SIComposite> item = itens.getElementsType();
        nome = item.addFieldString("nome");
        nome
                .asAtr().label("Nome").enabled(false)
                .asAtrBootstrap().colPreference(3);

        final STypeInteger quantidade = item.addFieldInteger("quantidade");
        quantidade
                .asAtr().label("Quantidade")
                .asAtrBootstrap().colPreference(2);

        baseType.withInitListener(i -> {
            for (String n : Arrays.asList("Mauro", "Laura")) {
                final Optional<SIList<SIComposite>> itensList = i.findNearest(itens);
                itensList.ifPresent(il -> initItem(il, n, nome));
            }
        });
    }

    private void initItem(SIList<SIComposite> list, String nomeItem, STypeString nome) {
        final SIComposite item = list.addNew();
        item.findNearest(nome)
                .ifPresent(n -> n.setValue(nomeItem));
    }

    @Test
    public void testarInitListener() {
        final List<FormComponent> inputs = findFormComponentsByType(nome).collect(Collectors.toList());

        List<String> nomesCarregados = inputs.stream()
                .map(FormComponent::getDefaultModelObjectAsString)
                .collect(Collectors.toList());

        final List<String> expected = Arrays.asList("Mauro", "Laura");
        assertThat("List equality without order",
                nomesCarregados, containsInAnyOrder(expected.toArray()));
    }

}
