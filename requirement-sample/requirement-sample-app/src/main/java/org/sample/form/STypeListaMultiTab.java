package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewTab;

import javax.annotation.Nonnull;

@SInfoType(name = "ListaExemploMULTI", spackage = RequirementsamplePackage.class)
public class STypeListaMultiTab extends STypeComposite<SIComposite> {

    public STypeExemploMultiTab exemplo22;
    public STypeExemploMultiTab exemplo33;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtrIndex().indexed(Boolean.TRUE);
        exemplo22 = addField("exemplo22", STypeExemploMultiTab.class);
        exemplo22.asAtrIndex().indexed(Boolean.TRUE);

        exemplo33 = addField("exemplo33", STypeExemploMultiTab.class);

        SViewTab sViewTab = new SViewTab();
        sViewTab.addTab(exemplo22);
        sViewTab.addTab(exemplo33);
        this.withView(sViewTab);
        this.asAtr().label("Dados MultiTab");
    }
}
