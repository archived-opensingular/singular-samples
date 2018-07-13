package org.opensingular.singular.form.showcase.component.form.core.country;

import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SICaseBrazilBasic extends SIGenericComposite<STCaseBrazilBasic> {

    public SIString cnpj(){
        return getField(getType().cnpj);
    }

    public SIString cpf(){
        return getField(getType().cpf);
    }

    public SIString cep(){
        return getField(getType().cep);
    }

    public SIString telefone(){
        return getField(getType().telefone);
    }
}
