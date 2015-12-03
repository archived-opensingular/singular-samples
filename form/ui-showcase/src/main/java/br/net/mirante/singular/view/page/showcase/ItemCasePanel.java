package br.net.mirante.singular.view.page.showcase;

import br.net.mirante.singular.form.mform.MInstancia;
import br.net.mirante.singular.form.mform.MTipo;
import br.net.mirante.singular.form.mform.io.MformPersistenciaXML;
import br.net.mirante.singular.form.util.xml.MElement;
import br.net.mirante.singular.form.validation.InstanceValidationContext;
import br.net.mirante.singular.form.validation.ValidationErrorLevel;
import br.net.mirante.singular.form.wicket.UIBuilderWicket;
import br.net.mirante.singular.form.wicket.WicketBuildContext;
import br.net.mirante.singular.form.wicket.model.MInstanceRootModel;
import br.net.mirante.singular.form.wicket.util.WicketFormUtils;
import br.net.mirante.singular.showcase.CaseBase;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSContainer;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSGrid;
import br.net.mirante.singular.util.wicket.modal.BSModalBorder;
import br.net.mirante.singular.view.SingularWicketContainer;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.io.PrintWriter;
import java.io.StringWriter;

import static br.net.mirante.singular.util.wicket.util.WicketUtils.$m;


public class ItemCasePanel extends Panel implements SingularWicketContainer<ItemCasePanel, Void> {

    private Form<?> inputForm = new Form<>("save-form");
    private BSGrid container = new BSGrid("generated");
    private IModel<MInstancia> currentInstance;
    private CaseBase caseBase;
    private final BSModalBorder viewXmlModal = new BSModalBorder("viewXmlModal");

    public ItemCasePanel(String id, CaseBase caseBase) {
        super(id);
        this.caseBase = caseBase;
        createInstance();
        updateContainer();
    }

    private void createInstance() {
        MTipo<?> tipo = caseBase.getCaseType();
        currentInstance = new MInstanceRootModel<>(tipo.novaInstancia());
    }

    private void updateContainer() {
        inputForm.remove(container);
        container = new BSGrid("generated");
        inputForm.add(container);
        inputForm.add(viewXmlModal);
        buildContainer();
    }

    private void buildContainer() {
        WicketBuildContext ctx = new WicketBuildContext(container.newColInRow(), buildBodyContainer());
        UIBuilderWicket.buildForEdit(ctx, currentInstance);
    }

    private BSContainer<?> buildBodyContainer() {
        BSContainer<?> bodyContainer = new BSContainer<>("body-container");
        add(bodyContainer);
        return bodyContainer;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(inputForm
                .add(createFeedbackPanel())
                .add(createSaveButton())
                .add(createValidateButton())
        );
    }

    private Component createSaveButton() {
        return new AjaxButton("save-btn") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

                MInstancia trueInstance = currentInstance.getObject();
                trueInstance.getDocument().persistFiles();
                MElement rootXml = MformPersistenciaXML.toXML(trueInstance);
                //TODO validação
                viewXml(target, printXml(rootXml));
            }
        };
    }

    private String printXml(MElement rootXml) {
        if (rootXml != null) {
            StringWriter buffer = new StringWriter();
            rootXml.printTabulado(new PrintWriter(buffer));
            return buffer.toString();
        }
        return null;
    }

    private Component createFeedbackPanel() {
        return new FencedFeedbackPanel("feedback", inputForm).add(new Behavior() {
            @Override
            public void onConfigure(Component component) {
                component.setVisible(((FencedFeedbackPanel) component).anyMessage());
            }
        });
    }

    private void addValidationErrors(AjaxRequestTarget target, Form<?> form, MInstancia trueInstance,
                                     MElement rootXml) throws Exception {
        runDefaultValidators(form, trueInstance);
        validateEmptyForm(form, rootXml);
    }

    private void validateEmptyForm(Form<?> form, MElement rootXml) {
        if (rootXml == null) {
            form.error(getMessage("form.message.empty").getString());
            throw new RuntimeException("Has empty form");
        }
    }

    private void runDefaultValidators(Form<?> form, MInstancia trueInstance) {
        InstanceValidationContext validationContext = new InstanceValidationContext(trueInstance);
        validationContext.validateAll();
        WicketFormUtils.associateErrorsToComponents(validationContext, form);

        if (validationContext.hasErrorsAboveLevel(ValidationErrorLevel.WARNING)) {
            throw new RuntimeException("Has form errors");
        }
    }

    private AjaxButton createValidateButton() {
        return new AjaxButton("validate-btn") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                MElement rootXml = MformPersistenciaXML.toXML(currentInstance.getObject());
                currentInstance.getObject().getDocument().persistFiles();
                //TODO validação
            }
        };
    }

    private void viewXml(AjaxRequestTarget target, String xml) {
        viewXmlModal.addOrReplace(new Label("xmlCode", $m.ofValue(xml)));
        viewXmlModal.show(target);
        viewXmlModal.setSize(BSModalBorder.Size.FIT);
    }

}