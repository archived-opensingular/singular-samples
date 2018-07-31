package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeLatitudeLongitudeGMaps;

import javax.annotation.Nonnull;

@SInfoType(name = "Exemplo2", spackage = RequirementsamplePackage.class)
public class STypeExemplo  extends STypeComposite<SIComposite> {


    public STypeString nome2;
    public STypeString nomeMae2;
    public STypeString nomeGato2;
    public STypeString nomeGato3;
    public STypeString nomeGato4;
    public STypeString nomeGato5;

    public STypeLatitudeLongitudeGMaps coordenada;
    public STypeLatitudeLongitudeGMaps coordenada2;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtrIndex().indexed(Boolean.TRUE);
        nome2 = this.addFieldString("nome2");
        nome2.asAtr().label("Nome");
        nomeGato2 = this.addFieldString("nomeGato2");
        nomeGato2.asAtr().label("Nome Gato");
        nomeGato3 = this.addFieldString("nomeGato3");
        coordenada2 = this.addField("coordenada2", STypeLatitudeLongitudeGMaps.class);
        coordenada2
                .asAtr().subtitle("subtitle maps").label("Maps").required();

        nomeGato3.asAtr().label("Nome Gato");
        nomeGato4 = this.addFieldString("nomeGato4");
        nomeGato4.asAtr().label("Nome Gato");
        nomeGato5 = this.addFieldString("nomeGato5");
        nomeGato5.asAtr().label("Nome Gato");



        nomeMae2 = this.addFieldString("nomeMae2");
        nomeMae2.asAtr().label("Nome Mãe");
        this.asAtr().label("EXEMPLO");
        coordenada = this.addField("coordenada", STypeLatitudeLongitudeGMaps.class);
        coordenada
                .asAtr().subtitle("subtitle maps").label("Maps").required();


    }

}
