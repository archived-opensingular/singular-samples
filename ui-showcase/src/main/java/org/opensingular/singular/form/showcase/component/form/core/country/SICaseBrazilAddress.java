package org.opensingular.singular.form.showcase.component.form.core.country;

import org.opensingular.form.SIComposite;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SICaseBrazilAddress extends SIGenericComposite<STCaseBrazilAddress> {

    public SIComposite endereco(){
        return getField(getType().endereco);
    }
}
