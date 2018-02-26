package org.opensingular.singular.form.showcase.studio.persistence.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeCarrinhoCompra;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CarrinhoFormRepository extends SpringFormPersistenceInMemory<STypeComposite<SIComposite>, SIComposite> {

    public CarrinhoFormRepository() {
        super(STypeCarrinhoCompra.class);
    }
}