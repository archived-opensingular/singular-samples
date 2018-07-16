package org.opensingular.singular.form.showcase.component.form.core.multiselect;

import org.opensingular.form.SIList;
import org.opensingular.form.type.generic.SIGenericComposite;

public class SICaseInputCoreMultiSelectComposite extends SIGenericComposite<STCaseInputCoreMultiSelectComposite> {

    public SIList<SIComponenteQuimico> componentesQuimicos(){
        return getField(getType().componentesQuimicos);
    }
}
