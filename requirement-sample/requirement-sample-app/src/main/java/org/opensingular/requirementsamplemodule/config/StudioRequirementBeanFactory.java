package org.opensingular.requirementsamplemodule.config;

import org.opensingular.form.SIComposite;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.requirementsamplemodule.form.EngenheiroForm;
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
    public StudioMenu studioMenu() {
        return studioConfig.getAppMenu();
    }

    @Bean(name = "engenheiroRepository")
    public FormRespository<EngenheiroForm, SIComposite> engenheiroRepository(){
        return new SpringFormPersistenceInMemory<>(EngenheiroForm.class);
    }
}