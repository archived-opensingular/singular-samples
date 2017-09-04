package org.opensingular.sample.studio.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studio.dao.ModalidadeEmpregoDAO;
import org.opensingular.sample.studio.entity.ModalidadeEmpregoEntity;
import org.opensingular.sample.studio.form.ModalidadeDeEmprego;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("modalidadeDeEmpregoRepository")
public class ModalidadeEmpregoRepository extends SpringFormPersistenceInMemory<ModalidadeDeEmprego, SIComposite>
        implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private ModalidadeEmpregoDAO modalidadeEmpregoDAO;

    public ModalidadeEmpregoRepository() {
        super(ModalidadeDeEmprego.class);
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (ModalidadeEmpregoEntity m : modalidadeEmpregoDAO.listAll()) {
            SIComposite instance = createInstance();
            instance.setValue("nome", m.getNome());
            insert(instance, null);
        }
    }
}