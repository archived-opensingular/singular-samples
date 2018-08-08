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

package org.opensingular.singular.form.showcase.component.form.interaction;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Valores iniciais para os componentes do formulário.
 */
@CaseItem(componentName = "InitValue", subCaseName = "Init Value", group = Group.INTERACTION,
        resources = @Resource(CaseInteractionPackage.class))
@SInfoType(spackage = CaseInteractionPackage.class, name = "InitValue")
public class CaseInitValueSType extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeInteger idade;
    public STypeDate date;
    public STypeBoolean genero;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        idade = this.addFieldInteger("idade");
        date = this.addFieldDate("date");
        genero = addFieldBoolean("genero");

        nome.asAtr().label("Nome");
      //@destacar
        nome.setInitialValue("João Da Silva");

        idade.asAtr().label("Idade");
      //@destacar
        idade.setInitialValue(32);

        date.asAtr().label("Data");
        //@destacar
        date.setInitialValue(new Date());

        genero.withRadioView("Masculino", "Feminino");
        //@destacar
        genero.setInitialValue(Boolean.TRUE);

        this.asAtr().label("Valores Iniciais");
    }

}
