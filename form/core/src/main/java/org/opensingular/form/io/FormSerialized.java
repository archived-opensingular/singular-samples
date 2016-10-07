/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.io;

import org.opensingular.form.internal.xml.MElement;
import org.opensingular.form.document.RefSDocumentFactory;
import org.opensingular.form.document.RefType;
import org.opensingular.form.document.ServiceRegistry;
import org.opensingular.form.validation.IValidationError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Objeto transitório para guardar uma versão serializável de MInstance ou
 * MDocument.
 *
 * @author Daniel C. Bordin
 */
public final class FormSerialized implements Serializable {

    private final RefSDocumentFactory         sDocumentFactoryRef;
    private final RefType  refRootType;
    private final String   rootTypeName;
    private final MElement xml, annotations;
    private String                            focusFieldPath;
    private Map<String, ServiceRegistry.Pair> services;
    private List<IValidationError>            validationErrors;

    public FormSerialized(RefType refRootType, String rootTypeName, MElement xml, MElement annotations,
                          RefSDocumentFactory sDocumentFactoryRef) {
        this.refRootType = refRootType;
        this.rootTypeName = rootTypeName;
        this.sDocumentFactoryRef = sDocumentFactoryRef;
        this.xml = xml;
        this.annotations = annotations;
    }

    public String getRootTypeName() {
        return rootTypeName;
    }

    public RefType getRefRootType() {
        return refRootType;
    }

    public String getFocusFieldPath() {
        return focusFieldPath;
    }

    public MElement getAnnotations() {
        return annotations;
    }

    public MElement getXml() {
        return xml;
    }

    public void setFocusFieldPath(String focusFieldPath) {
        this.focusFieldPath = focusFieldPath;
    }

    public Map<String, ServiceRegistry.Pair> getServices() {
        return services;
    }

    public void setServices(Map<String, ServiceRegistry.Pair> services) {
        this.services = services;
    }

    public RefSDocumentFactory getSDocumentFactoryRef() {
        return sDocumentFactoryRef;
    }

    public List<IValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Collection<IValidationError> validationErrors) {
        this.validationErrors = (validationErrors == null) ? null : new ArrayList<>(validationErrors);
    }
}
