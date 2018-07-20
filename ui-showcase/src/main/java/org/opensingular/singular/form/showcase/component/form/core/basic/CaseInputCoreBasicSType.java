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
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeCEP;
import org.opensingular.form.type.country.brazil.STypeCNPJ;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeEMail;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

/**
 * Campos básicos para uso nos formulários do singular
 */
/*hidden*/@CaseItem(componentName = "Basic", subCaseName = "Default",  group = Group.INPUT, resources = @Resource(CaseInputCorePackage.class))
@SInfoType(spackage = CaseInputCorePackage.class, name = "Basic")
public class CaseInputCoreBasicSType extends STypeComposite<SIComposite> {

    public STypeCNPJ cnpj;
    public STypeCPF cpf;
    public STypeCEP cep;
    public STypeEMail email;
    public STypeString descricao;
    public STypeTelefoneNacional telefone;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        cnpj = this.addField("cnpj", STypeCNPJ.class);
        cpf = this.addField("cpf", STypeCPF.class);
        cep = this.addField("cep", STypeCEP.class);
        email = this.addFieldEmail("email");
        descricao = this.addFieldString("descricao");
        telefone = this.addField("telefone", STypeTelefoneNacional.class);

        descricao.asAtr().label("Descrição");
    }
}
