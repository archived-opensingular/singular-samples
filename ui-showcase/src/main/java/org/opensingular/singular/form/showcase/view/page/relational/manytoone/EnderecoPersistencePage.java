package org.opensingular.singular.form.showcase.view.page.relational.manytoone;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.opensingular.form.SIComposite;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeEndereco;
import org.opensingular.singular.form.showcase.studio.persistence.repository.EnderecoFormRepository;
import org.opensingular.singular.form.showcase.view.page.relational.RelationalPersistencePage;
import org.wicketstuff.annotation.mount.MountPath;

import javax.inject.Inject;

@MountPath("relationalpersistence/manytoone")
public class EnderecoPersistencePage extends RelationalPersistencePage {

    @Inject
    private EnderecoFormRepository enderecoFormRepository;

    public EnderecoPersistencePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder) {
        builder.appendPropertyColumn("CEP", ins -> ins.getValue("cep"));
        builder.appendPropertyColumn("UF", ins -> ins.getValue("uf.nome"));
        builder.appendPropertyColumn("Endereço", ins -> ins.getValue("endereco"));
        builder.appendPropertyColumn("Bairro", ins -> ins.getValue("bairro"));
    }

    @Override
    protected FormRespository getFormRespository() {
        return enderecoFormRepository;
    }

    @Override
    protected String getERImageStringURI() {
        return "MerEnderecoUf.png";
    }

    @Override
    protected String getDDLStringURI() {
        return "DdlEnderecoUf.sql";
    }

    @Override
    protected Class<?> getTypeClass() {
        return STypeEndereco.class;
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return Model.of("Many to one");
    }
}
