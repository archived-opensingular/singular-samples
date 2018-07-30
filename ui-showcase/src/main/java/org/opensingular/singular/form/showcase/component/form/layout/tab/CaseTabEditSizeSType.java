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
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.view.SViewTab;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

/**
 * O tamanho da aba pode ser configurado utilizando o método navColPreference,
 * esse tamanho será aplicado para todas as resoluções.
 */
/*hidden*/@CaseItem(componentName = "Tabs", subCaseName = "Tamanho da aba", group = Group.LAYOUT,
/*hidden*/        resources = {@Resource(CaseLayoutPackage.class), @Resource(STypeInformacoesProfissionais.class)})
@SInfoType(spackage = CaseLayoutPackage.class)
public class CaseTabEditSizeSType extends STypeComposite<SIComposite> {

    public STypeInformacoesProfissionais informacoes;
    public STypeAddress endereco;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        informacoes = this.addField("informacoes", STypeInformacoesProfissionais.class);
        endereco = this.addField("endereco", STypeAddress.class);

        SViewTab tabbed = new SViewTab();
        tabbed.addTab(informacoes,"Informações profissionais");
        tabbed.addTab(endereco,"Endereço da empresa");
        //@destacar
        tabbed.navColPreference(2);
        this.withView(tabbed);
    }
}
