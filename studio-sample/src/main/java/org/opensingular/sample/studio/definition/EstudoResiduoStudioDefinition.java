package org.opensingular.sample.studio.definition;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.sample.studio.form.EstudoResiduo;
import org.opensingular.studio.app.definition.StudioDefinition;

public class EstudoResiduoStudioDefinition implements StudioDefinition {
    @Override
    public String getRepositoryBeanName() {
        return "estudoResiduoRepository";
    }

    @Override
    public void configureDatatableColumns(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
        dataTableBuilder.appendPropertyColumn("Cultura", ins -> ins.getValue(EstudoResiduo.class, i -> i.cultura.display));
        dataTableBuilder.appendPropertyColumn("Modalidade de Emprego", ins -> ins.getValue(EstudoResiduo.class, i -> i.modalidadeDeEmprego.display));
        dataTableBuilder.appendPropertyColumn("Norma", ins -> ins.getValue(EstudoResiduo.class, i -> i.norma.display));
        dataTableBuilder.appendPropertyColumn("Tipo de Dose", ins -> ins.getValue(EstudoResiduo.class, i -> i.tipoDose.display));
        dataTableBuilder.appendPropertyColumn("Intervalo de Segurança", ins -> ins.getValue(EstudoResiduo.class, i -> i.intervaloSeguranca));
    }

    @Override
    public String getTitle() {
        return "Cadastro de Estudos de Residuos";
    }
}
