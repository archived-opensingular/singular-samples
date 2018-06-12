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
import org.opensingular.form.view.SViewDate;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Componente para inserção de data
 */
@CaseItem(componentName = "Date", subCaseName = "Simples", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "Date")
public class CaseInputCoreDateSType extends STypeComposite<SIComposite> {

    public STypeDate livre;
    public STypeDate maiorIgualHoje;
    public STypeDate botaoLimpar;
    public STypeDate botaoHoje;
    public STypeDate hojeRealcado;
    public STypeTime hora;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        livre = this.addFieldDate("livre");
        maiorIgualHoje = this.addFieldDate("maiorIgualHoje");
        botaoLimpar = this.addFieldDate("botaoLimpar");
        botaoHoje = this.addFieldDate("botaoHoje");
        hojeRealcado = this.addFieldDate("hojeRealcado");
        hora = this.addField("hora", STypeTime.class);

        livre
                .asAtr()
                .label("Seleção Livre");

        maiorIgualHoje
                .withView(new SViewDate().setStartDate(new Date()))
                .asAtr()
                .label("Data igual ou maior que data atual");

        botaoLimpar
                .withView(new SViewDate().setClearBtn(true))
                .asAtr()
                .label("Botão de limpar");

        botaoHoje
                .withView(new SViewDate().setTodayBtn(true))
                .asAtr()
                .label("Botão que volta para o dia atual");

        hojeRealcado
                .withView(new SViewDate().setTodayHighlight(true))
                .asAtr()
                .label("Data atual realçada");

        hora
                .asAtr().label("Hora");
    }
}
