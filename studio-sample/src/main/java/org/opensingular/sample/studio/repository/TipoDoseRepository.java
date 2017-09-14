package org.opensingular.sample.studio.repository;

import javax.inject.Inject;
import javax.inject.Named;

import org.opensingular.form.SIComposite;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.persistence.FormPersistenceInRelationalDB;
import org.opensingular.form.persistence.RelationalDatabase;
import org.opensingular.sample.studio.form.TipoDose;

@Named("tipoDoseRepository")
public class TipoDoseRepository extends FormPersistenceInRelationalDB<TipoDose, SIComposite> {

	@Inject
	public TipoDoseRepository(RelationalDatabase db, SDocumentFactory documentFactory) {
		super(db, documentFactory, TipoDose.class);
	}

}