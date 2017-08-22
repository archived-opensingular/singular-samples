package org.opensingular.samples.studio.cfg;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.studio.app.AbstractStudioAppConfig;
import org.opensingular.studio.app.menu.StudioMenuItem;
import org.opensingular.studio.core.menu.GroupMenuEntry;
import org.opensingular.studio.core.menu.StudioMenu;

public class StudioSampleAppConfig extends AbstractStudioAppConfig {
    @Override
    public StudioMenu getAppMenu() {
        StudioMenu menu = new StudioMenu();
        GroupMenuEntry departamento = menu.add(new GroupMenuEntry(DefaultIcons.BRIEFCASE, "Departamento"));
        departamento.add(new StudioMenuItem(DefaultIcons.GIFT, "Presentes", "presenteRepository") {
            @Override
            public void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
                dataTableBuilder.appendPropertyColumn("Nome", it -> it.getValue("nome"));
            }
        });
        departamento.add(new StudioMenuItem(DefaultIcons.WRENCH, "Ferramentas", "ferramentaRepository") {
            @Override
            public void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
                dataTableBuilder.appendPropertyColumn("Nome", it -> it.getValue("nome"));
            }
        });
        return menu;
    }

    @Override
    public Class<?>[] getSpringAnnotatedConfigs() {
       return new Class<?>[]{StudioSampleBeanFactory.class};
    }
}