package org.opensingular.formsamples.crud;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.opensingular.formsamples.crud.ui.page.CRUDSamplePage;
import org.opensingular.formsamples.crud.ui.page.SampleHeader;
import org.opensingular.formsamples.crud.ui.page.decorator.DecoratorsSamplePage;
import org.opensingular.lib.wicket.util.template.admin.SingularAdminApp;
import org.opensingular.lib.wicket.util.template.admin.SingularAdminTemplate;

public class CRUDSampleApp extends WebApplication implements SingularAdminApp {

    private final static class OuputMarkupBehavior extends Behavior {
        @Override
        public void onConfigure(Component comp) {
            if (!comp.getRenderBodyOnly()) {
                comp
                        .setOutputMarkupId(true)
                        .setOutputMarkupPlaceholderTag(true);
            }
        }
    }

    @Override
    protected void init() {
        super.init();

        getMarkupSettings().setStripWicketTags(true);
        getMarkupSettings().setStripComments(true);

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        getComponentInitializationListeners().add(comp -> comp.add(new OuputMarkupBehavior()));

        mountPage("decorator", DecoratorsSamplePage.class);
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return CRUDSamplePage.class;
    }

    public static CRUDSampleApp get() {
        return (CRUDSampleApp) WebApplication.get();
    }

    @Override
    public MarkupContainer buildPageHeader(String id, boolean withMenu, SingularAdminTemplate adminTemplate) {
        return new SampleHeader(id);
    }
}
