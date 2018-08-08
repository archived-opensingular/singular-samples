package org.opensingular.singular.form.showcase.component.internal.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SDictionary;
import org.opensingular.form.document.RefType;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.wicket.modal.OpenSingularFormModalEvent;
import org.opensingular.form.wicket.model.SInstanceRootModel;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSContainer;
import org.opensingular.lib.wicket.util.modal.BSModalEventListenerBehavior;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

/**
 * Opening a modal window populated with wicket components by bubbling an event to a listening parent container
 *
 * @author Ronald Tetsuo Miura
 * @since 2018-08-02
 */
@CaseItem(componentName = "Modal by Event", subCaseName = "Singular Form", group = Group.MODAL)
public class CaseSingularFormModalByEvent extends Panel {

    private final IModel<String> textModel           = new Model<>("...");
    private final BSContainer<?> modalItemsContainer = new BSContainer<>("modalItemsContainer");
    private final Label          textLabel           = new Label("textLabel", textModel);

    public CaseSingularFormModalByEvent(String id) {
        super(id);

        add(modalItemsContainer);
        add(new BSModalEventListenerBehavior(modalItemsContainer));

        add(textLabel);
        add(new AjaxLink<Void>("change") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                newOpenModalEvent(target).bubble(this);
            }
        });
    }

    private OpenSingularFormModalEvent<STypeString, SIString> newOpenModalEvent(AjaxRequestTarget target) {

        SInstanceRootModel<SIString> instanceModel = new SInstanceRootModel<>(
            (SIString) SDocumentFactory.empty().createInstance(newRefType()));

        return new OpenSingularFormModalEvent<STypeString, SIString>(target, instanceModel, delegate -> {
            instanceModel.getObject().setValue(textModel.getObject());

            delegate.setTitle("Singular Form modal opened by event");
            delegate.addButton("Change", this::onChange);
            delegate.addCloseButton("Cancel");
        });
    }

    private void onChange(AjaxRequestTarget target, OpenModalEvent.ModalDelegate<SIString> delegate, IModel<SIString> model) {
        textModel.setObject(model.getObject().getValue());
        target.add(textLabel);
        delegate.close(target);
    }

    private RefType newRefType() {
        return RefType.of(() -> {
            SDictionary dict = SDictionary.create();
            PackageBuilder pkg = dict.createNewPackage("pkg");
            STypeString textType = pkg.createType("text", STypeString.class);
            textType.asAtr().label("Text").required(true);
            return textType;
        });
    }
}
