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

package org.opensingular.singular.form.showcase.dao.form.studio;

import org.opensingular.form.SDictionary;
import org.opensingular.form.SFormUtil;
import org.opensingular.form.SType;
import org.opensingular.form.spring.SpringTypeLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ShowcaseStudioTypeLoader extends SpringTypeLoader<Class<SType<?>>> {

    private Map<String, SDictionary> dictionaries = new HashMap<>();

    @Override
    protected Optional<SType<?>> loadTypeImpl(Class<SType<?>> typeClass) {
        String packageName = SFormUtil.getTypePackageName(typeClass);
        String typeName = SFormUtil.getTypeName(typeClass);

        SDictionary d = dictionaries.get(packageName);
        if (d == null) {
            d = SDictionary.create();
            dictionaries.put(packageName, d);
        }
        return Optional.of(d.getType(typeName));
    }
}
