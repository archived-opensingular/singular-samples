package org.opensingular.sample.studio.form.residuo;


import org.opensingular.form.*;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByMasterDetail;

import javax.annotation.Nonnull;

@SInfoType(name = "Ensaio", spackage = ResiduoPackage.class)
public class Ensaio extends STypeComposite<SIComposite> {

    public STypeString cidade;
    public STypeString codigo;
    public STypeList<Amostra, SIComposite> amostras;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        codigo = addField("codigo", STypeString.class);
        cidade = addField("cidade", STypeString.class);
        amostras = addFieldListOf("amostras", Amostra.class);
        codigo.asAtr().label("Código").asAtrBootstrap().colPreference(6);
        cidade.asAtr().label("Cidade").asAtrBootstrap().colPreference(6);
        Amostra amostra = amostras.getElementsType();
        amostras.asAtr().label("Amostra").asAtrBootstrap().colPreference(12);
        amostras.withView(new SViewListByMasterDetail(), view ->
            view.col(amostra.codigo, "Código")
                .col(amostra.dose, "Dose")
                .col(amostra.dat, "DAT")
                .col(amostra.loq, "LOQ")
        );
    }
}