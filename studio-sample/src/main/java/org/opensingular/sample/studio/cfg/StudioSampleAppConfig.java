/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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