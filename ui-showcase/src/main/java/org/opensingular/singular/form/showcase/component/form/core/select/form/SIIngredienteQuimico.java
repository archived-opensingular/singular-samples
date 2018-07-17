package org.opensingular.singular.form.showcase.component.form.core.select.form;

import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SIIngredienteQuimico extends SIGenericComposite<STIngredienteQuimico>{

    public SIString nome(){
        return getField(getType().nome);
    }

    public SIString formulaQuimica(){
        return getField(getType().formulaQuimica);
    }

}
