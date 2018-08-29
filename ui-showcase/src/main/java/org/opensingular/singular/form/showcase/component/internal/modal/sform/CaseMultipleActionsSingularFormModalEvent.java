package org.opensingular.singular.form.showcase.component.internal.modal.sform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.opensingular.form.SIComposite;
import org.opensingular.form.wicket.modal.OpenSingularFormModalEvent;
import org.opensingular.form.wicket.panel.SFormModalEventListenerBehavior;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSContainer;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent.ConfigureCallback;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

/**
 * Opening a modal window populated with wicket components by bubbling an event to a listening parent container
 *
 * @author Ronald Tetsuo Miura
 * @since 2018-08-02
 */
@CaseItem(componentName = "Open Singular Form Modal Event", subCaseName = "Multiple Actions", group = Group.MODAL,
        resources = {
            @Resource(STCodDesc.class),
            @Resource(SimpleFormPackage.class)
        })
public class CaseMultipleActionsSingularFormModalEvent extends Panel {

    private List<CodDesc> items = new ArrayList<>();

    public CaseMultipleActionsSingularFormModalEvent(String id) {
        super(id);

        // Basic setup
        BSContainer<?> modalItemsContainer = new BSContainer<>("modalItemsContainer");
        add(modalItemsContainer);
        add(new SFormModalEventListenerBehavior(modalItemsContainer)); // required listener

        add(new ListView<CodDesc>("items", items) {
            @Override
            protected void populateItem(ListItem<CodDesc> item) {
                item
                    .add(new Label("code"))
                    .add(new Label("description"))
                    .setDefaultModel(new CompoundPropertyModel<>(item.getModel()));
            }
        });

        add(new AjaxLink<Void>("newItemLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                openNewItemModal(target);
            }
        });
    }

    private void openNewItemModal(AjaxRequestTarget target) {
        ConfigureCallback<SIComposite> configureCallback = modalDelegate -> {
            modalDelegate.setTitle("New Item");
            modalDelegate.addButton("Add and Close", (t, d, m) -> {
                if (!m.getObject().hasNestedValidationErrors()) {
                    onAddItem(t, m);
                    d.close(t);
                }
            });
            modalDelegate.addButton("Add and Next...", (t, d, m) -> {
                if (!m.getObject().hasNestedValidationErrors()) {
                    onAddItem(t, m);
                    d.close(t);
                    openNewItemModal(t);
                }
            });
            modalDelegate.addCloseLink("Cancel");
        };
        new OpenSingularFormModalEvent<>(target, STCodDesc.class, configureCallback).bubble(this);
    }

    private void onAddItem(AjaxRequestTarget t, IModel<SIComposite> model) {
        SIComposite codDesc = model.getObject();
        items.add(new CodDesc(
            codDesc.getValueInteger("code"),
            codDesc.getValueString("description")));
        t.add(this);
    }

    public static class CodDesc implements Serializable {
        public int    code;
        public String description;
        public CodDesc() {
        }
        public CodDesc(int code, String description) {
            this.code = code;
            this.description = description;
        }
    }
}
