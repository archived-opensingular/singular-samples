package org.opensingular.requirementsamplemodule.extension;

import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.server.commons.wicket.view.extension.RequirementButtonExtension;

/**
 * A Example of the RequirementButtonExtension extension that says Hello World
 */
public class HelloWorldRequirementButtonExtension implements RequirementButtonExtension {

    @Override
    public ButtonView getButtonView() {
        return new ButtonView("Hello World", DefaultIcons.COMMENT);
    }

    @Override
    public void onAction(ActionContext actionContext) {
        actionContext.getAjaxRequestTarget().appendJavaScript("alert('Hello World');");
    }
}