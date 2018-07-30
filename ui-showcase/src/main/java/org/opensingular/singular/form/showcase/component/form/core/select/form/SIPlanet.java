package org.opensingular.singular.form.showcase.component.form.core.select.form;

import org.opensingular.form.type.core.SIBigDecimal;
import org.opensingular.form.type.core.SIInteger;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SIPlanet extends SIGenericComposite<STPlanet>{

    public SIString name(){
        return getField(getType().name);
    }

    public SIInteger position(){
        return getField(getType().position);
    }

    public SIBigDecimal diameter(){
        return getField(getType().diameter);
    }

}
