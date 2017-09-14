package org.opensingular.sample.studio.form;

import java.util.List;

import javax.inject.Inject;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.document.SDocument;
import org.opensingular.form.persistence.FormKey;
import org.opensingular.form.type.ref.STypeRef;
import org.opensingular.sample.studio.repository.TipoDoseRepository;

@SInfoType(name = "TipoDoseRef", spackage = ResiduoPackage.class)
public class TipoDoseRef extends STypeRef<SIComposite> {
    @Inject
    private TipoDoseRepository tipoDoseRepository;

    @Override
    protected String getKeyValue(SIComposite instance) {
		return FormKey.fromInstance(instance).toStringPersistence();
    }

    @Override
    protected String getDisplayValue(SIComposite instance) {
        return instance.getValue(TipoDose.class, c -> c.nome);
    }

    @Override
    protected List<SIComposite> loadValues(SDocument document) {
        return tipoDoseRepository.loadAll();
    }
}