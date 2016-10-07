/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.form.wicket.mapper.selection;

import org.opensingular.form.SInstance;
import org.opensingular.form.converter.SInstanceConverter;
import org.opensingular.form.view.SView;
import org.opensingular.form.view.SViewAutoComplete;
import org.opensingular.form.view.SViewSelectionBySelect;
import org.opensingular.form.wicket.WicketBuildContext;
import org.opensingular.form.wicket.mapper.AbstractControlsFieldComponentMapper;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSControls;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * Mapper responsible for rendering the SViewAutoComplete withing wicket.
 *
 * @author Fabricio Buzeto
 */
public class AutocompleteMapper extends AbstractControlsFieldComponentMapper {

    @Override
    public Component appendInput(WicketBuildContext ctx, BSControls formGroup, IModel<String> labelModel) {
        final IModel<? extends SInstance> model = ctx.getModel();
        final SView view = ctx.getView();
        
        validateView(view);
        SViewAutoComplete.Mode fetch = SViewAutoComplete.Mode.STATIC;
        if(view instanceof SViewAutoComplete){
            fetch = ((SViewAutoComplete) view).fetch();
        }
        final TypeaheadComponent comp = new TypeaheadComponent(model.getObject().getName(), model, fetch);
        formGroup.appendDiv(comp);
        return comp.getValueField();
    }

    private void validateView(SView view) {
        if (!isAValidView(view)) {
            throw new RuntimeException("AutocompleteMapper only accepts SViewAutoComplete as its view");
        }
    }

    private boolean isAValidView(SView view) {
        return view instanceof SViewAutoComplete ||
                view instanceof SViewSelectionBySelect;
    }

    @Override
    public String getReadOnlyFormattedText(IModel<? extends SInstance> model) {
        final SInstance mi = model.getObject();
        if ((mi != null) && (mi.getValue() != null)) {
            final SInstanceConverter converter = mi.asAtrProvider().getConverter();
            if (converter != null) {
                final Serializable converted = converter.toObject(mi);
                if (converted != null) {
                    return mi.asAtrProvider().getDisplayFunction().apply(converted);
                }
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void adjustJSEvents(Component comp) {
    }

}