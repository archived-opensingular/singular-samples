package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.document.SDocument;
import org.opensingular.form.type.ref.STypeRef;
import org.opensingular.sample.studio.repository.ModalidadeEmpregoRepository;

import javax.inject.Inject;
import java.util.List;

@SInfoType(name = "ModalidadeEmpregoRef", spackage = ResiduoPackage.class)
public class ModalidadeEmpregoRef extends STypeRef<SIComposite> {
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
    protected List<SIComposite> loadValues(SDocument document) {
        return modalidadeEmpregoRepository.loadAll();
    }
}
