package org.opensingular.requirementsamplemodule;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.module.config.workspace.WorkspaceMenuItem;

public class SampleMenuItem implements WorkspaceMenuItem {
    @Override
    public Panel newContent(String id) {
        return new EmptyPanel(id);
    }

    @Override
    public Icon getIcon() {
        return DefaultIcons.ROCKET;
    }

    @Override
    public String getName() {
        return "Painel Vazio";
    }

    @Override
    public String getDescription() {
        return "Um painel vazio simples";
    }

    @Override
    public String getHelpText() {
        return "Use como exemplo para criar itens avulsos no menu";
    }
}
