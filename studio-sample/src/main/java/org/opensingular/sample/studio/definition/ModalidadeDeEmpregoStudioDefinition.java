package org.opensingular.sample.studio.definition;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.studio.app.definition.StudioDefinition;

public class ModalidadeDeEmpregoStudioDefinition implements StudioDefinition {
    @Override
    public String getRepositoryBeanName() {
        return "modalidadeDeEmpregoRepository";
    }

    @Override
    public void configureDatatableColumns(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
        dataTableBuilder.appendPropertyColumn("Modalidade de emprego", ins -> ins.getValue("nome"));
    }

    @Override
    public String getTitle() {
        return "Cadastro de Modalidades de Emprego";
    }
}
