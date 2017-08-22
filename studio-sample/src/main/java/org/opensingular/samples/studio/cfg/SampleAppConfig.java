package org.opensingular.samples.studio.cfg;

import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.studio.app.StudioAppConfig;
import org.opensingular.studio.core.menu.GroupMenuEntry;
import org.opensingular.studio.core.menu.ItemMenuEntry;
import org.opensingular.studio.core.menu.StudioMenu;
import org.springframework.context.annotation.Bean;

public class SampleAppConfig extends StudioAppConfig {
    @Bean
    @Override
    public StudioMenu menu() {
        StudioMenu menu = new StudioMenu();
        GroupMenuEntry groupOne = menu.add(new GroupMenuEntry(DefaultIcons.GLOBE, "Corporativo"));
        groupOne.add(new ItemMenuEntry(DefaultIcons.CHECK, "Representante", "/corporativo/representante"));
        GroupMenuEntry groupOneDotOne = groupOne.add(new GroupMenuEntry(DefaultIcons.CHECK, "Pessoa"));
        groupOneDotOne.add(new ItemMenuEntry(DefaultIcons.CHECK, "Fisica", "/corporativo/pessoa/fisica"));
        groupOneDotOne.add(new ItemMenuEntry(DefaultIcons.CHECK, "Juridica", "/corporativo/pessoa/juridica"));
        menu.add(new ItemMenuEntry(DefaultIcons.CHECK, "Permissão", "/permissao"));
        return menu;
    }
}
