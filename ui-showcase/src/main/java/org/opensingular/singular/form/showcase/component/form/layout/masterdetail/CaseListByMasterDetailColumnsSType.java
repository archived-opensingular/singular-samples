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

package org.opensingular.singular.form.showcase.component.form.layout.masterdetail;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.list.SViewListByMasterDetail;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeExperienciaProfissional;

/**
 * List by Master Detail
 */
@CaseItem(componentName = "List by Master Detail", subCaseName = "Configurar Colunas", group = Group.LAYOUT,
        resources = { @Resource(STypeExperienciaProfissional.class), @Resource(CaseLayoutPackage.class) })
@SInfoType(spackage = CaseLayoutPackage.class, name = "ConfigColMasterDetail")
public class CaseListByMasterDetailColumnsSType extends STypeComposite<SIComposite> {

    public STypeList<STypeExperienciaProfissional, SIComposite> experienciasProfissionais;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        experienciasProfissionais = this.addFieldListOf("experienciasProfissionais", STypeExperienciaProfissional.class);

        STypeExperienciaProfissional stExperienciaProfissional = experienciasProfissionais.getElementsType();
        //@destacar:bloco
        SViewListByMasterDetail experienciaView = new SViewListByMasterDetail()
            .col(stExperienciaProfissional.empresa, "Empresa em que trabalhou") // Desta forma, será utilizado rótulo personalizado para esta coluna.
            .col(stExperienciaProfissional.inicio) //Nos demais, a coluna terá o mesmo rótulo do tipo que a define.
            .col(stExperienciaProfissional.fim)
            .col("Tempo de experiência",
                ins -> getPeriodoEmMeses(ins).map(it -> it + " meses").orElse(""),
                Comparator.comparing(it -> getPeriodoEmMeses(it).orElse(-1)))
            .label("Experiência Anterior");

        experienciasProfissionais.withView(experienciaView)
            //@destacar:fim
            .asAtr().label("Experiências profissionais");
    }

    private static Optional<Integer> getPeriodoEmMeses(SInstance instance) {
        Optional<LocalDate> inicio = Optional.ofNullable((YearMonth) instance.getField("inicio").getValue()).map(it -> it.atDay(1));
        Optional<LocalDate> fim = Optional.ofNullable((YearMonth) instance.getField("fim").getValue()).map(it -> it.atDay(1));
        return (inicio.isPresent() && fim.isPresent())
            ? Optional.of(Period.between(inicio.get(), fim.get()).getMonths())
            : Optional.empty();
    }
}
