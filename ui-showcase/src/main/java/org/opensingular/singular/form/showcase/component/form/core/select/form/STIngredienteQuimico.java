package org.opensingular.singular.form.showcase.component.form.core.select.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDecimal;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.generic.STGenericComposite;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class)
public class STIngredienteQuimico extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeString formulaQuimica;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        formulaQuimica = this.addFieldString("formulaQuimica");
        nome = this.addFieldString("nome");

        this.asAtr().label("Ingrediente Quimico");
    }
}
