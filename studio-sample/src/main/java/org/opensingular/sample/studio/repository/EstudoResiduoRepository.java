package org.opensingular.sample.studio.repository;

import javax.inject.Inject;
import javax.inject.Named;

import org.opensingular.form.SIComposite;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.persistence.FormPersistenceInRelationalDB;
import org.opensingular.form.persistence.RelationalDatabase;
import org.opensingular.sample.studio.form.EstudoResiduo;

@Named("estudoResiduoRepository")
public class EstudoResiduoRepository extends FormPersistenceInRelationalDB<EstudoResiduo, SIComposite> {

	@Inject
	public EstudoResiduoRepository(RelationalDatabase db, SDocumentFactory documentFactory) {
		super(db, documentFactory, EstudoResiduo.class);
	}

}
