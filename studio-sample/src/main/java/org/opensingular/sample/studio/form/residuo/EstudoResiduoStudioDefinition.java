package org.opensingular.sample.studio.form.residuo;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.studio.app.definition.StudioDefinition;

public class EstudoResiduoStudioDefinition implements StudioDefinition {
    @Override
    public String getRepositoryBeanName() {
        return "estudoResiduoRepository";
    }

    @Override
    public void configureDatatableColumns(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
        dataTableBuilder.appendPropertyColumn("Cultura", ins -> ins.getValue(EstudoResiduo.class, i -> i.cultura.nome));
        dataTableBuilder.appendPropertyColumn("Modalidade de Emprego", ins -> ins.getValue(EstudoResiduo.class, i -> i.modalidadeDeEmprego.nome));
        dataTableBuilder.appendPropertyColumn("Norma", ins -> ins.getValue(EstudoResiduo.class, i -> i.norma.nome));
        dataTableBuilder.appendPropertyColumn("Tipo de Dose", ins -> ins.getValue(EstudoResiduo.class, i -> i.tipoDose.nome));
        dataTableBuilder.appendPropertyColumn("Intervalo de Segurança", ins -> ins.getValue(EstudoResiduo.class, i -> i.intervaloSeguranca));
    }

    @Override
    public String getTitle() {
        return "Cadastro de Estudos de Residuos";
    }
}
