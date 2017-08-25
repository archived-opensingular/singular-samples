package org.opensingular.samples.studio.cfg;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studiop.form.STFerramenta;
import org.opensingular.sample.studiop.form.STPresente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"org.opensingular.samples.studio", "org.opensingular.lib.support.spring.util"})
public class StudioSampleSpringConfig {

    @Bean(name = "presenteRepository")
    public SpringFormPersistenceInMemory<STPresente, SIComposite> presenteRepository() {
        return new SpringFormPersistenceInMemory<>(STPresente.class);
    }

    @Bean(name = "ferramentaRepository")
    public SpringFormPersistenceInMemory<STFerramenta, SIComposite> ferramentaRepository() {
        return new SpringFormPersistenceInMemory<>(STFerramenta.class);
    }

}
