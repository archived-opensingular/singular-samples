package org.opensingular.singular.form.showcase.component.internal.modal.wicket;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.opensingular.lib.commons.lambda.IFunction;
import org.opensingular.lib.wicket.util.bootstrap.layout.BSContainer;
import org.opensingular.lib.wicket.util.modal.BSModalBorder.ButtonStyle;
import org.opensingular.lib.wicket.util.modal.BSModalEventListenerBehavior;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent.ConfigureCallback;
import org.opensingular.lib.wicket.util.modal.OpenModalEvent.ModalDelegate;
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

        add(new AjaxLink<Void>("simpleFormLink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                openModal(target, Model.of(new SimpleTO()));
            }
        });
    }

    private void openModal(AjaxRequestTarget target, IModel<SimpleTO> model) {
        IFunction<String, Component> bodyContentFactory = id -> newBodyFragment(id, model);
        ConfigureCallback<SimpleTO> configureCallback = (ConfigureCallback<SimpleTO>) md -> {
            md.setTitle("Simple Fragment");
            md.addButton("Accept", (t, d, m) -> onAccept(d, t, m));
            md.addButton("AaR", Model.of("Accept and repeat"), ButtonStyle.DEFAULT, (t, d, m) -> {
                onAccept(d, t, m);
                openModal(t, Model.of(new SimpleTO()));
            });
            md.addCloseLink("Close");
        };
        new OpenModalEvent<>(target, model, bodyContentFactory, configureCallback).bubble(this);
    }

    protected Component newBodyFragment(String id, IModel<SimpleTO> model) {
        return new Fragment(id, "BodyFragment", this, new CompoundPropertyModel<>(model))
            .add(new Form<>("form")
                .add(new FeedbackPanel("feedback"))
                .add(new NumberTextField<>("code", Long.class)
                    .setLabel(Model.of("Code"))
                    .setRequired(true))
                .add(new TextField<>("description")
                    .setLabel(Model.of("Description"))
                    .setRequired(true))
                .add(new AjaxLink<SimpleTO>("open") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        openModal(target, Model.of(new SimpleTO()));
                    }
                }));
    }

    private void onAccept(ModalDelegate<SimpleTO> d, AjaxRequestTarget t, IModel<SimpleTO> model) {
        t.appendJavaScript("alert('" + model.getObject() + "');");
        d.close(t);
    }

    public static class SimpleTO implements Serializable {
        public Long   code;
        public String description;
        @Override
        public String toString() {
            return "[" + code + "] " + description;
        }
    }
}
