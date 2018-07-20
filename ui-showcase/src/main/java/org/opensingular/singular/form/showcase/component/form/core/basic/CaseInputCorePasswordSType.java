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

package org.opensingular.singular.form.showcase.component.form.core.basic;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypePassword;
import org.opensingular.form.view.SViewPassword;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

/**
 * Campos básicos para uso nos formulários do singular
 */
/*hidden*/@CaseItem(componentName = "Basic", subCaseName = "Password",  group = Group.INPUT, resources = @Resource(CaseInputCorePackage.class))
@SInfoType(spackage = CaseInputCorePackage.class, name = "Password")
public class CaseInputCorePasswordSType extends STypeComposite<SIComposite> {

    public STypePassword senha;
    public STypePassword senhaResetada;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        //Por default a senha é resetada a cada request
        senhaResetada = this.addFieldPassword("senhaResetada");
        senha = this.addFieldPassword("senha");

        senhaResetada
                .asAtr().label("Senha Resetada").subtitle("Resetada após requisição");

        //Configurando view para não resetar a request após a requisição
        senha.withView(new SViewPassword().setResetPassword(false))
                .asAtr().label("Senha");
    }
}
