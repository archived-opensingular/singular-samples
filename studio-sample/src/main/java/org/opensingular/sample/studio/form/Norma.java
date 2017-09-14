package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(name = "Norma", spackage = ResiduoPackage.class)
public class Norma extends STypeComposite<SIComposite> {

    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addField("nome", STypeString.class);
        nome.asAtr().label("Nome da norma").asAtrBootstrap().colPreference(12);
        nome.asAtr().required();
		// relational mapping
        this.asSQL()
                .table("TD_NORMA")
                .tablePK("CO_SEQ_NORMA");
        nome.asSQL()
                .column("NO_NORMA");
    }
}
