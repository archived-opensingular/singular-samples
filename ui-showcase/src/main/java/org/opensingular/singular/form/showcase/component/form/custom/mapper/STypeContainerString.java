package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseCustomPackage.class)
public class STypeContainerString extends STypeComposite<SIComposite> {

    public STypeString texto1;
    public STypeString texto2;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        texto1 = this.addFieldString("texto1");
        texto2 = this.addFieldString("texto2");

        texto1.asAtr().label("Texto 1");
        texto2.asAtr().label("Texto 2");
    }
}
