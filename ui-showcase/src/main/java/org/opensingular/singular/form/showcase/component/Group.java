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

package org.opensingular.singular.form.showcase.component;

import org.opensingular.lib.wicket.util.resource.Icone;
import org.opensingular.lib.wicket.util.resource.SingularIcon;

public enum Group {
    INPUT("Input", Icone.PUZZLE, ShowCaseType.FORM),
    COUNTRY("Location", Icone.COUNTRY, ShowCaseType.FORM),
    FILE("File", Icone.FOLDER, ShowCaseType.FORM),
    LAYOUT("Layout", Icone.GRID, ShowCaseType.FORM),
    VALIDATION("Validation", Icone.BAN, ShowCaseType.FORM),
    INTERACTION("Interaction", Icone.ROCKET, ShowCaseType.FORM),
    CUSTOM("Custom", Icone.WRENCH, ShowCaseType.FORM),
    MAPS("Maps", Icone.MAP, ShowCaseType.FORM),
    STUDIO_SAMPLES("Samples", Icone.DOCS, ShowCaseType.STUDIO),
    IMPORTER("Importer", Icone.CODE, ShowCaseType.FORM);

    private final String       name;
    private final SingularIcon singularIcon;
    private final ShowCaseType tipo;

    Group(String name, SingularIcon singularIcon, ShowCaseType tipo) {

        this.name = name;
        this.singularIcon = singularIcon;
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public SingularIcon getIcone() {
        return singularIcon;
    }

    public ShowCaseType getTipo() {
        return tipo;
    }
}
