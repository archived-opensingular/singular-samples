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
import org.opensingular.form.STypeComposite;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.wicket.helpers.SingularFormBaseTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.opensingular.form.wicket.AjaxUpdateListenersFactory.SINGULAR_PROCESS_EVENT;

public class UpdateListenerTest extends SingularFormBaseTest {

    private STypeString logradouro;
    private STypeString cep;

    @Override
    protected void buildBaseType(STypeComposite<?> baseType) {

        baseType.asAtr().label("Endereço");
        cep = baseType.addFieldString("cep");
        cep.asAtr().maxLength(8).label("CEP (Use os valores 70863520 ou 70070120)");
        logradouro = baseType.addFieldString("logradouro");
        logradouro
                .asAtr().enabled(false)
                .label("Logradouro")
                .dependsOn(cep);

        logradouro.withUpdateListener(i -> {
            final Optional<SIString> cepField = i.findNearest(cep);
            cepField.ifPresent(c -> {
                if (c.getValue().equalsIgnoreCase("70863520")) {
                    i.setValue("CLN 211 Bloco 'B' Subsolo");
                } else if (c.getValue().equalsIgnoreCase("70070120")) {
                    i.setValue("SBS - Qd. 02 - Bl. Q - Centro Empresarial João Carlos Saad 12° andar");
                } else {
                    i.setValue("Não encontrado");
                }
            });
        });

    }

    @Test
    public void testarUpdateListenerCEPValido() {
        final FormComponent logradouro = findFirstFormComponentsByType(page.getForm(), this.logradouro);
        final FormComponent cep        = findFirstFormComponentsByType(page.getForm(), this.cep);

        form.setValue(cep, "70863520");
        tester.executeAjaxEvent(cep, SINGULAR_PROCESS_EVENT);
        assertEquals("Logradouro incorreto",
                logradouro.getDefaultModelObjectAsString(), "CLN 211 Bloco 'B' Subsolo");

        form.setValue(cep, "70070120");
        tester.executeAjaxEvent(cep, SINGULAR_PROCESS_EVENT);
        assertEquals("Logradouro incorreto",
                logradouro.getDefaultModelObjectAsString(), "SBS - Qd. 02 - Bl. Q - Centro Empresarial João Carlos Saad 12° andar");
    }


    @Test
    public void testarUpdateListenerCEPInvalido() {
        final FormComponent logradouro = findFirstFormComponentsByType(page.getForm(), this.logradouro);
        final FormComponent cep        = findFirstFormComponentsByType(page.getForm(), this.cep);

        form.setValue(cep, "12345678");
        tester.executeAjaxEvent(cep, SINGULAR_PROCESS_EVENT);
        assertEquals("Logradouro incorreto",
                logradouro.getDefaultModelObjectAsString(), "Não encontrado");
    }

}
