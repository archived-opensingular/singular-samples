package org.opensingular.singular.form.showcase.studio.persistence.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypePessoa;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PessoaFormRepository extends SpringFormPersistenceInMemory<STypeComposite<SIComposite>, SIComposite> {
    public PessoaFormRepository() {
        super(STypePessoa.class);
    }
}