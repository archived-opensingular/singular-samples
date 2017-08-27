package org.opensingular.samples.studio.cfg;

import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.sample.studio.form.residuo.*;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.menu.StudioMenuItem;
import org.opensingular.studio.core.menu.GroupMenuEntry;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        StudioMenu menu = new StudioMenu();
        GroupMenuEntry toxicologia = menu.add(new GroupMenuEntry(Icon.of("fa fa-bug"), "Toxicologia"));
        toxicologia.add(new StudioMenuItem(Icon.of("fa fa-folder"), "Cultura", new CulturaStudioDefinition()));
        toxicologia.add(new StudioMenuItem(Icon.of("fa fa-folder"), "Modalidade de Emprego", new ModalidadeDeEmpregoStudioDefinition()));
        toxicologia.add(new StudioMenuItem(Icon.of("fa fa-folder"), "Norma", new NormaStudioDefinition()));
        toxicologia.add(new StudioMenuItem(Icon.of("fa fa-folder"), "Tipo de Dose", new TipoDoseStudioDefinition()));
        toxicologia.add(new StudioMenuItem(Icon.of("fa fa-folder"), "Estudo de Residuo ", new EstudoResiduoStudioDefinition()));
        return menu;
    }

    @Override
    public Class<?> getSpringConfig() {
        return StudioSampleSpringConfig.class;
    }
}