package org.opensingular.sample.studio.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studio.dao.NormaDAO;
import org.opensingular.sample.studio.entity.NormaEntity;
import org.opensingular.sample.studio.form.Norma;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("normaRepository")
public class NormaRepository extends SpringFormPersistenceInMemory<Norma, SIComposite>
        implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private NormaDAO normaDAO;

    public NormaRepository() {
        super(Norma.class);
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (NormaEntity n : normaDAO.listAll()) {
            SIComposite instance = createInstance();
            instance.setValue("nome", n.getNome());
            insert(instance, null);
        }
    }
}