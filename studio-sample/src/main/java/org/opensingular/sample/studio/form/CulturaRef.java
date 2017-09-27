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
import org.opensingular.sample.studio.repository.CulturaRepository;

@SInfoType(name = "CulturaRef", spackage = ResiduoPackage.class)
public class CulturaRef extends STypeRef<SIComposite> {
	@Inject
	private CulturaRepository culturaRepository;

	@Override
	protected String getKeyValue(SIComposite instance) {
		return FormKey.fromInstance(instance).toStringPersistence();
	}

	@Override
	protected String getDisplayValue(SIComposite instance) {
		return instance.getValue(Cultura.class, c -> c.nome);
	}

	@Override
	protected List<SIComposite> loadValues(SDocument document) {
		return culturaRepository.loadAll();
	}

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
    		super.onLoadType(tb);
    		// relational mapping
    		key.asSQL()
    				.column("CO_CULTURA")
    				.columnConverter(IntegerConverter::new);
    		display.asSQL()
    				.foreignColumn("NO_CULTURA", "CO_CULTURA", Cultura.class);
    }
}
