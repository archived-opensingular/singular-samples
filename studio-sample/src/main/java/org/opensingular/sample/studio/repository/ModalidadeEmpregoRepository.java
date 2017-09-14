package org.opensingular.sample.studio.repository;

import javax.inject.Inject;
import javax.inject.Named;

import org.opensingular.form.SIComposite;
import org.opensingular.form.document.SDocumentFactory;
import org.opensingular.form.persistence.FormPersistenceInRelationalDB;
import org.opensingular.form.persistence.RelationalDatabase;
import org.opensingular.sample.studio.form.ModalidadeDeEmprego;

@Named("modalidadeDeEmpregoRepository")
public class ModalidadeEmpregoRepository extends FormPersistenceInRelationalDB<ModalidadeDeEmprego, SIComposite> {

	@Inject
	public ModalidadeEmpregoRepository(RelationalDatabase db, SDocumentFactory documentFactory) {
		super(db, documentFactory, ModalidadeDeEmprego.class);
	}
}