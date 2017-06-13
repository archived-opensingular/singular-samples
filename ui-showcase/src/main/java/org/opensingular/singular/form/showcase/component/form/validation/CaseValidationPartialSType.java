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

package org.opensingular.singular.form.showcase.component.form.validation;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SPackage;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

import javax.annotation.Nonnull;

/**
 * É possível validar somente uma parte do formulário, no exemplo a seguir somente o campo "Obrigatório 1" será validado ao acionar a validação parcial.
 */
@CaseItem(componentName = "Partial", group = Group.VALIDATION,
    resources = @Resource(PartialValidationButton.class), customizer = CaseValidationPartialCustomizer.class)
@SInfoType(spackage = CaseValidationPackage.class, name = "Partial")
public class CaseValidationPartialSType extends STypeComposite<SIComposite> {

    public STypeString obrigatorio1;
    public STypeInteger obrigatorio2;
    public STypeString obrigatorio3;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        //@destacar
        obrigatorio1 = this.addFieldString("obrigatorio1");
        obrigatorio1
                .asAtr().label("Obrigatorio 1")
                .asAtr().required();

        obrigatorio2 = this.addFieldInteger("obrigatorio2");
        obrigatorio2
                .asAtr().label("Obrigatorio 2")
                .asAtr().required();

        obrigatorio3 = this.addFieldString("obrigatorio3");
        obrigatorio3
                .asAtr().label("Obrigatorio 3")
                .asAtr().required();

    }
}
