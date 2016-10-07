/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.wicket.mapper.selection;

import org.opensingular.form.SInstance;
import org.opensingular.form.view.SViewBooleanByRadio;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.model.IModel;

public class BooleanRadioMapper extends RadioMapper {

    public String getReadOnlyFormattedText(IModel<? extends SInstance> model) {
        final SInstance mi = model.getObject();
        Boolean valor = mi.getValue(Boolean.class);
        if (valor != null) {
            SViewBooleanByRadio booleanRadioView = (SViewBooleanByRadio) mi.getType().getView();
            if (valor) {
                return booleanRadioView.labelTrue();
            } else {
                return booleanRadioView.labelFalse();
            }
        }
        return StringUtils.EMPTY;
    }
}
