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

package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(name = "UF", spackage = SPackageStudioPersistenceForm.class)
public class STypeUF extends STypeComposite<SIComposite> {

    public STypeString sigla;
    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        createType();
        this.selection().id(sigla).display(nome).simpleProvider(b -> b
                .add()
                .set(sigla, "DF")
                .set(nome, "Distrito Federal")
                .add()
                .set(sigla, "AM")
                .set(nome, "Amazonas")
        );
    }

    private void createType() {
        sigla = addField("sigla", STypeString.class);
        nome = addField("nome", STypeString.class);
    }

}
