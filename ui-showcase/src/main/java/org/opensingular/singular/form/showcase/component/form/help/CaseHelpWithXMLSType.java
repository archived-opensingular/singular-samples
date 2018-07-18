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

package org.opensingular.singular.form.showcase.component.form.help;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewListByForm;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

import javax.annotation.Nonnull;

/**
 * É possivel colocar as informações do help em um arquivo XML
 */
@CaseItem(componentName = "Help", subCaseName = "Help with XML", group = Group.HELP,
        resources = {@Resource(value = CaseHelpWithXMLSType.class, extension = "xml"), @Resource(STypeExperiencia.class)})
@SInfoType(spackage = CaseHelpPackage.class, name = "HelpWithXML")
public class CaseHelpWithXMLSType extends STypeComposite<SIComposite> {
    public STypeList<STypeExperiencia, SIComposite> experienciasProfissionais;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        experienciasProfissionais = this.addFieldListOf("experienciasProfissionais", STypeExperiencia.class);

        experienciasProfissionais
                .withView(SViewListByForm::new)
                .asAtr().label("Experiências profissionais");
    }
}
