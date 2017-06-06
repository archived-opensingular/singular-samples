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
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * Listener que é executado quando um dependsOn é executado
 */
@CaseItem(componentName = "Listeners", subCaseName = "Update listener", group = Group.INTERACTION)
@SInfoType(spackage = CaseInteractionPackage.class, name = "UpdateListener")
public class CaseUpdateListenerSType extends STypeComposite<SIComposite> {

    public STypeString cep;
    public STypeString logradouro;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().label("Endereço");

        cep = this.addFieldString("cep");
        cep.asAtr().maxLength(8).label("CEP (Use os valores 70863520 ou 70070120)");

        logradouro = this.addFieldString("logradouro");
        logradouro
                .asAtr().enabled(false)
                .label("Logradouro")
                .dependsOn(cep);
        //@destacar
        logradouro.withUpdateListener(this::pesquisarLogradouro);
    }

    private void pesquisarLogradouro(SIString instance) {
        final Optional<SIString> cepField = instance.findNearest(cep);
        cepField.ifPresent(c -> {
            if (c.getValue().equalsIgnoreCase("70863520")) {
                instance.setValue("CLN 211 Bloco 'B' Subsolo");
            } else if (c.getValue().equalsIgnoreCase("70070120")) {
                instance.setValue("SBS - Qd. 02 - Bl. Q - Centro Empresarial João Carlos Saad 12° andar");
            } else {
                instance.setValue("Não encontrado");
            }
        });
    }
}
