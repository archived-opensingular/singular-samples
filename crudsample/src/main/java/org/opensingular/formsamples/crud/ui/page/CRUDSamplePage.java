package org.opensingular.formsamples.crud.ui.page;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.form.studio.SingularStudioSimpleCRUDPanel;
import org.opensingular.formsamples.crud.types.MyPackage.MyTypeForm;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.lib.wicket.util.template.SingularTemplate;

public class CRUDSamplePage extends SingularTemplate {

    @Inject
    private SpringFormPersistenceInMemory<MyTypeForm, SIComposite> formPersistence;

    public CRUDSamplePage() {
        add(new SingularStudioSimpleCRUDPanel<MyTypeForm, SIComposite>("crud", formPersistence) {
            @Override
            protected void buildListTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> dataTableBuilder) {
                dataTableBuilder
                    .appendPropertyColumn("Name", it -> it.getValue(MyTypeForm.class, f -> f.name))
                    .appendPropertyColumn("Age", it -> it.getValue(MyTypeForm.class, f -> f.age));
            }

        });
    }
}
