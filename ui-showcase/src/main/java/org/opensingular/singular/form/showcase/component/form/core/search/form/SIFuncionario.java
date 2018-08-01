package org.opensingular.singular.form.showcase.component.form.core.search.form;

import org.opensingular.form.type.core.SIInteger;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SIFuncionario extends SIGenericComposite<STFuncionario> {

    public SIString nome(){
        return getField(getType().nome);
    }

    public SIString funcao(){
        return getField(getType().funcao);
    }

    public SIInteger idade(){
        return getField(getType().idade);
    }

}
