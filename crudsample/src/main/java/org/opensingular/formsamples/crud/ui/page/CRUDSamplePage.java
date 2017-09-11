package org.opensingular.formsamples.crud.ui.page;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.form.studio.SingularStudioSimpleCRUDPanel;
import org.opensingular.formsamples.crud.types.toxicologia.EstudoResiduoForm;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.lib.wicket.util.template.admin.SingularAdminTemplate;

import javax.inject.Inject;

public class CRUDSamplePage extends SingularAdminTemplate {

    @Inject
    private SpringFormPersistenceInMemory<EstudoResiduoForm, SIComposite> formPersistence;

    public CRUDSamplePage() {
        Form<Void> form = new Form<>("form");
        form.setMultiPart(true);
        form.add(new SingularStudioSimpleCRUDPanel<EstudoResiduoForm, SIComposite>("crud", formPersistence) {
            @Override
            protected void buildListTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
                dataTableBuilder
                        .appendPropertyColumn("Cultura", it -> {
                            if (Boolean.TRUE.equals(it.getValue("outraCultura"))) {
                                return it.getValue("nomeOutraCultura");
                            } else {
                                return it.getValue("cultura.nomeCultura");
                            }
                        })
                        .appendPropertyColumn("Emprego", it -> it.getValue("emprego.nomeEmprego"));
            }
        }.setCrudTitle("Cadastro de Estudos"));
        add(form);
    }

    @Override
    protected IModel<String> getContentTitle() {
        return null;
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return null;
    }

    @Override
    protected boolean isWithMenu() {
        return false;
    }
}
