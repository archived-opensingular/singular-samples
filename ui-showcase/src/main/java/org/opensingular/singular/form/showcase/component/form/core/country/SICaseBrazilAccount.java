package org.opensingular.singular.form.showcase.component.form.core.country;

import org.opensingular.form.SIComposite;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SICaseBrazilAccount extends SIGenericComposite<STCaseBrazilAccount> {

    public SIComposite contaBancaria(){
        return getField(getType().contaBancaria);
    }
}
