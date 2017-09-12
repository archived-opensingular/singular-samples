package org.opensingular.sample.studio.definition;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.form.studio.StudioCRUDPermissionStrategy;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.studio.core.definition.StudioDefinition;

public class CulturaStudioDefinition implements StudioDefinition {
    @Override
    public String getRepositoryBeanName() {
        return "culturaRepository";
    }

    @Override
    public void configureDatatableColumns(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
        dataTableBuilder.appendPropertyColumn("Cultura", ins -> ins.getValue("nome"));
    }

    @Override
    public String getTitle() {
        return "Cadastro de Culturas";
    }

    @Override
    public StudioCRUDPermissionStrategy getPermissionStrategy() {
        return StudioCRUDPermissionStrategy.ALL;
    }
}
