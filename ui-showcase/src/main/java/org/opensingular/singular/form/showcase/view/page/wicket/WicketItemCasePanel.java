package org.opensingular.singular.form.showcase.view.page.wicket;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.opensingular.singular.form.showcase.ShowCaseException;
import org.opensingular.singular.form.showcase.component.CaseBaseWicket;
import org.opensingular.singular.form.showcase.view.page.ItemCasePanel;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;

/**
 * Displays the {@link CaseBaseWicket}.
 *
 * @author Daniel C. Bordin
 * @since 2018-07-27
 */
public class WicketItemCasePanel extends ItemCasePanel<CaseBaseWicket> {

    public WicketItemCasePanel(String id, IModel<CaseBaseWicket> caseBase) {
        super(id, caseBase);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Component c = createTargetComponent("content");
        add(c);
    }

    /** Creates the the wicket component target of the example. */
    @Nonnull
    private Component createTargetComponent(@Nonnull String id) {
        Class<? extends Component> cl = getCaseBase().getObject().getCaseClass();
        Constructor<? extends Component> constructor;
        try {
            constructor = cl.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new ShowCaseException("The example class '" + cl.getSimpleName() +
                    "' doesn't have public constructor receiving String as a parameter", e);
        }
        try {
            return constructor.newInstance(id);
        } catch (Exception e) {
            throw new ShowCaseException("Error creating component", e);
        }
    }
}
