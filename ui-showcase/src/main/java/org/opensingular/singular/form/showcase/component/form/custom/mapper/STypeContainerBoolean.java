package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseCustomPackage.class)
public class STypeContainerBoolean extends STypeComposite<SIComposite> {

    public STypeBoolean bool1;
    public STypeBoolean bool2;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        bool1 = this.addFieldBoolean("bool1");
        bool2 = this.addFieldBoolean("bool2");

        bool1.asAtr().label("Boolean 1");
        bool2.asAtr().label("Boolean 2");
    }
}
