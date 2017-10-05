package org.opensingular.sample.studio.cfg;

import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.sample.studio.definition.*;
import org.opensingular.sample.studio.wicket.WicketStudioContent;
import org.opensingular.studio.app.config.AbstractStudioAppConfig;
import org.opensingular.studio.app.spring.StudioPersistenceConfiguration;
import org.opensingular.studio.app.spring.StudioSpringConfiguration;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        return StudioMenu.Builder.newPortalMenu()
                .addSidebarGroup(Icon.of("fa fa-flask"), "Toxicologia", toxicologia -> toxicologia
                        .addStudioItem("Cultura", new CulturaStudioDefinition())
                        .addStudioItem("Modalidade de Emprego", new ModalidadeDeEmpregoStudioDefinition())
                        .addStudioItem("Norma", new NormaStudioDefinition())
                        .addStudioItem("Tipo de Dose", new TipoDoseStudioDefinition())
                        .addStudioItem("Estudo de Residuo", new EstudoResiduoStudioDefinition())
                        .addStudioItemWithMenu("Wicket Panel(with menu)", WicketStudioContent::new)
                        .addStudioItemWithoutMenu("Wicket Panel(without menu)", WicketStudioContent::new)
                        .addHTTPEndpoint(DefaultIcons.SEARCH, "Wikipédia", "http://wikipedia.org/"))
                .addSidebarGroup(DefaultIcons.PENCIL, "Favoritos (Sidebar)", favoritos -> favoritos
                        .addHTTPEndpoint(DefaultIcons.GLOBE, "Globo", "http://globo.com/")
                        .addHTTPEndpoint(DefaultIcons.SEARCH, "Google", "http://google.com/")
                        .addHTTPEndpoint(Icon.of("fa fa-book"), "Wikipédia", "http://wikipedia.org/")
                        .addHTTPEndpoint(DefaultIcons.DIRECTIONS, "Reddit", "http://reddit.com/"))
                .addPortalGroup(DefaultIcons.PENCIL, "Favoritos (Portal)", favoritos -> favoritos
                        .addHTTPEndpoint(DefaultIcons.GLOBE, "Globo", "http://globo.com/")
                        .addHTTPEndpoint(DefaultIcons.SEARCH, "Google", "http://google.com/")
                        .addHTTPEndpoint(Icon.of("fa fa-book"), "Wikipédia", "http://wikipedia.org/")
                        .addHTTPEndpoint(DefaultIcons.DIRECTIONS, "Reddit", "http://reddit.com/"))
                .addHTTPEndpoint(DefaultIcons.ROCKET, "Google", "https://www.google.com/")
                .getStudioMenu();
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