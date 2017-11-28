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

package org.opensingular.singular.form.showcase.component.form.layout;


import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeYearMonth;
import org.opensingular.form.view.SViewListByForm;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;

/**
 * List by Form
 */
@CaseItem(componentName = "List by Form", subCaseName = "Número de linhas iniciais", group = Group.LAYOUT)
@SInfoType(spackage = CaseLayoutPackage.class, name = "InitialNumberOfLines")
public class CaseListByFormPreAddLinesSType extends STypeComposite<SIComposite> {

    public STypeList<STypeComposite<SIComposite>, SIComposite> experienciasProfissionais;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        experienciasProfissionais = this.addFieldListOfComposite("experienciasProfissionais", "experiencia");

        STypeComposite<?>                                   experiencia         = experienciasProfissionais.getElementsType();
        STypeYearMonth                                      dtInicioExperiencia = experiencia.addField("inicio", STypeYearMonth.class, true);
        STypeYearMonth                                      dtFimExperiencia    = experiencia.addField("fim", STypeYearMonth.class);
        STypeString                                         empresa             = experiencia.addFieldString("empresa", true);
        STypeString                                         cargo               = experiencia.addFieldString("cargo", true);

        STypeString atividades = experiencia.addFieldString("atividades");

        {
            experienciasProfissionais
                    .withMiniumSizeOf(1)
                    .withMaximumSizeOf(3)
                    //@destacar:bloco
                    .withView(() -> new SViewListByForm().setInitialNumberOfLines(2))
                     //@destacar:fim
                    .asAtr()
                    .label("Experiências profissionais");
            experiencia
                    .asAtr()
                    .displayString("${cargo!} na empresa ${empresa!}")
                    .dependsOn(cargo, empresa);
            dtInicioExperiencia
                    .asAtr().label("Data inicial")
                    .asAtrBootstrap().colPreference(2);
            dtFimExperiencia
                    .asAtr().label("Data final")
                    .asAtrBootstrap().colPreference(2);
            empresa
                    .asAtr().label("Empresa")
                    .asAtrBootstrap().colPreference(8);
            cargo
                    .asAtr().label("Cargo");
            atividades
                    .withTextAreaView()
                    .asAtr().label("Atividades Desenvolvidas");
        }
    }
}
