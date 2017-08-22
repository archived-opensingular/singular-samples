package org.opensingular.sample.studiop.form;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.studio.app.definition.StudioDefinition;

public class PresenteStudioDefinition implements StudioDefinition {
    @Override
    public String getRepositoryBeanName() {
        return "presenteRepository";
    }

    @Override
    public void configureDatatableColumns(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
        dataTableBuilder.appendPropertyColumn("Nome", it -> it.getValue("nome"));
    }

    @Override
    public String getTitle() {
        return "Cadastro de Presentes";
    }
}
