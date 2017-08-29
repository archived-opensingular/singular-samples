package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.form.type.ref.STypeRef;
import org.opensingular.sample.studio.repository.NormaRepository;

import javax.inject.Inject;

@SInfoType(name = "NormaRef", spackage = ResiduoPackage.class)
public class NormaRef extends STypeRef<Norma, SIComposite> {
    @Inject
    private NormaRepository normaRepository;

    @Override
    protected String getKeyValue(SIComposite instance) {
        return instance.getValue(Norma.class, c -> c.nome);
    }

    @Override
    protected String getDisplayValue(SIComposite instance) {
        return instance.getValue(Norma.class, c -> c.nome);
    }

    @Override
    protected FormRespository<Norma, SIComposite> getRepository() {
        return normaRepository;
    }

}
