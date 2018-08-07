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

package org.opensingular.singular.form.showcase.component.form.importer;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseImporterPackage.class, name = "endereco")
public class STypeEndereco extends STypeComposite<SIComposite> {
    public STypeString rua;
    public STypeInteger numero;
    public STypeString bairro;
    public STypeString cidade;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        rua = this.addFieldString("rua");
        numero = this.addFieldInteger("numero");
        bairro = this.addFieldString("bairro");
        cidade = this.addFieldString("cidade");
    }
}
