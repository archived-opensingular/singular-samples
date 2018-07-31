package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewTab;

import javax.annotation.Nonnull;

@SInfoType(name = "ListaExemplo", spackage = RequirementsamplePackage.class)
public class STypeListaExemplo extends STypeComposite<SIComposite> {

    public STypeExemplo exemplo2;
    public STypeExemplo exemplo3;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtrIndex().indexed(Boolean.TRUE);
        exemplo2 = addField("exemplo2", STypeExemplo.class);
        exemplo2.asAtrIndex().indexed(Boolean.TRUE);

        exemplo3 = addField("exemplo3", STypeExemplo.class);
        exemplo3.asAtrIndex().indexed(Boolean.TRUE);

        SViewTab sViewTab = new SViewTab();
        sViewTab.addTab(exemplo2);
        sViewTab.addTab(exemplo3);
        this.withView(sViewTab);
        this.asAtr().label("Dados EXEMPLO");
    }
}
