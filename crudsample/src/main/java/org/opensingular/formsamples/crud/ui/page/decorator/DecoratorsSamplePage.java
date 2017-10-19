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

package org.opensingular.formsamples.crud.ui.page.decorator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.opensingular.form.SIComposite;
import org.opensingular.form.decorator.action.ISInstanceActionCapable;
import org.opensingular.form.decorator.action.SIcon;
import org.opensingular.form.decorator.action.SInstanceAction;
import org.opensingular.form.wicket.IWicketBuildListener;
import org.opensingular.form.wicket.IWicketComponentMapper;
import org.opensingular.form.wicket.WicketBuildContext;
import org.opensingular.form.wicket.enums.AnnotationMode;
import org.opensingular.form.wicket.panel.SingularFormPanel;
import org.opensingular.formsamples.crud.types.antaq.Resolucao912Form;
import org.opensingular.lib.wicket.util.ajax.ActionAjaxLink;
import org.opensingular.lib.wicket.util.template.SingularTemplate;

public class DecoratorsSamplePage extends SingularTemplate {

    public DecoratorsSamplePage() {

        SingularFormPanel singularFormPanel = new SingularFormPanel("panel", Resolucao912Form.class)
            .setAnnotationMode(AnnotationMode.EDIT)
            .addBuildListener(new IWicketBuildListener() {
                @Override
                public void onBeforeBuild(WicketBuildContext ctx, IWicketComponentMapper mapper) {
                    if (mapper instanceof ISInstanceActionCapable) {
                        ISInstanceActionCapable iac = (ISInstanceActionCapable) mapper;
                        iac.addSInstanceActionsProvider(0,
                            (t, i) -> (i instanceof SIComposite)
                                ? Collections.emptyList()
                                : actionList());
                    }
                }

                private List<SInstanceAction> actionList() {
                    boolean secondary = true;
                    return Arrays.asList(
                        new SInstanceAction(SInstanceAction.ActionType.NORMAL)
                            .setIcon(SIcon.resolve("html5")
                                .setColors("white", "red"))
                            .setText("HTML")
                            .setSecondary(secondary)
                            .setActionHandler((a, i, d) -> d.showMessage("HTML", ""
                                + "<h1>HTML</h1>"
                                + "<p>This is a paragraph, with <b>bold</b>, <i>italic</i>, and <u>underlined</u> text.</p>"
                                + "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>"
                                + "<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>"
                                + "<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>"
                                + "<p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
                                + "<table class='table table-hover'>"
                                + "  <tr>"
                                + "    <th>A</th>"
                                + "    <th>B</th>"
                                + "    <th>C</th>"
                                + "  </tr>"
                                + "  <tr>"
                                + "    <td>a</td>"
                                + "    <td>b</td>"
                                + "    <td>c</td>"
                                + "  </tr>"
                                + "  <tr>"
                                + "    <td>d</td>"
                                + "    <td>e</td>"
                                + "    <td>f</td>"
                                + "  </tr>"
                                + "</table>"
                                + "")),
                        new SInstanceAction(SInstanceAction.ActionType.NORMAL)
                            .setIcon(SIcon.resolve("doc"))
                            .setText("Text")
                            .setSecondary(secondary)
                            .setActionHandler((a, i, d) -> d.showMessage("Text", ""
                                + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \n"
                                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. \n"
                                + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. \n"
                                + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")),
                        new SInstanceAction(SInstanceAction.ActionType.NORMAL)
                            .setIcon(SIcon.resolve("asterisk"))
                            .setText("Markdown")
                            .setSecondary(secondary)
                            .setActionHandler((a, i, d) -> d.showMessage("Markdown", ""
                                + "\n# Título"
                                + "\n"
                                + "\nIsto é um parágrafo."
                                + "\n* item 1"
                                + "\n* **item 2** (negrito)"
                                + "\n* *item 3* (itálico)"
                                + "\n"
                                + "\n--"
                                + "\n"
                                + "\n[Google](https://google.com)"
                                + "\n"
                                + "\n"))
                    //                            new SInstanceAction(SInstanceAction.ActionType.PRIMARY)
                    //                                .setIcon(SIcon.resolve("cog")
                    //                                    .setBgColor(
                    //                                        Arrays.asList("red", "green", "blue")
                    //                                            .get(new Random().nextInt(3))))
                    //                                .setText("SInstance")
                    //                                .setActionHandler((a, actionInstance, d) -> {
                    //                                    ISupplier<SInstance> formInstanceSupplier = () -> {
                    //                                        SInstance ins = SDocumentFactory.empty().createInstance(new RefType() {
                    //                                            @Override
                    //                                            protected SType<?> retrieve() {
                    //                                                SDictionary dict = SDictionary.create();
                    //                                                return dict.getType(STypeString.class);
                    //                                            }
                    //                                        });
                    //                                        ins.setValue("Test");
                    //                                        return ins;
                    //                                    };
                    //
                    //                                    Out<SInstanceAction.FormDelegate> formDelegate = new Out<>();
                    //                                    List<SInstanceAction> actions = Arrays.asList(
                    //
                    //                                        new SInstanceAction(SInstanceAction.ActionType.PRIMARY)
                    //                                            .setText("Modal as JSON")
                    //                                            .setActionHandler((action, formInstance, modalDelegate) -> modalDelegate
                    //                                                .showMessage("Modal as JSON",
                    //                                                    new PersistenceBuilderXML()
                    //                                                        .withPersistId(false)
                    //                                                        .toXML(formInstance.get())
                    //                                                        .toJSONString())),
                    //
                    //                                        new SInstanceAction(SInstanceAction.ActionType.PRIMARY)
                    //                                            .setText("Base as JSON")
                    //                                            .setActionHandler((action, formInstance, modalDelegate) -> {
                    //                                                PersistenceBuilderXML builder = new PersistenceBuilderXML().withPersistId(false);
                    //                                                SInstance baseInstance = modalDelegate.getInstanceRef().get();
                    //                                                MElement xml = builder.toXML(baseInstance);
                    //                                                String json = (xml != null) ? xml.toJSONString() : "{}";
                    //                                                modalDelegate.showMessage("Base as JSON", json);
                    //                                            }),
                    //
                    //                                        new SInstanceAction(ActionType.NORMAL)
                    //                                            .setText("Fechar")
                    //                                            .setActionHandler((action, i, d1) -> {
                    //                                                formDelegate.get().close();
                    //                                                d1.refreshFieldForInstance(d.getInstanceRef().get());
                    //                                            }));
                    //
                    //                                    d.openForm(formDelegate, "Form", formInstanceSupplier, actions);
                    //                                })//
                    );
                }
            });
        Form<?> form = new Form<>("form");
        form.setMultiPart(true);
        add(form
            .add(singularFormPanel)
            .add(new ActionAjaxLink<Void>("toggleEdit") {
                @Override
                protected void onAction(AjaxRequestTarget target) {
                    int next = singularFormPanel.getAnnotationMode().ordinal() + 1;
                    AnnotationMode[] modes = AnnotationMode.values();
                    singularFormPanel.setAnnotationMode(modes[next % modes.length]);
                    target.add(singularFormPanel);
                }
            }));
    }
}
