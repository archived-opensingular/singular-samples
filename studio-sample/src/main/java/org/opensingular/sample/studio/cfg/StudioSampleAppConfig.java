package org.opensingular.sample.studio.cfg;

import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.sample.studio.form.residuo.*;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.menu.StudioMenuItem;
import org.opensingular.studio.app.spring.StudioSpringConfiguration;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        StudioMenu menu = new StudioMenu();
        menu.add(new StudioMenuItem(Icon.of("fa fa-leaf"), "Cultura", new CulturaStudioDefinition()));
        menu.add(new StudioMenuItem(Icon.of("fa fa-book"), "Modalidade de Emprego", new ModalidadeDeEmpregoStudioDefinition()));
        menu.add(new StudioMenuItem(Icon.of("fa fa-legal"), "Norma", new NormaStudioDefinition()));
        menu.add(new StudioMenuItem(Icon.of("fa fa-eyedropper"), "Tipo de Dose", new TipoDoseStudioDefinition()));
        menu.add(new StudioMenuItem(Icon.of("fa fa-flask"), "Estudo de Residuo ", new EstudoResiduoStudioDefinition()));
        return menu;
    }

    @Override
    public Class<? extends StudioSpringConfiguration> getSpringConfig() {
        return StudioSampleSpringConfig.class;
    }
}