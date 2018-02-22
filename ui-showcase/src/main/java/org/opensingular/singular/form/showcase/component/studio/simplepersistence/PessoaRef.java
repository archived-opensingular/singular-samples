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

package org.opensingular.singular.form.showcase.component.studio.simplepersistence;

import java.util.List;

import javax.inject.Inject;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.document.SDocument;
import org.opensingular.form.persistence.FormKeyRelational;
import org.opensingular.form.type.ref.STypeRef;

@SInfoType(name = "PessoaRef", spackage = DemandaPackage.class)
public class PessoaRef extends STypeRef<SIComposite> {
    @Inject
    private PessoaRepository pessoaRepository;

    @Override
    protected String getKeyValue(SIComposite instance) {
        return FormKeyRelational.columnValuefromInstance("ID", instance).toString();
    }

    @Override
    protected String getDisplayValue(SIComposite instance) {
        return instance.getValue(STypePessoa.class, c -> c.nome);
    }

    @Override
    protected List<SIComposite> loadValues(SDocument document) {
        return pessoaRepository.loadAll();
    }

    public void bindForeignColumn(String keyColumn) {
        // relational mapping
        key.asSQL().column(keyColumn);
        display.asSQL().foreignColumn("NOME", keyColumn, STypePessoa.class);
    }
}
