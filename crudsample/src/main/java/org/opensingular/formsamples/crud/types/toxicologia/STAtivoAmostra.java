package org.opensingular.formsamples.crud.types.toxicologia;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

@SInfoType(spackage = ToxicologiaPackage.class)
public class STAtivoAmostra extends STypeComposite<SIComposite> {

    public STypeString idAtivo;
    public STypeString nomeComumPortugues;

    @Override
    protected void onLoadType(TypeBuilder tb) {
        idAtivo = addFieldString("idAtivo");
        nomeComumPortugues = addFieldString("nomeComumPortugues");

        this.selection()
                .id(idAtivo)
                .display(nomeComumPortugues)
                .simpleProvider(builder -> {
                    builder.add()
                            .set(idAtivo, "1")
                            .set(nomeComumPortugues, "Ativo 1");

                });

        this.asAtrBootstrap().colPreference(4);
    }
}
