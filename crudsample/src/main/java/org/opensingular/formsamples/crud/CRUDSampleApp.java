package org.opensingular.formsamples.crud;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.opensingular.formsamples.crud.ui.page.CRUDSamplePage;

public class CRUDSampleApp extends WebApplication {

    @Override
    protected void init() {
        super.init();
        
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
    
    @Override
    public Class<? extends Page> getHomePage() {
        return CRUDSamplePage.class;
    }

    public static CRUDSampleApp get() {
        return (CRUDSampleApp) WebApplication.get();
    }
}
