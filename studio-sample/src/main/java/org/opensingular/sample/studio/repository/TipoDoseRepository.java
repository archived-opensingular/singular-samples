package org.opensingular.sample.studio.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studio.dao.TipoDoseDAO;
import org.opensingular.sample.studio.entity.TipoDoseEntity;
import org.opensingular.sample.studio.form.TipoDose;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("tipoDoseRepository")
public class TipoDoseRepository extends SpringFormPersistenceInMemory<TipoDose, SIComposite>
        implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private TipoDoseDAO tipoDoseDAO;

    public TipoDoseRepository() {
        super(TipoDose.class);
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (TipoDoseEntity t : tipoDoseDAO.listAll()) {
            SIComposite instance = createInstance();
            instance.setValue("nome", t.getNome());
            insert(instance, null);
        }
    }
}