package org.opensingular.singular.form.showcase.studio.persistence.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeEndereco;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EnderecoFormRepository extends SpringFormPersistenceInMemory<STypeComposite<SIComposite>, SIComposite> {

    public EnderecoFormRepository() {
        super(STypeEndereco.class);
    }
}