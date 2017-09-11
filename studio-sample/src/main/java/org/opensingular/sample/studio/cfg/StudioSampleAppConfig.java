package org.opensingular.sample.studio.cfg;

import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.sample.studio.definition.*;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.definition.StudioDefinition;
import org.opensingular.studio.app.menu.*;
import org.opensingular.studio.app.spring.StudioPersistenceConfiguration;
import org.opensingular.studio.app.spring.StudioSpringConfiguration;

import java.util.function.Consumer;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        return SMBuilder.newPortal()
                .addSidebarGroup(Icon.of("fa fa-flask"), "Toxicologia", toxicologia -> toxicologia
                        .addStudioItem("Cultura", new CulturaStudioDefinition())
                        .addStudioItem("Modalidade de Emprego", new ModalidadeDeEmpregoStudioDefinition())
                        .addStudioItem("Norma", new NormaStudioDefinition())
                        .addStudioItem("Tipo de Dose", new TipoDoseStudioDefinition())
                        .addStudioItem("Estudo de Residuo", new EstudoResiduoStudioDefinition())
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


    static class SMBuilder {
        StudioMenu studioMenu;

        public SMBuilder(StudioMenu studioMenu) {
            this.studioMenu = studioMenu;
        }

        SMBuilder addHTTPEndpoint(Icon icon, String name, String endpoint) {
            ItemMenuEntry i = studioMenu.add(new ItemMenuEntry(icon, name, new HTTPEndpointMenuView(endpoint)));
            return this;
        }

        SMBuilder addSidebarGroup(Icon icon, String name, Consumer<GBuilder> groupConsumer) {
            GroupMenuEntry g = studioMenu.add(new GroupMenuEntry(icon, name, new SidebarMenuView()));
            if (groupConsumer != null) {
                groupConsumer.accept(new GBuilder(g));
            }
            return this;
        }

        SMBuilder addPortalGroup(Icon icon, String name, Consumer<GBuilder> groupConsumer) {
            GroupMenuEntry g = studioMenu.add(new GroupMenuEntry(icon, name, new PortalMenuView()));
            if (groupConsumer != null) {
                groupConsumer.accept(new GBuilder(g));
            }
            return this;
        }

        static SMBuilder newPortal() {
            return new SMBuilder(new StudioMenu(new PortalMenuView()));
        }

        public StudioMenu getStudioMenu() {
            return studioMenu;
        }
    }


    static class GBuilder {
        GroupMenuEntry groupEntry;

        public GBuilder(GroupMenuEntry groupEntry) {
            this.groupEntry = groupEntry;
        }

        GBuilder addStudioItem(String name, StudioDefinition definition) {
            groupEntry.add(new ItemMenuEntry(name, new StudioMenuView(definition)));
            return this;
        }

        GBuilder addHTTPEndpoint(Icon ico, String name, String endpoint) {
            groupEntry.add(new ItemMenuEntry(ico, name, new HTTPEndpointMenuView(endpoint)));
            return this;
        }
    }

}