package org.opensingular.singular.form.showcase.component.internal.modal.wicket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
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
@CaseItem(componentName = "Open Modal Event", subCaseName = "Simple modal", group = Group.MODAL)
public class CaseSimpleWicketModalEvent extends Panel {

    private final IModel<String> textModel           = new Model<>("...");
    private final BSContainer<?> modalItemsContainer = new BSContainer<>("modalItemsContainer");
    private final Label          textLabel           = new Label("textLabel", textModel);

    public CaseSimpleWicketModalEvent(String id) {
        super(id);

        // Basic setup
        add(modalItemsContainer);
        add(new BSModalEventListenerBehavior(modalItemsContainer)); // required listener

        add(textLabel);
        add(new AjaxLink<Void>("change") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                newOpenModalEvent(target).bubble(this);
            }
        });
    }

    private OpenModalEvent<String> newOpenModalEvent(AjaxRequestTarget target) {
        return new OpenModalEvent<>(
            target,
            textModel,
            id -> new MyModalFragment(id, textModel),
            delegate -> {
                delegate.setTitle("Wicket modal opened by event");
                delegate.addButton("Change", this::onChange);
                delegate.addCloseButton("Cancel");
            });
    }

    private void onChange(AjaxRequestTarget target, OpenModalEvent.ModalDelegate<String> delegate, IModel<String> model) {
        target.add(textLabel);
        delegate.close(target);
    }

    private class MyModalFragment extends Fragment {
        public MyModalFragment(String id, IModel<String> model) {
            super(id, "MyModalFragment", CaseSimpleWicketModalEvent.this);
            add(new TextField<>("text", model));
        }
    }
}
