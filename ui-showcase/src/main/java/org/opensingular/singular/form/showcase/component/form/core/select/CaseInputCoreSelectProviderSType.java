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

package org.opensingular.singular.form.showcase.component.form.core.select;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;
import org.opensingular.singular.form.showcase.component.form.core.multiselect.form.STypeArquivo;
import org.opensingular.singular.form.showcase.view.page.form.crud.services.MFileIdsOptionsProvider;

import javax.annotation.Nonnull;

/**
 * É permitido alterar o provedor de dados de forma que estes sejam carregados de forma dinâmica ou de outras fontes de informação.
 */
//@formatter:off
@CaseItem(componentName = "Select", subCaseName = "Provedor Dinâmico", group = Group.INPUT,
            resources = {@Resource(MFileIdsOptionsProvider.class), @Resource(STypeArquivo.class)})
@SInfoType(spackage = CaseInputCorePackage.class, name = "SelectProvider")
public class CaseInputCoreSelectProviderSType extends STypeComposite<SIComposite> {

    public STypeArquivo arquivo;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        /*
         * Neste caso será utilizado o serviço de nome filesChoiceProvider
         * cadastrado através do Document.bindLocalService
         */
        arquivo = this.addField("arquivo", STypeArquivo.class);

        arquivo.asAtr().label("Seleção de Arquivos Persistidos");
        arquivo.selection()
                .id(arquivo.fileName)
                .display(arquivo.fileName)
                .simpleProvider("filesChoiceProvider");
    }

}
