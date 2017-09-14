package org.opensingular.sample.studio.repository;

import javax.inject.Inject;
import javax.inject.Named;

import org.opensingular.form.SIComposite;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.persistence.FormPersistenceInRelationalDB;
import org.opensingular.form.persistence.RelationalDatabase;
import org.opensingular.sample.studio.form.Norma;

@Named("normaRepository")
public class NormaRepository extends FormPersistenceInRelationalDB<Norma, SIComposite> {

	@Inject
	public NormaRepository(RelationalDatabase db, SDocumentFactory documentFactory) {
		super(db, documentFactory, Norma.class);
	}

}