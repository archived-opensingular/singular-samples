package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;

import javax.annotation.Nonnull;

@SInfoType(name = "Metabolito", spackage = ResiduoPackage.class)
public class Metabolito extends STypeComposite<SIComposite> {

    public STypeInteger loq;
    public STypeInteger quantidadeResiduoEncontrado;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        loq = addField("loq", STypeInteger.class);
        quantidadeResiduoEncontrado = addField("quantidadeResiduoEncontrado", STypeInteger.class);
        loq.asAtr().required().label("LOQ").asAtrBootstrap().colPreference(6);
        quantidadeResiduoEncontrado.asAtr().required().label("Residuo Encontrado").asAtrBootstrap().colPreference(6);
    }
}