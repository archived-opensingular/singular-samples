package org.opensingular.singular.form.showcase.component.internal.tabletool;

import org.apache.wicket.markup.html.panel.Panel;
import org.opensingular.lib.commons.table.ColumnType;
import org.opensingular.lib.commons.table.TableTool;
import org.opensingular.lib.wicket.views.WicketViewWrapperForViewOutputHtml;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

/**
 * Populating TableTool using a TableTool#setReaderByLine and a LineReader implementation may also use lambda expression
 * to translate de object to columns.
 *
 * @author Daniel C. Bordin
 * @since 2018-07-27
 */
@CaseItem(componentName = "Data Provider", subCaseName = "LineReader Lambda", group = Group.TABLE_TOOL, resources =
@Resource(value = MyProject.class))
public class CaseTableToolProviderLineReaderLambda extends Panel {

    public CaseTableToolProviderLineReaderLambda(String id) {
        super(id);
        add(new WicketViewWrapperForViewOutputHtml("table", this::createTable));
    }

    public TableTool createTable() {
        TableTool table = new TableTool();
        table.addColumn(ColumnType.STRING, "Name");
        table.addColumn(ColumnType.DATE, "Start Date");
        table.addColumn(ColumnType.DATE, "End Date");
        table.addColumn(ColumnType.MONEY, "Expenses");

        //@destacar:bloco
        table.setReaderByLine(MyProject.listProjects(), (ctx, prj, line) -> {
            line.get(0).setValue(prj.getName());
            line.get(1).setValue(prj.getStart());
            line.get(2).setValue(prj.getEnd());
            line.get(3).setValue(prj.getExpenses());
        });
        //@destacar:fim
        return table;
    }

}
