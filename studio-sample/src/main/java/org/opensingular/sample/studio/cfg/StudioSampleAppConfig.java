package org.opensingular.sample.studio.cfg;

import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.sample.studio.definition.*;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.menu.StudioMenuItem;
import org.opensingular.studio.app.spring.StudioPersistenceConfiguration;
import org.opensingular.studio.app.spring.StudioSpringConfiguration;
import org.opensingular.studio.core.menu.GroupBuilder;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        GroupBuilder sidebarMenu = new GroupBuilder();
        GroupBuilder toxicologia = sidebarMenu.addGroup(Icon.of("fa fa-flask"), "Toxicologia");
        toxicologia.addItem(new StudioMenuItem("Cultura", new CulturaStudioDefinition()));
        toxicologia.addItem(new StudioMenuItem("Modalidade de Emprego", new ModalidadeDeEmpregoStudioDefinition()));
        toxicologia.addItem(new StudioMenuItem("Norma", new NormaStudioDefinition()));
        toxicologia.addItem(new StudioMenuItem("Tipo de Dose", new TipoDoseStudioDefinition()));
        toxicologia.addItem(new StudioMenuItem("Estudo de Residuo ", new EstudoResiduoStudioDefinition()));
        return sidebarMenu.build();
    }

    @Override
    public Class<? extends StudioSpringConfiguration> getSpringConfig() {
        return StudioSampleSpringConfig.class;
    }

    @Override
    public Class<? extends StudioPersistenceConfiguration> getSpringPersistenceConfig() {
        return StudioSampleSpringPersistenceConfig.class;
    }
}