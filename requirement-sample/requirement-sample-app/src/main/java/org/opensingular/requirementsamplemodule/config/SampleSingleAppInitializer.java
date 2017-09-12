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
import org.opensingular.server.p.commons.config.PWebInitializer;
import org.opensingular.server.single.config.SingleAppInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.function.Predicate;

public class SampleSingleAppInitializer implements SingleAppInitializer {
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
            protected IServerContext[] serverContexts() {
                return StudioRequirementContext.values();
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
                if (sameContextCheck.test(StudioRequirementContext.WORKLIST)) {
                    return AnalysisApplication.class;
                }
                if (sameContextCheck.test(StudioRequirementContext.REQUIREMENT)) {
                    return PetitionApplication.class;
                }
                if (sameContextCheck.test(StudioRequirementContext.ADMINISTRATION)) {
                    return AdministrationApplication.class;
                }
                if (sameContextCheck.test(StudioRequirementContext.ROOT)) {
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
}