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

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.validation.InstanceValidatable;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

import javax.annotation.Nonnull;

/**
 * Between Fields
 */
@CaseItem(componentName = "Between Fields", group = Group.VALIDATION, resources = @Resource(CaseValidationPackage.class))
@SInfoType(spackage = CaseValidationPackage.class, name = "BetweenFields")
public class CaseValidationBetweenFieldsSType extends STypeComposite<SIComposite> {

    public STypeInteger valorInicial;
    public STypeInteger valorFinal;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        valorInicial = this.addFieldInteger("valorInicial");
        valorFinal = this.addFieldInteger("valorFinal");

        valorInicial.asAtr().required().label("Valor Inicial");
        valorFinal.asAtr().required().label("Valor Final");

        //@destacar
        this.addInstanceValidator(this::verificaMenorMaior);
    }

    private void verificaMenorMaior(InstanceValidatable<SIComposite> validator) {
        SIComposite myForm = validator.getInstance();

        int mivFinal = myForm.findNearestOrException(valorFinal).getInteger();
        int mivInicial = myForm.findNearestOrException(valorInicial).getInteger();

        if (mivFinal <= mivInicial) {
            validator.error("O valor do campo final deve ser maior que o valor do campo inicial");
        }
    }
}
