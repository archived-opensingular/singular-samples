package org.opensingular.singular.form.showcase.view.page.relational.manytomany;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeCarrinhoCompra;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeProduto;
import org.opensingular.singular.form.showcase.studio.persistence.repository.CarrinhoFormRepository;
import org.opensingular.singular.form.showcase.view.page.relational.RelationalPersistencePage;
import org.wicketstuff.annotation.mount.MountPath;

import javax.inject.Inject;
import java.util.stream.Collectors;

@MountPath("relationalpersistence/manytomany")
public class CarrinhoPersistencePage extends RelationalPersistencePage {

    @Inject
    private CarrinhoFormRepository carrinhoFormRepository;

    public CarrinhoPersistencePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder) {
        builder.appendPropertyColumn("Produtos", ins -> {
            return ins
                    .findField(STypeCarrinhoCompra.class, type -> type.produtos)
                    .map(produtos ->
                            produtos.stream()
                                    .map(produto -> (String) produto.getValue("nome"))
                                    .collect(Collectors.joining(",")))
                    .orElse("");
        });
    }

    @Override
    protected FormRespository getFormRespository() {
        return carrinhoFormRepository;
    }

    @Override
    protected String getERImageStringURI() {
        return "MerCarrinho.png";
    }

    @Override
    protected String getDDLStringURI() {
        return "DdlCarrinho.sql";
    }

    @Override
    protected Class<?> getTypeClass() {
        return STypeCarrinhoCompra.class;
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return Model.of("Many to many");
    }
}
