package org.opensingular.samples.studio.cfg;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studio.form.residuo.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"org.opensingular.samples.studio", "org.opensingular.lib.support.spring.util"})
public class StudioSampleSpringConfig {

    @Bean(name = "culturaRepository")
    public SpringFormPersistenceInMemory<Cultura, SIComposite> culturaRepository() {
        return new SpringFormPersistenceInMemory<>(Cultura.class);
    }

    @Bean(name = "normaRepository")
    public SpringFormPersistenceInMemory<Norma, SIComposite> normaRepository() {
        return new SpringFormPersistenceInMemory<>(Norma.class);
    }

    @Bean(name = "tipoDoseRepository")
    public SpringFormPersistenceInMemory<TipoDose, SIComposite> tipoDoseRepository() {
        return new SpringFormPersistenceInMemory<>(TipoDose.class);
    }

    @Bean(name = "modalidadeDeEmpregoRepository")
    public SpringFormPersistenceInMemory<ModalidadeDeEmprego, SIComposite> modalidadeDeEmpregoRepository() {
        return new SpringFormPersistenceInMemory<>(ModalidadeDeEmprego.class);
    }

    @Bean(name = "estudoResiduoRepository")
    public SpringFormPersistenceInMemory<EstudoResiduo, SIComposite> estudoResiduoRepository() {
        return new SpringFormPersistenceInMemory<>(EstudoResiduo.class);
    }
}
