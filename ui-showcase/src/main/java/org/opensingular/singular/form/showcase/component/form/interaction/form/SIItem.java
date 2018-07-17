package org.opensingular.singular.form.showcase.component.form.interaction.form;

import org.opensingular.form.type.core.SIInteger;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SIItem extends SIGenericComposite<STItem> {

    public SIString nome(){
        return getField(getType().nome);
    }

    public SIInteger quantidade(){
        return getField(getType().quantidade);
    }

}
