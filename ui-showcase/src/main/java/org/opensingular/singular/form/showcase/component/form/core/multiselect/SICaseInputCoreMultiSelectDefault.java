package org.opensingular.singular.form.showcase.component.form.core.multiselect;

import org.opensingular.form.SIList;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SICaseInputCoreMultiSelectDefault extends SIGenericComposite<STCaseInputCoreMultiSelectDefault> {

    public SIList<SIString> frutas(){
        return getField(getType().frutas);
    }
}
