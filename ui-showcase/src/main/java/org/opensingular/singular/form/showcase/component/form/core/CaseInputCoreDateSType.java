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

package org.opensingular.singular.form.showcase.component.form.core;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeTime;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;

/**
 * Componente para inserção de data
 */
@CaseItem(componentName = "Date", subCaseName = "Simples", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "Date")
public class CaseInputCoreDateSType extends STypeComposite<SIComposite> {

    public STypeDate inicioDia;
    public STypeTime inicioHora;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        inicioDia = this.addFieldDate("inicioDia");
        inicioDia
                  .asAtr().label("Data Início");
        inicioHora = this.addField("inicioHora", STypeTime.class);
        inicioHora
                .asAtr().label("Hora Início");
    }
}
