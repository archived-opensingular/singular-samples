package org.opensingular.requirementsamplemodule.config;

import org.opensingular.form.persistence.FormRespository;
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
        public Class<? extends FormRespository> getRepositoryClass() {
            return EngenheiroRepository.class;
        }

        @Override
        public void configureStudioDataTable(StudioDataTable studioDataTable) {
            studioDataTable.add("Nome", "nome");
        }

        @Override
        public String getTitle() {
            return "Cadastro de Engenheiros";
        }
    }
}