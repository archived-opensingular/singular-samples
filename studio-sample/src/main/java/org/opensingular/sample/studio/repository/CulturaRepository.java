package org.opensingular.sample.studio.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studio.dao.CulturaDAO;
import org.opensingular.sample.studio.entity.CulturaEntity;
import org.opensingular.sample.studio.form.Cultura;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("culturaRepository")
public class CulturaRepository extends SpringFormPersistenceInMemory<Cultura, SIComposite>
        implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private CulturaDAO culturaDAO;

    public CulturaRepository() {
        super(Cultura.class);
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (CulturaEntity c : culturaDAO.listAll()) {
            SIComposite instance = createInstance();
            instance.setValue("nome", c.getNome());
            insert(instance, null);
        }
    }
}
