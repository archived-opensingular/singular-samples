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

package org.opensingular.singular.form.showcase.view.template;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.jetbrains.annotations.NotNull;
import org.opensingular.lib.wicket.util.template.admin.SingularAdminTemplate;

@AuthorizeAction(action = Action.RENDER, roles = Roles.ADMIN)
public abstract class Template extends SingularAdminTemplate {

    private Content content;

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forUrl(commonResource("/plugins/syntaxHighlighter/theme.css")));
        response.render(JavaScriptHeaderItem.forUrl(commonResource("/plugins/syntaxHighlighter/syntaxhighlighter.js")));
        response.render(CssHeaderItem.forReference(new PackageResourceReference(Template.class, "Template.css")));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        addContent();
    }

    public void addContent() {
        content = getContent("content");
        queue(content);
    }

    @Override
    protected boolean isWithMenu() {
        return true;
    }

    protected abstract Content getContent(String id);

    @Override
    protected String getContentTitle() {
        if (content != null) {
            return content.getContentTitleModel().getObject();
        }
        return "";
    }

    @Override
    protected String getContentSubtitle() {
        if (content != null) {
            return content.getContentSubtitleModel().getObject();
        }
        return "";
    }

    @Override
    protected @NotNull WebMarkupContainer buildPageMenu(String id) {
        if (isWithMenu()) {
            return new Menu("menu");
        } else {
            return new WebMarkupContainer("menu");
        }
    }
}
