package org.opensingular.sample.studio.form;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

@SInfoType(name = "ModalidadeDeEmprego", spackage = ResiduoPackage.class)
public class ModalidadeDeEmprego extends STypeComposite<SIComposite> {

    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addField("nome", STypeString.class);
        nome.asAtr().label("Nome da modalidade de emprego").asAtrBootstrap().colPreference(12);
        nome.asAtr().required();
		// relational mapping
        this.asSQL()
                .table("TD_MODALIDADE_EMPREGO")
                .tablePK("CO_SEQ_MODALIDADE_EMPREGO");
        nome.asSQL()
                .column("NO_MODALIDADE_EMPREGO");
    }
}
