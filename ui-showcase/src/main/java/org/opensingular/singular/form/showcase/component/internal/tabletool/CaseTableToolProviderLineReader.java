package org.opensingular.singular.form.showcase.component.internal.tabletool;

import org.apache.wicket.markup.html.panel.Panel;
import org.opensingular.lib.commons.table.ColumnType;
import org.opensingular.lib.commons.table.LineInfo;
import org.opensingular.lib.commons.table.LineReadContext;
import org.opensingular.lib.commons.table.LineReader;
import org.opensingular.lib.commons.table.TableTool;
import org.opensingular.lib.wicket.views.WicketViewWrapperForViewOutputHtml;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

/**
 * Populating TableTool using a TableTool#setReaderByLine and a LineReader implementation.
 *
 * @author Daniel C. Bordin
 * @since 2018-07-27
 */
@CaseItem(componentName = "Data Provider", subCaseName = "LineReader", group = Group.TABLE_TOOL, resources = @Resource(value = MyProject.class))
public class CaseTableToolProviderLineReader extends Panel {

    public CaseTableToolProviderLineReader(String id) {
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
        table.setReaderByLine(MyProject.listProjects(), new LineReader<MyProject>() {
            @Override
            public void retrieveValues(LineReadContext ctx, MyProject prj, LineInfo line) {
                line.get(0).setValue(prj.getName());
                line.get(1).setValue(prj.getStart());
                line.get(2).setValue(prj.getEnd());
                line.get(3).setValue(prj.getExpenses());
            }
        });
        //@destacar:fim
        return table;
    }

}
