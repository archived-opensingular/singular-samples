package org.opensingular.sample.studio.form;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.document.SDocument;
import org.opensingular.form.persistence.FormKey;
import org.opensingular.form.persistence.relational.IntegerConverter;
import org.opensingular.form.type.ref.STypeRef;
import org.opensingular.sample.studio.repository.NormaRepository;

@SInfoType(name = "NormaRef", spackage = ResiduoPackage.class)
public class NormaRef extends STypeRef<SIComposite> {
    @Inject
    private NormaRepository normaRepository;

    @Override
    protected String getKeyValue(SIComposite instance) {
		return FormKey.fromInstance(instance).toStringPersistence();
    }

    @Override
    protected String getDisplayValue(SIComposite instance) {
        return instance.getValue(Norma.class, c -> c.nome);
    }

    @Override
    protected List<SIComposite> loadValues(SDocument document) {
        return normaRepository.loadAll();
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
    		super.onLoadType(tb);
    		// relational mapping
    		key.asSQL()
    				.column("CO_NORMA")
    				.columnConverter(IntegerConverter::new);
    		display.asSQL()
    				.foreignColumn("NO_NORMA", "CO_NORMA", Norma.class);
    }
}
