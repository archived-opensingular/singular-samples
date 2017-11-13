/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.singular.form.showcase.wicket;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Duration;
import org.opensingular.lib.commons.base.SingularProperties;
import org.opensingular.lib.support.spring.util.ApplicationContextProvider;
import org.opensingular.lib.wicket.util.application.SingularAnnotatedMountScanner;
import org.opensingular.lib.wicket.util.application.SkinnableApplication;
import org.opensingular.lib.wicket.util.page.error.Error403Page;
import org.opensingular.lib.wicket.util.template.SingularTemplate;
import org.opensingular.lib.wicket.util.template.admin.SingularAdminApp;
import org.opensingular.lib.wicket.util.template.admin.SingularAdminTemplate;
import org.opensingular.singular.form.showcase.view.page.form.ListPage;
import org.opensingular.singular.form.showcase.view.template.ShowcaseFooter;
import org.opensingular.singular.form.showcase.view.template.ShowcaseHeader;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class ShowcaseApplication extends AuthenticatedWebApplication implements SkinnableApplication, SingularAdminApp {

    public static final String BASE_FOLDER = "/tmp/fileUploader";


    public static ShowcaseApplication get() {
        return (ShowcaseApplication) WebApplication.get();
    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return ListPage.class;
    }

    @Override
    public void init() {
        super.init();

        getRequestCycleSettings().setTimeout(Duration.minutes(5));
        Locale.setDefault(new Locale("pt", "BR"));

        getApplicationSettings().setAccessDeniedPage(Error403Page.class);
        // in case you change this value, don't forget to check the configuration
        // for your application server. It must allow it.
        getApplicationSettings().setDefaultMaximumUploadSize(Bytes.megabytes(10));
        getApplicationSettings().setDefaultMaximumUploadSize(Bytes.MAX);

        getMarkupSettings().setStripWicketTags(true);
        getMarkupSettings().setStripComments(false);
        getMarkupSettings().setDefaultMarkupEncoding(StandardCharsets.UTF_8.name());
        getComponentOnConfigureListeners().add(component -> {
            boolean outputId = !component.getRenderBodyOnly();
            component.setOutputMarkupId(outputId).setOutputMarkupPlaceholderTag(outputId);
        });

        if (ApplicationContextProvider.isConfigured()) {
            getComponentInstantiationListeners().add(new SpringComponentInjector(this, ApplicationContextProvider.get(), true));
        } else {
            getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        }
        new SingularAnnotatedMountScanner().mountPages(this);

        setHeaderResponseDecorator(r -> new JavaScriptFilteredIntoFooterHeaderResponse(r, SingularTemplate.JAVASCRIPT_CONTAINER));

    }

    @Override
    public Session newSession(Request request, Response response) {
        return new UIAdminSession(request, response);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return UIAdminSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return ListPage.class;
    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        if (SingularProperties.get().isFalse(SingularProperties.SINGULAR_DEV_MODE)) {
            return RuntimeConfigurationType.DEPLOYMENT;
        } else {
            return RuntimeConfigurationType.DEVELOPMENT;
        }
    }

    @Override
    public MarkupContainer buildPageHeader(String id, boolean withMenu, SingularAdminTemplate adminTemplate) {
        return new ShowcaseHeader(id, withMenu, adminTemplate.getSkinOptions());
    }

    @Override
    public MarkupContainer buildPageFooter(String id) {
        return new ShowcaseFooter(id);
    }
}
