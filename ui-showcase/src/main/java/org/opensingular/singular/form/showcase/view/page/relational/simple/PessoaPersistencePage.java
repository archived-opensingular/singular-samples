package org.opensingular.singular.form.showcase.view.page.relational.simple;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.opensingular.form.SIComposite;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypePessoa;
import org.opensingular.singular.form.showcase.studio.persistence.repository.PessoaFormRepository;
import org.opensingular.singular.form.showcase.view.page.relational.RelationalPersistencePage;
import org.wicketstuff.annotation.mount.MountPath;

import javax.inject.Inject;

@MountPath("relationalpersistence/simple")
public class PessoaPersistencePage extends RelationalPersistencePage {

    @Inject
    private PessoaFormRepository pessoaFormRepository;

    public PessoaPersistencePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder) {
        builder.appendPropertyColumn("Nome", ins -> ins.getValue("nome"));
        builder.appendPropertyColumn("CPF", ins -> ins.getValue("cpf"));
        builder.appendPropertyColumn("EMail", ins -> ins.getValue("email"));
    }

    @Override
    protected FormRespository getFormRespository() {
        return pessoaFormRepository;
    }

    @Override
    protected String getERImageStringURI() {
        return "pessoa_mer.jpg";
    }

    @Override
    protected String getDDLStringURI() {
        return "pessoa_ddl.sql";
    }

    @Override
    protected Class<?> getTypeClass() {
        return STypePessoa.class;
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return Model.of("Simple");
    }

}