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

import org.opensingular.form.provider.SimpleProvider;
import org.opensingular.singular.form.showcase.component.form.core.select.form.SIIngredienteQuimico;

import java.util.Arrays;
import java.util.List;

public class IngredienteQuimicoFilteredProvider implements SimpleProvider<IngredienteQuimico, SIIngredienteQuimico> {

    private final List<IngredienteQuimico> ingredientes =
            Arrays.asList(
                    new IngredienteQuimico("Água", "H2O"),
                    new IngredienteQuimico("Água Oxigenada", "H2O2"),
                    new IngredienteQuimico("Gás Oxigênio", "O2"),
                    new IngredienteQuimico("Açúcar", "C12H22O11"));

    @Override
    public List<IngredienteQuimico> load(SIIngredienteQuimico ins) {
        return ingredientes;
    }

}