/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.lib.wicket.util.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;

import org.opensingular.lib.wicket.util.bootstrap.layout.TemplatePanel;

public class FormPanel extends TemplatePanel {

    private static final String ID_FORM_BODY = "formBody";

    public FormPanel(String id, Form<?> form) {
        super(id, () -> "<form wicket:id='" + form.getId() + "'><div wicket:id='" + ID_FORM_BODY + "'></div><wicket:child/></form>");
        add(form
            .add(newFormBody(ID_FORM_BODY)));
    }

    protected Component newFormBody(String id) {
        return new WebMarkupContainer(id).setVisible(false);
    }
}
