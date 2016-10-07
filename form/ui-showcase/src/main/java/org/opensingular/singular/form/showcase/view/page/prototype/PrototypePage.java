/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.form.showcase.view.page.prototype;

import org.opensingular.singular.form.showcase.view.template.Template;
import org.opensingular.singular.form.showcase.view.template.Content;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("prototype/edit")
public class PrototypePage extends Template {
    protected static String ID = "id";

    @Override
    protected Content getContent(String id) {
        StringValue idValue = getPageParameters().get(ID);
        return new PrototypeContent(id, idValue);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("$('#_menuItemPrototype').addClass('active');"));
    }
}
