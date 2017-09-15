package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(name = "TipoDose", spackage = ResiduoPackage.class)
public class TipoDose  extends STypeComposite<SIComposite> {

    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addField("nome", STypeString.class);
        nome.asAtr().label("Nome do tipo de dose").asAtrBootstrap().colPreference(12);
        nome.asAtr().required();
		// relational mapping
        this.asSQL()
                .table("TD_TIPO_DOSE")
                .tablePK("CO_SEQ_TIPO_DOSE")
                .tableRefColumn("NO_TIPO_DOSE");
        nome.asSQL()
                .column("NO_TIPO_DOSE");
    }
}
