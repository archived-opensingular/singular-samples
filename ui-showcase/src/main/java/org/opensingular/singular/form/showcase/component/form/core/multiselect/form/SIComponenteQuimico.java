package org.opensingular.singular.form.showcase.component.form.core.multiselect.form;

import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SIComponenteQuimico extends SIGenericComposite<STComponenteQuimico> {

    public SIString nome(){
        return getField(getType().nome);
    }

    public SIString formulaQuimica(){
        return getField(getType().formulaQuimica);
    }

}
