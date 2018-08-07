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

package org.opensingular.singular.form.showcase.component.form.layout.block;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

/**
 * Block
 */
@CaseItem(componentName = "Block", subCaseName = "Default", group = Group.LAYOUT, resources = @Resource(CaseLayoutPackage.class))
@SInfoType(spackage = CaseLayoutPackage.class, name = "DefaultBlock")
public class CaseBlockSType extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeCPF cpf;
    public STypeDate dataNascimento;
    public STypeEMail email;
    public STypeTelefoneNacional telefone;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        cpf = this.addField("cpf", STypeCPF.class);
        dataNascimento = this.addFieldDate("dataNascimento");
        email = this.addField("email", STypeEMail.class);
        telefone = this.addField("telefone", STypeTelefoneNacional.class);

        nome
                .asAtr().label("Nome completo")
                .asAtrBootstrap().colPreference(6);

        cpf.asAtrBootstrap().colPreference(3);

        dataNascimento
                .asAtr().label("Data de nascimento");

        email.asAtrBootstrap().colPreference(3);

        telefone.asAtrBootstrap().colPreference(2);

        //@destacar:bloco
        SViewByBlock viewByBlock = new SViewByBlock();
        viewByBlock
                .newBlock("Dados Pessoais").add(nome).add(cpf).add(dataNascimento)
                .newBlock("Contato").add(email).add(telefone);
        this.withView(viewByBlock);
        //@destacar:fim
    }
}
