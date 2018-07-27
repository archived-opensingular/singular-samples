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

package org.opensingular.singular.form.showcase.component.form.layout.tab;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypeInformacoesPessoais extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeCPF cpf;
    public STypeTelefoneNacional telefone;
    public STypeEMail email;
    public STypeAddress endereco;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        cpf = this.addField("cpf", STypeCPF.class);
        telefone = this.addField("telefone", STypeTelefoneNacional.class);
        email = this.addField("email", STypeEMail.class);
        endereco = this.addField("endereco", STypeAddress.class);

        nome
                .asAtr().label("Nome")
                .asAtrBootstrap().colPreference(6);

        cpf.asAtrBootstrap().colPreference(3);
        telefone.asAtrBootstrap().colPreference(3);
        email.asAtrBootstrap().colPreference(3);

        //@destacar:bloco
        this.withView(new SViewByBlock(), blockView->
                blockView
                        .newBlock().add(nome).add(cpf)
                        .newBlock("Contato").add(telefone).add(email)
                        .newBlock("Endereço").add(endereco)
        );
        //@destacar:fim
    }
}
