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

package org.opensingular.singular.form.showcase.component.form.core.search.form;

import org.opensingular.form.converter.SInstanceConverter;

public class SIFuncionarioConverter implements SInstanceConverter<Funcionario, SIFuncionario> {
    @Override
    public void fillInstance(SIFuncionario siFuncionario, Funcionario funcionario) {
        siFuncionario.nome().setValue(funcionario.getNome());
        siFuncionario.funcao().setValue(funcionario.getFuncao());
        siFuncionario.idade().setValue(funcionario.getIdade());
    }

    @Override
    public Funcionario toObject(SIFuncionario siFuncionario) {
        return new Funcionario(siFuncionario.nome().getValue(), siFuncionario.funcao().getValue(),
                siFuncionario.idade().getValue());
    }
}
