package org.opensingular.singular.form.showcase.component.internal.tabletool;

import org.apache.wicket.markup.html.panel.Panel;
import org.opensingular.lib.commons.table.ColumnType;
import org.opensingular.lib.commons.table.TablePopulator;
import org.opensingular.lib.commons.table.TableTool;
import org.opensingular.lib.commons.views.ViewOutputFormat;
import org.opensingular.lib.commons.views.ViewOutputFormatExportable;
import org.opensingular.lib.wicket.views.WicketViewWrapperForViewOutputHtml;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

/**
 * Populating TableTool using TablePopulator, a static data structure, using TablePopulator#insertLine() and
 * TablePopulator@setValue().
 *
 * @author Daniel C. Bordin
 * @since 2018-07-27
 */
@CaseItem(componentName = "Data Provider", subCaseName = "TablePopulator", group = Group.TABLE_TOOL)
public class CaseTableToolProviderTablePopulator extends Panel {

    public CaseTableToolProviderTablePopulator(String id) {
        super(id);
        add(new WicketViewWrapperForViewOutputHtml("table", this::createTable));
    }

    public TableTool createTable() {
        TableTool table = new TableTool();
        table.addColumn(ColumnType.STRING, "Name");
        table.addColumn(ColumnType.STRING, "DisplayName");
        table.addColumn(ColumnType.STRING, "Class");
        table.addColumn(ColumnType.STRING, "Exportable File Extension");

        TablePopulator populator = table.createSimpleTablePopulator(); //@destacar
        for (ViewOutputFormat format : table.getDirectSupportedFormats()) {
            //@destacar:bloco
            TablePopulator tablePopulator = populator.insertLine();
            tablePopulator.setValue(0, format.getName());
            tablePopulator.setValue(1, format.getDisplayName());
            tablePopulator.setValue(2, format.getClass().getSimpleName());
            if (format instanceof ViewOutputFormatExportable) {
                tablePopulator.setValue(3, ((ViewOutputFormatExportable) format).getFileExtension());
            }
            //@destacar:fim
        }
        return table;
    }
}
