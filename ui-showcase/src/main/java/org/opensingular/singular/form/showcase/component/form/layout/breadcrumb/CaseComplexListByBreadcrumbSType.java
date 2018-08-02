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

package org.opensingular.singular.form.showcase.component.form.layout.breadcrumb;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewBreadcrumb;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeComponente;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeSubComponente;

import javax.annotation.Nonnull;

/**
 * Breadcrumb
 */
/*hidden*/@CaseItem(componentName = "Breadcrumb", subCaseName = "Complexo", group = Group.LAYOUT, resources = {@Resource(STypeComponente.class),
/*hidden*/        @Resource(STypeSubComponente.class), @Resource(CaseLayoutPackage.class)})
@SInfoType(spackage = CaseLayoutPackage.class, name = "Complexo")
public class CaseComplexListByBreadcrumbSType extends STypeComposite<SIComposite> {

    public STypeList<STypeComponente, SIComposite> componentes;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        componentes = this.addFieldListOf("componentes", STypeComponente.class);

        componentes
                //@destacar
                .withView(SViewBreadcrumb::new)
                .asAtr().label("Componentes");
    }
}
