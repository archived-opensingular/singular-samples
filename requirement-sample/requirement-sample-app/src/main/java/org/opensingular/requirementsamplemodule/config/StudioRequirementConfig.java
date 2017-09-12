package org.opensingular.requirementsamplemodule.config;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.studio.core.config.StudioConfig;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioRequirementConfig implements StudioConfig {

    @Override
    public StudioMenu getAppMenu() {
        return StudioMenu.Builder.newPortalMenu()
                .addHTTPEndpoint(DefaultIcons.GLOBE, "Requerimento", "/requirement")
                .addHTTPEndpoint(DefaultIcons.COMMENT, "Análise", "/worklist")
                .addSidebarGroup(DefaultIcons.MAGIC, "Cadastros", cadastros -> {
                    cadastros.addStudioItem("Engenheiros", new EngenheiroFormDefinition());
                })
                .getStudioMenu();
    }


    public static class EngenheiroFormDefinition implements StudioDefinition {
        @Override
        public String getRepositoryBeanName() {
            return "engenheiroRepository";
        }

        @Override
        public void configureDatatableColumns(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
            dataTableBuilder.appendPropertyColumn("Nome", i -> i.getValue("nome"));
        }

        @Override
        public String getTitle() {
            return "Cadastro de Engenheiros";
        }
    }
}