/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.wicket.component;

import org.opensingular.form.SInstance;
import org.opensingular.form.wicket.util.WicketFormProcessing;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import java.util.Optional;

public abstract class SingularValidationButton extends SingularButton {

    public SingularValidationButton(String id, IModel<? extends SInstance> currentInstance) {
        super(id, currentInstance);
    }

    protected abstract void onValidationSuccess(AjaxRequestTarget target, Form<?> form, IModel<? extends SInstance>  instanceModel);
    protected void onValidationError(AjaxRequestTarget target, Form<?> form, IModel<? extends SInstance> instanceModel) {}

    @Override
    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        super.onSubmit(target, form);
        if (WicketFormProcessing.onFormSubmit(form, Optional.of(target), getCurrentInstance(), true)) {
            onValidationSuccess(target, form, getCurrentInstance());
        } else {
            onValidationError(target, form, getCurrentInstance());
        }
        target.add(form);
    }
    
    @Override
    protected boolean isShouldProcessFormSubmitWithoutValidation() {
        return false;
    }

}
