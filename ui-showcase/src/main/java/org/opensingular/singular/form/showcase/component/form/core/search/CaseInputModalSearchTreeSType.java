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

package org.opensingular.singular.form.showcase.component.form.core.search;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;
import org.opensingular.singular.form.showcase.component.form.core.search.form.Processo;
import org.opensingular.singular.form.showcase.component.form.core.search.form.ProcessoProvider;
import org.opensingular.singular.form.showcase.component.form.core.search.form.ProcessoRepository;
import org.opensingular.singular.form.showcase.component.form.core.search.form.STProcesso;

import javax.annotation.Nonnull;

/**
 * Permite a seleção a partir de uma busca no modo de tree em mémoria
 */
@CaseItem(componentName = "Search Select", subCaseName = "TreeView in Memory", group = Group.INPUT,
        resources = {@Resource(STProcesso.class), @Resource(Processo.class), @Resource(CaseInputCorePackage.class),
                @Resource(ProcessoProvider.class), @Resource(ProcessoRepository.class)})
@SInfoType(spackage = CaseInputCorePackage.class, name = "TreeViewMemory")
public class CaseInputModalSearchTreeSType extends STypeComposite<SIComposite> {

    public STProcesso processo;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        processo = this.addField("processo", STProcesso.class);

        processo.asAtr().label("Processo").displayString("${id} - ${nome}")
                .asAtrBootstrap().colPreference(6);
    }

}
