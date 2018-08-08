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

package org.opensingular.requirementsamplemodule.extension;

import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.wicket.view.extension.RequirementButtonExtension;

/**
 * A Example of the RequirementButtonExtension extension that says Hello World
 */
public class HelloWorldRequirementButtonExtension implements RequirementButtonExtension {

    @Override
    public ButtonView getButtonView() {
        return new ButtonView("Hello World", DefaultIcons.COMMENT);
    }

    @Override
    public void onAction(RequirementButtonExtension.ButtonExtensionActionContext actionContext) {
        actionContext.getAjaxRequestTarget().appendJavaScript("alert('Hello World');");
    }
}