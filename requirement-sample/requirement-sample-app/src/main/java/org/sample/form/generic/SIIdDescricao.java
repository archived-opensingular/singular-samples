package org.sample.form.generic;

import org.opensingular.form.type.core.SILong;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SIIdDescricao extends SIGenericComposite<STIdDescricao> {

    public SILong id() {
        return this.getDescendant(getType().id);
    }

    public SIString descricao() {
        return this.getDescendant(getType().descricao);
    }

}