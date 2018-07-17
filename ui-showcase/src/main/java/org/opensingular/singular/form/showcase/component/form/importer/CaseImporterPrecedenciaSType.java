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

package org.opensingular.singular.form.showcase.component.form.importer;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

/**
 * Importador de atributos através de arquivos XML. <br/>
 * Utilizando a precedencia, onde o xml tem maior prioridade entre os demais.
 */

@CaseItem(componentName = "ImporterXML", subCaseName = "Precedência", group = Group.IMPORTER, resources = @Resource(value = CaseImporterPrecedenciaSType.class, extension = "xml") )
@SInfoType(spackage = CaseImporterPackage.class, name = "ImporterXMLPrecedencia")
public class CaseImporterPrecedenciaSType extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeInteger idade;
    public STypeEMail email;
    public STypeString descricao;
    
    
    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        idade = this.addFieldInteger("idade");
        email = this.addFieldEmail("email");
        descricao = this.addFieldString("descricao");

        nome.asAtr().label("Nome (este label não será mostrado, o que tem precedência é o do xml)");
        idade.asAtr().label("idade (este label não será mostrado, o que tem precedência é o do xml)");
        email.asAtr().label("email (este label não será mostrado, o que tem precedência é o do xml)");
        descricao.asAtr().label("descrição (este label não será mostrado, o que tem precedência é o do xml)");
    }
  
}
