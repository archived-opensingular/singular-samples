package org.opensingular.sample.studio.cfg;

import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.sample.studio.definition.*;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.menu.*;
import org.opensingular.studio.app.spring.StudioPersistenceConfiguration;
import org.opensingular.studio.app.spring.StudioSpringConfiguration;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        StudioMenu menu = new StudioMenu(new PortalMenuView());
        GroupMenuEntry toxicologia = menu.add(new GroupMenuEntry(Icon.of("fa fa-flask"), "Toxicologia", new SidebarMenuView()));
        toxicologia.add(new ItemMenuEntry("Cultura", new StudioMenuView(new CulturaStudioDefinition())));
        toxicologia.add(new ItemMenuEntry("Modalidade de Emprego", new StudioMenuView(new ModalidadeDeEmpregoStudioDefinition())));
        toxicologia.add(new ItemMenuEntry("Norma", new StudioMenuView(new NormaStudioDefinition())));
        toxicologia.add(new ItemMenuEntry("Tipo de Dose", new StudioMenuView(new TipoDoseStudioDefinition())));
        toxicologia.add(new ItemMenuEntry("Estudo de Residuo ", new StudioMenuView(new EstudoResiduoStudioDefinition())));
        toxicologia.add(new ItemMenuEntry(DefaultIcons.SEARCH, "Wikipédia", new SimpleUrlMenuView("https://www.wikipedia.org/")));
        menu.add(new ItemMenuEntry(DefaultIcons.ROCKET, "Google", new SimpleUrlMenuView("https://www.google.com/")));
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