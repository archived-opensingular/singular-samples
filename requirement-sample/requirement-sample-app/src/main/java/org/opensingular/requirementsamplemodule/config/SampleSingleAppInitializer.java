package org.opensingular.requirementsamplemodule.config;

import org.opensingular.lib.commons.lambda.IConsumer;
import org.opensingular.lib.wicket.util.template.SkinOptions;
import org.opensingular.requirementsamplemodule.spring.RequirementSamplePersistenceConfiguration;
import org.opensingular.server.commons.config.IServerContext;
import org.opensingular.server.commons.config.SingularSpringWebMVCConfig;
import org.opensingular.server.commons.exception.SingularServerException;
import org.opensingular.server.commons.spring.SingularDefaultBeanFactory;
import org.opensingular.server.commons.spring.SingularDefaultPersistenceConfiguration;
import org.opensingular.server.commons.wicket.SingularServerApplication;
import org.opensingular.server.p.commons.admin.AdministrationApplication;
import org.opensingular.server.p.commons.config.PServerContext;
import org.opensingular.server.p.commons.config.PSpringSecurityInitializer;
import org.opensingular.server.p.commons.config.PWebInitializer;
import org.opensingular.server.single.config.SingleAppInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.function.Predicate;

import static org.opensingular.server.p.commons.config.PServerContext.ADMINISTRATION;
import static org.opensingular.server.p.commons.config.PServerContext.REQUIREMENT;
import static org.opensingular.server.p.commons.config.PServerContext.WORKLIST;

public class SampleSingleAppInitializer implements SingleAppInitializer {

    public static PServerContext STUDIO = new PServerContext("STUDIO", "/*", "singular.studio");

    @Override
    public String moduleCod() {
        return "REQUIREMENTSAMPLE";
    }

    @Override
    public String[] springPackagesToScan() {
        return new String[]{"org.opensingular"};
    }

    @Override
    public Class<? extends SingularDefaultPersistenceConfiguration> persistenceConfiguration() {
        return RequirementSamplePersistenceConfiguration.class;
    }

    @Override
    public Class<? extends SingularDefaultBeanFactory> beanFactory() {
        return StudioRequirementBeanFactory.class;
    }

    @Override
    public PWebInitializer webConfiguration() {
        return new PWebInitializer() {

            @Override
            protected void configureCAS(ServletContext servletContext) {
            }

            @Override
            public IServerContext[] serverContexts() {
                return new IServerContext[]{REQUIREMENT, WORKLIST, ADMINISTRATION, STUDIO};
            }

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setAttribute(INITSKIN_CONSUMER_REQ_PARAM, (IConsumer<SkinOptions>) SampleSingleAppInitializer.this::initRequirementSkin);
                servletContext.setAttribute(INITSKIN_CONSUMER_ANL_PARAM, (IConsumer<SkinOptions>) SampleSingleAppInitializer.this::initAnalysisSkin);
                super.onStartup(servletContext);
            }

            @Override
            protected Class<? extends SingularServerApplication> getWicketApplicationClass(IServerContext currentContext) {
                Predicate<IServerContext> sameContextCheck = (i) -> i.isSameContext(currentContext);
                if (sameContextCheck.test(WORKLIST)) {
                    return AnalysisApplication.class;
                }
                if (sameContextCheck.test(REQUIREMENT)) {
                    return PetitionApplication.class;
                }
                if (sameContextCheck.test(ADMINISTRATION)) {
                    return AdministrationApplication.class;
                }
                if (sameContextCheck.test(STUDIO)) {
                    return StudioRequirementApplication.class;
                }
                throw new SingularServerException("Contexto inválido");
            }
        };
    }

    @Override
    public Class<? extends SingularSpringWebMVCConfig> getSingularSpringWebMVCConfig() {
        return StudioSingularSpringWebMVCConfig.class;
    }

    @Override
    public PSpringSecurityInitializer springSecurityConfiguration() {
        return new RequirementSpringSecurityInitializer();
    }
}