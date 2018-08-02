/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.samples.wicketutils.ui.page.modal.singularform;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.opensingular.form.SIComposite;
import org.opensingular.form.io.SFormXMLUtil;
import org.opensingular.form.wicket.modal.OpenSingularFormModalEvent;
import org.opensingular.form.wicket.panel.SFormModalEventListenerBehavior;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSContainer;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent.ConfigureCallback;
import org.opensingular.lib.wicket.util.template.SingularTemplate;
import org.opensingular.samples.wicketutils.types.simpleform.STSimpleForm;
import org.springframework.web.util.JavaScriptUtils;

public class SFormModalPage extends SingularTemplate {

    public SFormModalPage() {

        BSContainer<?> modalItemsContainer = new BSContainer<>("modalItemsContainer");
        add(modalItemsContainer);//);
        add(new SFormModalEventListenerBehavior(modalItemsContainer));

        add(new AjaxLink<Void>("simpleFormLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                openModal(target);
            }

            private void openModal(AjaxRequestTarget target) {
                new OpenSingularFormModalEvent<STSimpleForm, SIComposite>(target, STSimpleForm.class, (ConfigureCallback<SIComposite>) md -> {
                    md.setTitle("Simple Form");
                    md.addButton("Accept", (t, d, m) -> {
                        if (!m.getObject().hasNestedValidationErrors()) {
                            onAccept(t, m);
                            d.close(t);
                        }
                    });
                    md.addButton("Accept and repeat", (t, d, m) -> {
                        if (!m.getObject().hasNestedValidationErrors()) {
                            onAccept(t, m);
                            d.close(t);
                            openModal(t);
                        }
                    });
                    md.addCloseLink("Close");
                }).bubble(this);
            }
        });
    }

    private void onAccept(AjaxRequestTarget t, IModel<SIComposite> model) {
        t.appendJavaScript("alert('" + JavaScriptUtils.javaScriptEscape(SFormXMLUtil.toStringXML(model.getObject()).orElse("-")) + "');");
    }
}
