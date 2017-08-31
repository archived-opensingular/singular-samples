package org.opensingular.sample.studio.cfg;

import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.sample.studio.definition.*;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.menu.StudioMenuItem;
import org.opensingular.studio.app.spring.StudioPersistenceConfiguration;
import org.opensingular.studio.app.spring.StudioSpringConfiguration;
import org.opensingular.studio.core.menu.GroupMenuEntry;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        StudioMenu menu = new StudioMenu();
        GroupMenuEntry toxicologia = menu.add(new GroupMenuEntry(Icon.of("fa fa-flask"), "Toxicologia"));
        toxicologia.add(new StudioMenuItem(null, "Cultura", new CulturaStudioDefinition()));
        toxicologia.add(new StudioMenuItem(null, "Modalidade de Emprego", new ModalidadeDeEmpregoStudioDefinition()));
        toxicologia.add(new StudioMenuItem(null, "Norma", new NormaStudioDefinition()));
        toxicologia.add(new StudioMenuItem(null, "Tipo de Dose", new TipoDoseStudioDefinition()));
        toxicologia.add(new StudioMenuItem(null, "Estudo de Residuo ", new EstudoResiduoStudioDefinition()));
        return menu;
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