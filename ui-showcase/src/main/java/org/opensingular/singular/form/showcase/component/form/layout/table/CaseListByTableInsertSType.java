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

package org.opensingular.singular.form.showcase.component.form.layout.table;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewListByTable;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;
import org.opensingular.singular.form.showcase.component.form.layout.stypes.STypeCertificacao;

import javax.annotation.Nonnull;

/**
 * List by Table with Insert Option
 */
/*hidden*/@CaseItem(componentName = "List by Table", subCaseName = "Line Insert", group = Group.LAYOUT, resources = {@Resource(STypeCertificacao.class)})
@SInfoType(spackage = CaseLayoutPackage.class, name = "DefaultTable")
public class CaseListByTableInsertSType extends STypeComposite<SIComposite> {

    public STypeList<STypeCertificacao, SIComposite> certificacoes;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        certificacoes = this.addFieldListOf("certificacoes", STypeCertificacao.class);

        certificacoes
                //@destacar
                .withView(new SViewListByTable().enabledInsert())
                .asAtr().label("Certificações");
    }
}
