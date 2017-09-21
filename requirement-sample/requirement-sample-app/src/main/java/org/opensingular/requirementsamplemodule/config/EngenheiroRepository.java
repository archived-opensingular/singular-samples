package org.opensingular.requirementsamplemodule.config;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.requirementsamplemodule.form.EngenheiroForm;

import javax.inject.Named;

@Named
public class EngenheiroRepository extends SpringFormPersistenceInMemory<EngenheiroForm, SIComposite> {
    public EngenheiroRepository() {
        super(EngenheiroForm.class);
    }
}