package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseCustomPackage.class)
public class STypeContainerInteger extends STypeComposite<SIComposite> {

    public STypeInteger int1;
    public STypeInteger int2;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        int1 = this.addFieldInteger("int1");
        int2 = this.addFieldInteger("int2");

        int1.asAtr().label("Inteiro 1");
        int2.asAtr().label("Inteiro 2");
    }
}
