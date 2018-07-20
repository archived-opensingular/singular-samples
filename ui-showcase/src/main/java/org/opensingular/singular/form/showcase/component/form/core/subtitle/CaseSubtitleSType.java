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

package org.opensingular.singular.form.showcase.component.form.core.subtitle;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewByBlock;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

/**
 * Subtitle - Exemplo de como utilizar nos agrupadores (StypeComposite) e fields.
 */
/*hidden*/@CaseItem(componentName = "Subtitle", group = Group.INPUT, resources = {@Resource(STypeDadosPessoais.class),
/*hidden*/        @Resource(STypeDadosProfissionais.class), @Resource(CaseInputCorePackage.class)})
@SInfoType(spackage = CaseInputCorePackage.class, name = "Subtitle")
public class CaseSubtitleSType extends STypeComposite<SIComposite> {

    public STypeDadosPessoais dadosPessoais;
    public STypeDadosProfissionais dadosProfissionais;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {

        dadosPessoais = this.addField("dadosPessoais", STypeDadosPessoais.class);
        dadosProfissionais = this.addField("dadosProfissionais", STypeDadosProfissionais.class);

        this.withView(new SViewByBlock(), v ->
                v.newBlock("Ficha de Cadastro").add(dadosPessoais)
                 .newBlock("Ficha de Cadastro").add(dadosProfissionais));
    }
}
