package org.opensingular.sample.studio.form;

import org.opensingular.form.*;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.util.SingularPredicates;
import org.opensingular.form.view.SViewListByMasterDetail;

import javax.annotation.Nonnull;

@SInfoType(name = "Amostra", spackage = ResiduoPackage.class)
public class Amostra extends STypeComposite<SIComposite> {

    public STypeString codigo;
    public STypeInteger dose;
    public STypeInteger dat;
    public STypeInteger loq;
    public STypeInteger quantidadeResiduoEncontrado;
    public STypeBoolean possuiMetabolito;
    public STypeList<Metabolito, SIComposite> metabolitos;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        codigo = addField("codigo", STypeString.class);
        dose = addField("dose", STypeInteger.class);
        dat = addField("dat", STypeInteger.class);
        loq = addField("loq", STypeInteger.class);
        quantidadeResiduoEncontrado = addField("quantidadeResiduoEncontrado", STypeInteger.class);
        possuiMetabolito = addField("possuiMetabolito", STypeBoolean.class);
        metabolitos = addFieldListOf("metabolitos", Metabolito.class);

        codigo.asAtr().required().label("Código").asAtrBootstrap().colPreference(4);
        dose.asAtr().required().label("Dose").asAtrBootstrap().colPreference(4);
        dat.asAtr().required().label("DAT").asAtrBootstrap().newRow().colPreference(4);
        loq.asAtr().required().label("LOQ").asAtrBootstrap().colPreference(4);
        quantidadeResiduoEncontrado.asAtr().required().label("Residuo Encontrado").asAtrBootstrap().colPreference(4);
        possuiMetabolito.asAtr().label("Possui metabolito").asAtrBootstrap().colPreference(12);
        Metabolito metabolito = metabolitos.getElementsType();
        metabolitos.asAtr()
                .dependsOn(possuiMetabolito)
                .exists(SingularPredicates.typeValueIsTrue(possuiMetabolito))
                .label("Metabolito");
        metabolitos.withView(new SViewListByMasterDetail(), view -> view
                .col(metabolito.loq, "LOQ")
                .col(metabolito.quantidadeResiduoEncontrado, "Residuo Encontrado")
        );
    }
}
