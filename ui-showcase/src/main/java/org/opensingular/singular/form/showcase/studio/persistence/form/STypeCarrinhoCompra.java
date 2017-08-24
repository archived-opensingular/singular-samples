package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.*;
import org.opensingular.form.view.SViewListByTable;

import javax.annotation.Nonnull;

@SInfoType(name = "carrinhoDeCompras", spackage = SPackageStudioPersistenceForm.class)
public class STypeCarrinhoCompra extends STypeComposite<SIComposite> {

    public STypeList<STypeProduto, SIComposite> produtos;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        produtos = addFieldListOf("produtos", STypeProduto.class);
        produtos.withView(new SViewListByTable(), view -> view.setRenderCompositeFieldsAsColumns(false));
        produtos.asAtrBootstrap().colPreference(12).asAtr().label("Produtos");
    }

}
