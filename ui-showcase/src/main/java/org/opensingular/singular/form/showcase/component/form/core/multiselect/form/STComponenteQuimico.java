package org.opensingular.singular.form.showcase.component.form.core.multiselect.form;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.generic.STGenericComposite;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class, name = "ComponenteQuimico")
public class STComponenteQuimico extends STGenericComposite<SIComponenteQuimico> {

    public STypeString nome;
    public STypeString formulaQuimica;

    public STComponenteQuimico() {
        super(SIComponenteQuimico.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome           = this.addFieldString("nome");
        formulaQuimica = this.addFieldString("formulaQuimica");
    }
}
