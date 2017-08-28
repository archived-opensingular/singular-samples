package org.opensingular.sample.studio.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studio.form.EstudoResiduo;

import javax.inject.Named;

@Named("estudoResiduoRepository")
public class EstudoResiduoRepository extends SpringFormPersistenceInMemory<EstudoResiduo, SIComposite> {
    public EstudoResiduoRepository() {
        super(EstudoResiduo.class);
    }
}
