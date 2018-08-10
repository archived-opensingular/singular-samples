package org.opensingular.singular.form.showcase.component;

import org.apache.wicket.Component;

import javax.annotation.Nonnull;

/**
 * Represents a usage example for a Wicket component.
 *
 * @author Daniel C. Bordin
 * @since 2018-07-27
 */
public class CaseBaseWicket extends CaseBase<Component> {

    public CaseBaseWicket(@Nonnull Class<? extends Component> target) {
        super(target);
        addHtmlReference(target);
    }

    /** Add the html of the component,if it exists, to the list of source to be displayed. */
    private void addHtmlReference(@Nonnull Class<? extends Component> aClass) {
        ResourceRef.forClassWithExtension(aClass, "html").ifPresent(ref -> getAdditionalSources().add(ref));
    }
}
