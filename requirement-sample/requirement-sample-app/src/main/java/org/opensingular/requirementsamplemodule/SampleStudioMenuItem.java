package org.opensingular.requirementsamplemodule;

import org.apache.wicket.markup.html.panel.Panel;
import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.config.workspace.WorkspaceMenuItem;
import org.opensingular.requirementsamplemodule.config.StudioRequirementConfig;
import org.opensingular.studio.core.panel.CrudShellPanel;

public class SampleStudioMenuItem implements WorkspaceMenuItem {
    private StudioRequirementConfig.EngenheiroFormDefinition definition = new StudioRequirementConfig.EngenheiroFormDefinition();

    @Override
    public Panel newContent(String id) {
        return new CrudShellPanel(id, definition);
    }

    @Override
    public Icon getIcon() {
        return DefaultIcons.USERS3;
    }

    @Override
    public String getName() {
        return definition.getTitle();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getHelpText() {
        return null;
    }
}
