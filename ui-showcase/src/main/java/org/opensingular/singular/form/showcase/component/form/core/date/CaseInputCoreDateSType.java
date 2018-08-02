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

package org.opensingular.singular.form.showcase.component.form.core.date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.time.DateUtils;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeTime;
import org.opensingular.form.view.date.SViewDate;
import org.opensingular.form.view.date.SViewTime;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

/**
 * Componentes personalizados para inserção de data
 */
/*hidden*/@CaseItem(componentName = "Date", subCaseName = "Personalizados", group = Group.INPUT, resources = @Resource(CaseInputCorePackage.class))
@SInfoType(spackage = CaseInputCorePackage.class, name = "Date")
public class CaseInputCoreDateSType extends STypeComposite<SIComposite> {

    public STypeDate livre;
    public STypeDate maiorIgualHoje;
    public STypeDate botaoLimpar;
    public STypeDate botaoHoje;
    public STypeDate hojeRealcado;
    public STypeDate datasHabilitadas;
    public STypeDate datasSemPicker;
    public STypeTime hora;
    public STypeTime horaSemPicker;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        livre = this.addFieldDate("livre");
        maiorIgualHoje = this.addFieldDate("maiorIgualHoje");
        botaoLimpar = this.addFieldDate("botaoLimpar");
        botaoHoje = this.addFieldDate("botaoHoje");
        hojeRealcado = this.addFieldDate("hojeRealcado");
        datasHabilitadas = this.addFieldDate("datasHabilitadas");
        datasSemPicker = this.addFieldDate("datasSemPicker");
        hora = this.addField("hora", STypeTime.class);
        horaSemPicker = this.addFieldTime("horaSemPicker");

        livre
                .asAtr().label("Seleção Livre");

        maiorIgualHoje
                .withView(new SViewDate().setStartDate(new Date()))
                .asAtr().label("Data igual ou maior que data atual");

        botaoLimpar
                .withView(new SViewDate().setClearBtn(true))
                .asAtr().label("Botão de limpar");

        botaoHoje
                .withView(new SViewDate().setTodayBtn(true))
                .asAtr().label("Botão que volta para o dia atual");

        hojeRealcado
                .withView(new SViewDate().setTodayHighlight(true))
                .asAtr().label("Data atual realçada");

        datasHabilitadas
                .withView(new SViewDate().setEnabledDatesFunction(this::tresDiasAntesDepoisAtual))
                .asAtr().label("Data habilitadas").subtitle("três dias antes e depois da data atual");

        hora.asAtr().label("Hora");

        horaSemPicker
                .withView(new SViewTime().hideModalTimePicker(Boolean.TRUE))
                .asAtr().label("Hora máscara simples")
                .asAtrBootstrap()
                .colMd(3);

        datasSemPicker
                .withView(new SViewDate().hideModalDatePicker(Boolean.TRUE))
                .asAtr()
                .label("Data máscara simples")
                .asAtrBootstrap()
                .colMd(3);

    }

    private List<Date> tresDiasAntesDepoisAtual(SInstance inst) {
        Date hoje = new Date();
        List<Date> datasHabilidatas = new ArrayList<>();
        for (int range = -3; range <= 3; range++) {
            datasHabilidatas.add(DateUtils.addDays(hoje, range));
        }
        return datasHabilidatas;
    }
}
