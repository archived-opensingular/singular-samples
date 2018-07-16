package org.opensingular.singular.form.showcase.component.form.core.select.form;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDecimal;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.generic.STGenericComposite;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class)
public class STIngredienteQuimico extends STGenericComposite<SIPlanet> {

    public STypeString name;
    public STypeInteger position;
    public STypeDecimal diameter;

    public STIngredienteQuimico() {
        super(SIPlanet.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        name     = this.addFieldString("name");
        position = this.addFieldInteger("position");
        diameter = this.addFieldDecimal("diameter");
    }
}
