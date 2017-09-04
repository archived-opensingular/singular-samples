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

import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.lib.commons.ui.Icon;

public enum Group {
    //@formatter:off
    INPUT           ("Input"        , DefaultIcons.PUZZLE   , ShowCaseType.FORM),
    COUNTRY         ("Location"     , DefaultIcons.COUNTRY  , ShowCaseType.FORM),
    FILE            ("File"         , DefaultIcons.FOLDER   , ShowCaseType.FORM),
    LAYOUT          ("Layout"       , DefaultIcons.GRID     , ShowCaseType.FORM),
    VALIDATION      ("Validation"   , DefaultIcons.BAN      , ShowCaseType.FORM),
    INTERACTION     ("Interaction"  , DefaultIcons.ROCKET   , ShowCaseType.FORM),
    HELP            ("Help"         , DefaultIcons.HELP     , ShowCaseType.FORM),
    CUSTOM          ("Custom"       , DefaultIcons.WRENCH   , ShowCaseType.FORM),
    MAPS            ("Maps"         , DefaultIcons.MAP      , ShowCaseType.FORM),
    STUDIO_SAMPLES  ("Samples"      , DefaultIcons.DOCS     , ShowCaseType.STUDIO),
    IMPORTER        ("Importer"     , DefaultIcons.CODE     , ShowCaseType.FORM),
    //@formatter:on
    ;

    private final String       name;
    private final Icon         icon;
    private final ShowCaseType tipo;

    Group(String name, Icon icon, ShowCaseType tipo) {

        this.name = name;
        this.icon = icon;
        this.tipo = tipo;
    }

    public String getName() {
        return name;
    }

    public Icon getIcone() {
        return icon;
    }

    public ShowCaseType getTipo() {
        return tipo;
    }
}
