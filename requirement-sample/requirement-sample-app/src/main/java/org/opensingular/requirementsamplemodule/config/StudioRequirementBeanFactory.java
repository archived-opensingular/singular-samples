package org.opensingular.requirementsamplemodule.config;

import org.opensingular.server.single.config.SingleAppBeanFactory;
import org.opensingular.studio.core.config.StudioConfig;
import org.opensingular.studio.core.config.StudioConfigProvider;
import org.opensingular.studio.core.menu.StudioMenu;
import org.springframework.context.annotation.Bean;

public class StudioRequirementBeanFactory extends SingleAppBeanFactory {
    private StudioConfig studioConfig;

    public StudioRequirementBeanFactory() {
        this.studioConfig = StudioConfigProvider.get().retrieve();
    }

    @Bean
    private StudioMenu studioMenu() {
        return studioConfig.getAppMenu();
    }
}