package org.sample.form;

import java.util.Date;
import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.STypeTime;
import org.opensingular.form.view.SViewCheckBoxLabelAbove;
import org.opensingular.lib.commons.ui.Alignment;
import org.opensingular.lib.commons.util.Loggable;

@SInfoType(name = "ListaExemplo", spackage = RequirementsamplePackage.class)
public class STypeListaExemplo extends STypeComposite<SIComposite> implements Loggable {

    public STypeString nome2;
    public STypeString  sobrenome2;
    public STypeString  nomeMae2;
    public STypeString  nomeGato2;
    public STypeBoolean aceitaTermos2;
    public STypeBoolean teste;
    public STypeTime time;
    public STypeDate date;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome2 = this.addFieldString("nome2");
        nome2.asAtr().label("Nome");
        nomeGato2 = this.addFieldString("nomeGato2");
        nomeGato2.asAtr().label("Nome Gato");

        aceitaTermos2 = this.addFieldBoolean("aceitaTermos2");
        aceitaTermos2
                .withRadioView("Aceito", "Rejeito")
                .asAtr().label("Aceito os termos e condiçoes").required(true);;

        nome2.asAtrAnnotation().setAnnotated();

        sobrenome2 = this.addFieldString("sobrenome2");
        sobrenome2.asAtr().label("Sobrenome").required(true);
        sobrenome2.asAtrAnnotation().setAnnotated();
        nomeMae2 = this.addFieldString("nomeMae2");
        nomeMae2.asAtr().label("Nome Mãe").required(true);


        date = this.addFieldDate("date");
        date.asAtr().label("date").subtitle("min date").required();
        date.maxDate(new Date());
        Date dateNow = new Date();
        date.minDate(dateNow);


        nomeMae2.asAtrAnnotation().setAnnotated();

        teste = this.addFieldBoolean("teste");

        SViewCheckBoxLabelAbove sView = new SViewCheckBoxLabelAbove();
        sView.setAlignCheckBox(Alignment.CENTER);
        teste.withView(sView);
        teste.asAtr().label("Teste boolean").required(true);

        time = this.addFieldTime("time");
        time.asAtr().label("tempo");

        nomeMae2.asAtr().dependsOn(time).visible(false);


    }
}
