package org.opensingular.singular.form.showcase.component.internal.tabletool;

import com.google.common.io.LineReader;
import org.apache.wicket.markup.html.panel.Panel;
import org.opensingular.lib.commons.table.ColumnType;
import org.opensingular.lib.commons.table.LineInfo;
import org.opensingular.lib.commons.table.LineReadContext;
import org.opensingular.lib.commons.table.TableTool;
import org.opensingular.lib.commons.table.TreeLineReader;
import org.opensingular.lib.wicket.views.WicketViewWrapperForViewOutputHtml;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Populating TableTool with data structured as a tree using TreeLineReader interface.
 *
 * @author Daniel C. Bordin
 * @since 2018-07-27
 */
@CaseItem(componentName = "Data Provider", subCaseName = "TreeLineReader", group = Group.TABLE_TOOL)
public class CaseTableToolProviderTreeLineReader extends Panel {

    public CaseTableToolProviderTreeLineReader(String id) {
        super(id);
        add(new WicketViewWrapperForViewOutputHtml("table", this::createTable));
    }

    public TableTool createTable() {
        TableTool table = new TableTool();
        table.addColumn(ColumnType.STRING, "Class/Method");
        table.addColumn(ColumnType.STRING, "Modifiers");
        table.addColumn(ColumnType.STRING, "Return");

        //@destacar:bloco
        table.setReaderByTree(new TreeLineReader() {

            @Override
            public Object getRoots() {
                //May return collections, arrays, iterators or a single object
                return new Class<?>[] {TreeLineReader.class, LineReader.class};
            }

            @Override
            public Object getChildren(Object item) {
                //May return collections, arrays, iterator or a single object representing item's children
                return item instanceof Class ? ((Class<?>) item).getDeclaredMethods() : null;
            }

            @Override
            public void retrieveValues(LineReadContext ctx, Object current, LineInfo line) {
                if (current instanceof Class) {
                    Class<?> c = (Class<?>) current;
                    line.get(0).setValue(c.getSimpleName());
                    line.get(1).setValue(Modifier.toString(c.getModifiers()));
                } else if (current instanceof Method) {
                    Method m = (Method) current;
                    line.get(0).setValue(m.getName() + "()");
                    line.get(1).setValue(Modifier.toString(m.getModifiers()));
                    line.get(2).setValue(m.getReturnType().getSimpleName());
                }
            }
        });
        //@destacar:fim
        return table;
    }
}
