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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

/**
 * Componente para inserção de min e max data
 */
/*hidden*/@CaseItem(componentName = "Date", subCaseName = "Min/Max Data", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "MinMaxDateTime")
public class CaseInputCoreDateMinMaxSType extends STypeComposite<SIComposite> {

    public STypeDate dateInicio;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        dateInicio = this.addFieldDate("dateInicio");

        dateInicio
                .asAtr().label("Data").subtitle("Min: 01/05/2018. Max: 01/08/2018")
                .asAtrBootstrap().colPreference(3);
        //@destacar:bloco
        dateInicio
                .minDate(generateDate("01/05/2018"))
                .maxDate(generateDate("01/08/2018"));
        //@destacar:fim
    }

    private Date generateDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            getLogger().error("Erro ao obter data", e);
        }
        return null;
    }
}
