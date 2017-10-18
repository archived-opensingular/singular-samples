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

package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.document.SDocument;
import org.opensingular.form.type.ref.STypeRef;
import org.opensingular.sample.studio.repository.ModalidadeEmpregoRepository;

import javax.inject.Inject;
import java.util.List;

@SInfoType(name = "ModalidadeEmpregoRef", spackage = ResiduoPackage.class)
public class ModalidadeEmpregoRef extends STypeRef<SIComposite> {
    @Inject
    private ModalidadeEmpregoRepository modalidadeEmpregoRepository;

    @Override
    protected String getKeyValue(SIComposite instance) {
        return instance.getValue(ModalidadeDeEmprego.class, c -> c.nome);
    }

    @Override
    protected String getDisplayValue(SIComposite instance) {
        return instance.getValue(ModalidadeDeEmprego.class, c -> c.nome);
    }

    @Override
    protected List<SIComposite> loadValues(SDocument document) {
        return modalidadeEmpregoRepository.loadAll();
    }
}
