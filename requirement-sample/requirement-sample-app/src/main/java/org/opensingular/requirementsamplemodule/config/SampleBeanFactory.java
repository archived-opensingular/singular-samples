package org.opensingular.requirementsamplemodule.config;

import org.opensingular.lib.wicket.SingularWebResourcesFactory;
import org.opensingular.requirement.studio.spring.RequirementStudioBeanFactory;
import org.opensingular.resources.DefaulSingularWebResourcesFactory;
import org.opensingular.resources.SampleResourcesScope;
import org.springframework.context.annotation.Bean;

public class SampleBeanFactory extends RequirementStudioBeanFactory {
    @Bean
    public SingularWebResourcesFactory singularWebResourcesFactory() {
        return new DefaulSingularWebResourcesFactory(new SampleResourcesScope());
    }
}