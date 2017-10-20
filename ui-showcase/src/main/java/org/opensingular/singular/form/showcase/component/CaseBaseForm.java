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

import org.opensingular.form.SDictionary;
import org.opensingular.form.SFormUtil;
import org.opensingular.form.SType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.SingularFormException;
import org.opensingular.form.wicket.enums.AnnotationMode;

import java.util.Optional;


public class CaseBaseForm extends CaseBase {

    private transient SType<?> caseType;

    public CaseBaseForm(Class<? extends STypeComposite<?>> caseClass, String componentName, String subCaseName, AnnotationMode annotation) {
        super(caseClass, ShowCaseType.FORM, componentName, subCaseName, annotation);
    }

    public CaseBaseForm(String componentName) {
        super(componentName);
    }

    public CaseBaseForm(String componentName, String subCaseName) {
        super(componentName, subCaseName);
    }

    @SuppressWarnings("unchecked")
    private Class<? extends STypeComposite<?>> getSTypeClass() {
        return caseClass;
    }


    public String getTypeName() {
        return SFormUtil.getTypeName(getSTypeClass());
    }

    public SType<?> getCaseType() {
        if (caseType == null) {
            SDictionary dictionary = SDictionary.create();

            caseType = dictionary.getType(getSTypeClass());
            if (caseType == null){
                throw new SingularFormException("O SType " + getSTypeClass().getName() + " não foi encontrado.");
            }
        }
        return caseType;
    }


    @Override
    public Optional<ResourceRef> getMainSourceResourceName() {
        return ResourceRef.forSource(getSTypeClass());
    }


    public boolean showValidateButton() {
        return getCaseType().hasAnyValidation();
    }


}
