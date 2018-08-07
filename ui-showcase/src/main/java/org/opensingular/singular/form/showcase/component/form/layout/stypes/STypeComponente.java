package org.opensingular.singular.form.showcase.component.form.layout.stypes;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewBreadcrumb;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypeComponente extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeList<STypeSubComponente, SIComposite> subComponentes;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        subComponentes = this.addFieldListOf("subComponentes", STypeSubComponente.class);

        nome.asAtr().label("Nome");

        subComponentes
                //@destacar
                .withView(SViewBreadcrumb::new)
                .asAtr().label("Testes de componente");

        this.withView(SViewByBlock::new);
    }
}
