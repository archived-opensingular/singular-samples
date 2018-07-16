package org.opensingular.singular.form.showcase.component.form.core.multiselect;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SICaseInputCoreMultiSelectProvider extends SIGenericComposite<STCaseInputCoreMultiSelectProvider> {

    public SIList<SIComposite> arquivos(){
        return getField(getType().arquivos);
    }
}
