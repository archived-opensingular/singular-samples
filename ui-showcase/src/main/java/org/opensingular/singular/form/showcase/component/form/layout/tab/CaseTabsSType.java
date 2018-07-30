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

package org.opensingular.singular.form.showcase.component.form.layout.tab;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewTab;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeExperienciaList;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeExperienciaProfissional;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeInformacaoPessoal;

import javax.annotation.Nonnull;

/*hidden*/
/*hidden*/
/*hidden*/

/**
 * Tabs
 */
/*hidden*/@CaseItem(componentName = "Tabs", subCaseName ="Default", group = Group.LAYOUT, resources = {@Resource(STypeInformacaoPessoal.class),
/*hidden*/        @Resource(STypeExperienciaProfissional.class), @Resource(STypeExperienciaList.class), @Resource(CaseLayoutPackage.class)})
@SInfoType(spackage = CaseLayoutPackage.class, name = "DefaultTabs")
public class CaseTabsSType extends STypeComposite<SIComposite> {

    public STypeInformacaoPessoal tab1;
    public STypeExperienciaList tab2;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        tab1 = addField("tab1", STypeInformacaoPessoal.class);
        tab2 = addField("tab2", STypeExperienciaList.class);

        //@destacar:bloco
        SViewTab tabbed = new SViewTab();
        tabbed.addTab("informacoes", "Informações pessoais").add(tab1);
        tabbed.addTab("exp","Experiências profissionais").add(tab2);
        this.withView(tabbed);
        //@destacar:fim
    }
}
