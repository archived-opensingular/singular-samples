package org.opensingular.sample.studio.form;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.document.SDocument;
import org.opensingular.form.persistence.FormKeyRelational;
import org.opensingular.form.persistence.relational.IntegerConverter;
import org.opensingular.form.type.ref.STypeRef;
import org.opensingular.sample.studio.repository.ModalidadeEmpregoRepository;

@SInfoType(name = "ModalidadeEmpregoRef", spackage = ResiduoPackage.class)
public class ModalidadeEmpregoRef extends STypeRef<SIComposite> {
	@Inject
	private ModalidadeEmpregoRepository modalidadeEmpregoRepository;

	@Override
	protected String getKeyValue(SIComposite instance) {
		return FormKeyRelational.columnValuefromInstance("CO_SEQ_MODALIDADE_EMPREGO", instance).toString();
	}

	@Override
	protected String getDisplayValue(SIComposite instance) {
		return instance.getValue(ModalidadeDeEmprego.class, c -> c.nome);
	}

	@Override
	protected List<SIComposite> loadValues(SDocument document) {
		return modalidadeEmpregoRepository.loadAll();
	}

	@Override
	protected void onLoadType(@Nonnull TypeBuilder tb) {
		super.onLoadType(tb);
		// relational mapping
		key.asSQL().column("CO_MODALIDADE_EMPREGO").columnConverter(IntegerConverter::new);
		display.asSQL().foreignColumn("NO_MODALIDADE_EMPREGO", "CO_MODALIDADE_EMPREGO", ModalidadeDeEmprego.class);
	}
}
