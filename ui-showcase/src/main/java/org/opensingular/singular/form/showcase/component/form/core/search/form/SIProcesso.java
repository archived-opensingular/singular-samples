package org.opensingular.singular.form.showcase.component.form.core.search.form;

import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SIProcesso extends SIGenericComposite<STProcesso> {

    public SIString id(){
        return getField(getType().id);
    }

    public SIString nome(){
        return getField(getType().nome);
    }

}
