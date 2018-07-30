package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseCustomPackage.class)
public class STypeContainer extends STypeComposite<SIComposite> {

    public STypeContainerString  tab1;
    public STypeContainerInteger tab2;
    public STypeContainerBoolean tab3;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        tab1 = this.addField("tab1", STypeContainerString.class);
        tab2 = this.addField("tab2", STypeContainerInteger.class);
        tab3 = this.addField("tab3", STypeContainerBoolean.class);

        tab1.asAtr().label("Aba 1");
        tab2.asAtr().label("Aba 2");
        tab3.asAtr().label("Aba 3");
    }
}
