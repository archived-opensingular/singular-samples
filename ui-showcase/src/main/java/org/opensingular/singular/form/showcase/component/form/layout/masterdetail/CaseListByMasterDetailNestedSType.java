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

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewListByMasterDetail;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeCargo;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeExperienciaProfissionalWithCargos;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypePet;

import javax.annotation.Nonnull;

/**
 * List by Master Detail
 */
/*hidden*/@CaseItem(componentName = "List by Master Detail", subCaseName = "Aninhado", group = Group.LAYOUT,
/*hidden*/        resources = {@Resource(STypeExperienciaProfissionalWithCargos.class), @Resource(STypeCargo.class), @Resource(STypePet.class)})
@SInfoType(spackage = CaseLayoutPackage.class, name = "MasterDetailNested")
public class CaseListByMasterDetailNestedSType extends STypeComposite<SIComposite> {

    public STypeList<STypeExperienciaProfissionalWithCargos, SIComposite> experienciasProfissionais;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        experienciasProfissionais = this.addFieldListOf("experienciasProfissionais", STypeExperienciaProfissionalWithCargos.class);

        //@destacar:bloco
        experienciasProfissionais
                .withView(SViewListByMasterDetail::new)
                .asAtr().label("Experiências profissionais");
        //@destacar:fim
    }
}
