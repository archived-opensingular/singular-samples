/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.form.wicket.component;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.model.IModel;

import org.opensingular.singular.form.wicket.feedback.SFeedbackPanel;
import org.opensingular.singular.util.wicket.feedback.BSFeedbackPanel;
import org.opensingular.singular.util.wicket.modal.BSModalBorder;

public class BFModalBorder extends BSModalBorder {

    public BFModalBorder(String id, IModel<?> model) {
        super(id, model);
    }
    public BFModalBorder(String id) {
        super(id);
    }

    @Override
    protected BSFeedbackPanel newFeedbackPanel(String id, BSModalBorder fence, IFeedbackMessageFilter messageFilter) {
        return new SFeedbackPanel(id, fence, messageFilter);
    }
}