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

package org.opensingular.singular.form.showcase.component.form.custom.inject;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * É possivel passar dados para o formulario a partir de Injeção de Beans.
 */
@CaseItem(componentName = "Inject",subCaseName = "Default", group = Group.CUSTOM,
        resources = {@Resource(value = CaseCustomInjectService.class), @Resource(value = CaseCustomPackage.class)})
@SInfoType(spackage = CaseCustomPackage.class, name = "CaseInject")
public class CaseCustomInjectStype extends STypeComposite<SIComposite> {

    //@destacar:bloco
    @Inject
    private CaseCustomInjectService customInjectService;
    //@destacar:fim

    public STypeString injectOptions;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        injectOptions = this.addFieldString("injectOptions");

        injectOptions.selectionOf(customInjectService.fruitsOptions());

        this.asAtr().label("Escolha uma fruta");
    }
}
