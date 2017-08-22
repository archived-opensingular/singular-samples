package org.opensingular.samples.studio.cfg;

import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.sample.studiop.form.FerramentaStudioDefinition;
import org.opensingular.sample.studiop.form.PresenteStudioDefinition;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.menu.StudioMenuItem;
import org.opensingular.studio.core.menu.GroupMenuEntry;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {

    @Override
    public StudioMenu getAppMenu() {
        StudioMenu menu = new StudioMenu();
        GroupMenuEntry departamento = menu.add(new GroupMenuEntry(DefaultIcons.BRIEFCASE, "Departamento"));
        departamento.add(new StudioMenuItem(DefaultIcons.GIFT, "Presentes", new PresenteStudioDefinition()));
        departamento.add(new StudioMenuItem(DefaultIcons.WRENCH, "Ferramentas", new FerramentaStudioDefinition()));
        return menu;
    }

    @Override
    public Class<?>[] getSpringAnnotatedConfigs() {
        return new Class<?>[]{StudioSampleBeanFactory.class};
    }
}