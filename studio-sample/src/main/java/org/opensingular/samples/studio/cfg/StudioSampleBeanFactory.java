package org.opensingular.samples.studio.cfg;

import org.opensingular.form.SFormUtil;
import org.opensingular.form.SIComposite;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.form.spring.SpringSDocumentFactoryEmpty;
import org.opensingular.form.spring.SpringServiceRegistry;
import org.opensingular.lib.context.singleton.SpringBoundedSingletonStrategy;
import org.opensingular.sample.studiop.form.STFerramenta;
import org.opensingular.sample.studiop.form.STPresente;
import org.opensingular.sample.studiop.form.StudioPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@ComponentScan(value = {"org.opensingular.samples.studio", "org.opensingular.lib.support.spring.util"})
public class StudioSampleBeanFactory {

    @Bean
    public SpringBoundedSingletonStrategy springBoundedSingletonStrategy() {
        return new SpringBoundedSingletonStrategy();
    }

    @Bean
    public SpringSDocumentFactoryEmpty springSDocumentFactoryEmpty() {
        return new SpringSDocumentFactoryEmpty();
    }

    @Bean
    public SpringServiceRegistry springServiceRegistry() {
        return new SpringServiceRegistry();
    }

    @Bean(name = "presenteRepository")
    public SpringFormPersistenceInMemory<STPresente, SIComposite> presenteRepository(SDocumentFactory documentFactory) {
        SpringFormPersistenceInMemory<STPresente, SIComposite> s = new SpringFormPersistenceInMemory<>();
        s.setTypeFullName(SFormUtil.getTypeName(STPresente.class));
        s.setDocumentFactory(documentFactory);
        s.setPackageClasses(Collections.singletonList(StudioPackage.class));
        return s;
    }

    @Bean(name = "ferramentaRepository")
    public SpringFormPersistenceInMemory<STFerramenta, SIComposite> ferramentaRepository(SDocumentFactory documentFactory) {
        SpringFormPersistenceInMemory<STFerramenta, SIComposite> s = new SpringFormPersistenceInMemory<>();
        s.setTypeFullName(SFormUtil.getTypeName(STFerramenta.class));
        s.setDocumentFactory(documentFactory);
        s.setPackageClasses(Collections.singletonList(StudioPackage.class));
        return s;
    }

}
