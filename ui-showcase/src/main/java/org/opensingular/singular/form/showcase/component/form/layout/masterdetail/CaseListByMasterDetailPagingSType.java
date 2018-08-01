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

package org.opensingular.singular.form.showcase.component.form.layout.masterdetail;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewListByMasterDetail;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeExperienciaProfissional;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * List by Master Detail
 */
@CaseItem(componentName = "List by Master Detail", subCaseName = "Paginado", group = Group.LAYOUT,
        resources = {@Resource(STypeExperienciaProfissional.class), @Resource(CaseLayoutPackage.class)})
@SInfoType(spackage = CaseLayoutPackage.class, name = "MasterDetailPaging")
public class CaseListByMasterDetailPagingSType extends STypeComposite<SIComposite> {

    public STypeList<STypeExperienciaProfissional, SIComposite> experienciasProfissionais;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        experienciasProfissionais = this.addFieldListOf("experienciasProfissionais", STypeExperienciaProfissional.class);

        experienciasProfissionais
                //@destacar
                .withView(new SViewListByMasterDetail())
                .withInitListener(this::fillWithBlankValues);
    }

    private void fillWithBlankValues(SIList<SIComposite> list) {
        STypeExperienciaProfissional type = experienciasProfissionais.getElementsType();
        for (int i = 0; i < 101; i++) {
            SIComposite experiencia = list.addNew();
            experiencia.setValue(type.atividades, "Reuniões");
            experiencia.setValue(type.empresa, "Corp.");
            experiencia.setValue(type.cargo, "Gerente");
            experiencia.setValue(type.fim, new Date());
            experiencia.setValue(type.inicio, new Date());
        }
    }
}
