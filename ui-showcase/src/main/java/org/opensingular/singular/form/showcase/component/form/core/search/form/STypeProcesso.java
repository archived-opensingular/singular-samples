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
package org.opensingular.singular.form.showcase.component.form.core.search.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.converter.ValueToSICompositeConverter;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewTree;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;
import org.opensingular.singular.form.showcase.component.form.core.search.Processo;
import org.opensingular.singular.form.showcase.component.form.core.search.ProcessoProvider;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class, name = "Processo")
public class STypeProcesso extends STypeComposite<SIComposite> {

    public STypeString id;
    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {

        this.addFieldString("id");
        this.addFieldString("nome");

        // @destacar
        this.withView(new SViewTree()
                .setTitle("Buscar Processos"))
                .asAtrProvider()
                // @destacar
                .idFunction(Processo::getId)
                .displayFunction(Processo::getNome)
                .treeProvider(new ProcessoProvider())
                .converter((ValueToSICompositeConverter<Processo>) (newProc, processo) -> {
                    newProc.setValue(id, processo.getId());
                    newProc.setValue(nome, processo.getNome());
                });
    }
}
