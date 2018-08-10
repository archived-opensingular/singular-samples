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
import org.opensingular.singular.form.showcase.view.page.ItemCasePanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Representa um exemplo de um componente ou solução junto com os respectivo
 * códigos e explicações.
 */
public abstract class CaseBase<TARGET> implements Serializable {

    private String componentName;
    private String subCaseName;
    private String descriptionHtml;
    private final List<ItemCasePanel.ItemCaseButton> botoes = new ArrayList<>();
    private final List<ResourceRef> additionalSources = new ArrayList<>();
    private Class<? extends TARGET> caseClass;
    private AnnotationMode annotationMode = AnnotationMode.NONE;

    private ShowCaseType showCaseType;

    public CaseBase() {}

    public CaseBase(Class<? extends TARGET> caseClass) {
        this.caseClass = caseClass;
    }

    public Class<? extends TARGET> getCaseClass() {
        return caseClass;
    }

    public void setAnnotationMode(AnnotationMode annotationMode) {
        this.annotationMode = annotationMode;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public void setSubCaseName(String subCaseName) {
        this.subCaseName = subCaseName;
    }

    public void setShowCaseType(ShowCaseType showCaseType) {
        this.showCaseType = showCaseType;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getSubCaseName() {
        return subCaseName;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public Optional<String> getDescriptionHtml() {
        if (descriptionHtml != null) {
            return Optional.of(descriptionHtml);
        }
        return getDescriptionResourceName().map(ResourceRef::getContent);
    }

    public Optional<ResourceRef> getDescriptionResourceName() {
        return ResourceRef.forClassWithExtension(getClass(), "html");
    }

    public List<ResourceRef> getAdditionalSources() {
        return additionalSources;
    }

    public List<ItemCasePanel.ItemCaseButton> getBotoes() {
        return botoes;
    }


    public AnnotationMode annotation() {
        return annotationMode;
    }

    public boolean isDynamic() {
        return false;
    }


    public ShowCaseType getShowCaseType() {
        return showCaseType;
    }


    public Optional<ResourceRef> getMainSourceResourceName() {
        return ResourceRef.forSource(caseClass);
    }

}
