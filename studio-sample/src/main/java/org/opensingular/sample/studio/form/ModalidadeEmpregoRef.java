package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.form.type.ref.STypeRef;
import org.opensingular.sample.studio.repository.ModalidadeEmpregoRepository;

import javax.inject.Inject;

@SInfoType(name = "ModalidadeEmpregoRef", spackage = ResiduoPackage.class)
public class ModalidadeEmpregoRef extends STypeRef<ModalidadeDeEmprego, SIComposite> {
    @Inject
    private ModalidadeEmpregoRepository modalidadeEmpregoRepository;

    @Override
    protected String getKeyValue(SIComposite instance) {
        return instance.getValue(ModalidadeDeEmprego.class, c -> c.nome);
    }

    @Override
    protected String getDisplayValue(SIComposite instance) {
        return instance.getValue(ModalidadeDeEmprego.class, c -> c.nome);
    }

    @Override
    protected FormRespository<ModalidadeDeEmprego, SIComposite> getRepository() {
        return modalidadeEmpregoRepository;
    }

}
