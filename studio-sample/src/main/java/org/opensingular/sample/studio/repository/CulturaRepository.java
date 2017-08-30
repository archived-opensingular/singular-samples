package org.opensingular.sample.studio.repository;

import org.hibernate.SessionFactory;
import org.opensingular.form.SIComposite;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.persistence.relational.FormRepositoryHibernate;
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
public class CulturaRepository extends FormRepositoryHibernate<Cultura, SIComposite> {

    @Inject
    public CulturaRepository(SessionFactory sessionFactory, SDocumentFactory documentFactory) {
        super(sessionFactory, documentFactory, Cultura.class);
    }

}