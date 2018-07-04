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

package org.opensingular.singular.form.showcase.component.form.core;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDateTime;
import org.opensingular.form.view.SViewDateTime;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

/**
 * Componente para inserção de data e hora com seletor configurado para avançar de 3 em 3 minutos
 */
@CaseItem(componentName = "Date", subCaseName = "Data e Hora de 3 em 3 minutos", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "DateTimeHour")
public class CaseInputCoreDateTimeStepSType extends STypeComposite<SIComposite> {

    public STypeDateTime inicio;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        inicio = this.addFieldDateTime("inicio");

        inicio
                .asAtr()
                .label("Início")
                .asAtrBootstrap()
                .colPreference(3);
        //@destacar
        inicio.withView(() -> new SViewDateTime().setMinuteStep(3));
    }
}
