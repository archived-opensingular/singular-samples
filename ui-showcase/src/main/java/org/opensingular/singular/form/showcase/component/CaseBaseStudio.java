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

import org.opensingular.form.wicket.enums.AnnotationMode;
import org.opensingular.lib.commons.base.SingularException;
import org.opensingular.studio.core.definition.StudioDefinition;

public class CaseBaseStudio extends CaseBase {

    public CaseBaseStudio(Class<? extends StudioDefinition> caseClass, String componentName, String subCaseName, AnnotationMode annotation) {
        super(caseClass, ShowCaseType.STUDIO, componentName, subCaseName, annotation);
    }


    public StudioDefinition getStudioDefinition() {
        try {
            return (StudioDefinition) caseClass.newInstance();
        } catch (Exception e) {
            throw SingularException.rethrow(e.getMessage(), e);
        }
    }
}
