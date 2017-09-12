package org.opensingular.requirementsamplemodule.config;

import org.opensingular.server.commons.config.SingularSpringWebMVCConfig;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@EnableWebMvc
public class StudioSingularSpringWebMVCConfig extends SingularSpringWebMVCConfig {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }
}