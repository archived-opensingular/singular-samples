package org.opensingular.singular.form.showcase.component.internal.modal.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.opensingular.lib.commons.lambda.IFunction;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSContainer;
import org.opensingular.lib.wicket.util.modal.BSModalEventListenerBehavior;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent.ConfigureCallback;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

/**
 * Opening a modal window populated with wicket components by bubbling an event to a listening parent container
 *
 * @author Ronald Tetsuo Miura
 * @since 2018-08-02
 */
@CaseItem(componentName = "Open Modal Event", subCaseName = "Stacking modals", group = Group.MODAL)
public class CaseStackingWicketModalEvent extends Panel {

    public CaseStackingWicketModalEvent(String id) {
        super(id);

        // Basic setup
        BSContainer<?> modalItemsContainer = new BSContainer<>("modalItemsContainer");
        add(modalItemsContainer);
        add(new BSModalEventListenerBehavior(modalItemsContainer)); // required listener

        add(new AjaxLink<Void>("open") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                openModal(target, 1);
            }
        });
    }

    private void openModal(AjaxRequestTarget target, int count) {
        IFunction<String, Component> bodyContentFactory = id -> newBodyFragment(id, count);
        ConfigureCallback<Integer> configureCallback = (ConfigureCallback<Integer>) md -> {
            md.setTitle("Simple Fragment");
            md.addButton("Open...", (t, d, m) -> openModal(t, count + 1));
            md.addCloseLink("Close");
        };
        new OpenModalEvent<>(target, Model.of(count), bodyContentFactory, configureCallback).bubble(this);
    }

    protected Component newBodyFragment(String id, int count) {
        return new Fragment(id, "BodyFragment", this)
            .add(new Label("count", count));
    }
}
