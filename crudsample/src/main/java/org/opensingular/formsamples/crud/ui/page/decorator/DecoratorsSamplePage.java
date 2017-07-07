package org.opensingular.formsamples.crud.ui.page.decorator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.opensingular.form.SDictionary;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInstance;
import org.opensingular.form.SType;
import org.opensingular.form.decorator.action.ISInstanceActionCapable;
import org.opensingular.form.decorator.action.SIcon;
import org.opensingular.form.decorator.action.SInstanceAction;
import org.opensingular.form.decorator.action.SInstanceAction.ActionType;
import org.opensingular.form.document.RefType;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.io.PersistenceBuilderXML;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.wicket.IWicketBuildListener;
import org.opensingular.form.wicket.IWicketComponentMapper;
import org.opensingular.form.wicket.WicketBuildContext;
import org.opensingular.form.wicket.panel.SingularFormPanel;
import org.opensingular.formsamples.crud.types.MyPackage.MyTypeForm;
import org.opensingular.internal.lib.commons.xml.MElement;
import org.opensingular.lib.commons.lambda.ISupplier;
import org.opensingular.lib.wicket.util.template.SingularTemplate;

public class DecoratorsSamplePage extends SingularTemplate {

    public DecoratorsSamplePage() {

        add(new SingularFormPanel("panel", MyTypeForm.class)
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
                    boolean secondary = false;
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
                                + "\n")),
                        new SInstanceAction(SInstanceAction.ActionType.PRIMARY)
                            .setIcon(SIcon.resolve("cog")
                                .setBgColor(
                                    Arrays.asList("red", "green", "blue")
                                        .get(new Random().nextInt(3))))
                            .setText("SInstance")
                            .setActionHandler((a, actionInstance, d) -> {
                                ISupplier<SInstance> formInstanceSupplier = () -> {
                                    SInstance ins = SDocumentFactory.empty().createInstance(new RefType() {
                                        @Override
                                        protected SType<?> retrieve() {
                                            SDictionary dict = SDictionary.create();
                                            return dict.getType(STypeString.class);
                                        }
                                    });
                                    ins.setValue("Test");
                                    return ins;
                                };
                                List<SInstanceAction> actions = Arrays.asList(

                                    new SInstanceAction(SInstanceAction.ActionType.PRIMARY)
                                        .setText("Modal as JSON")
                                        .setActionHandler((action, formInstance, modalDelegate) -> modalDelegate
                                            .showMessage("Modal as JSON",
                                                new PersistenceBuilderXML()
                                                    .withPersistId(false)
                                                    .toXML(formInstance.get())
                                                    .toJSONString())),

                                    new SInstanceAction(SInstanceAction.ActionType.PRIMARY)
                                        .setText("Base as JSON")
                                        .setActionHandler((action, formInstance, modalDelegate) -> {
                                            PersistenceBuilderXML builder = new PersistenceBuilderXML().withPersistId(false);
                                            SInstance baseInstance = modalDelegate.getInstanceRef().get();
                                            MElement xml = builder.toXML(baseInstance);
                                            String json = (xml != null) ? xml.toJSONString() : "{}";
                                            modalDelegate.showMessage("Base as JSON", json);
                                        }),

                                    new SInstanceAction(ActionType.NORMAL)
                                        .setText("Fechar")
                                        .setActionHandler((action, i, d1) -> {
                                            d1.closeForm(i.get());
                                            d1.refreshFieldForInstance(d.getInstanceRef().get());
                                        }));

                                d.openForm("Form", formInstanceSupplier, actions);
                            }));
                }
            }));
    }
}
