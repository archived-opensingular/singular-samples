package org.sample.form;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeDateTime;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.STypeTime;
import org.opensingular.form.view.SViewCheckBox;
import org.opensingular.lib.commons.ui.Alignment;
import org.opensingular.form.util.SingularPredicates;
import org.opensingular.form.view.date.SViewDate;
import org.opensingular.form.view.date.SViewDateTime;
import org.opensingular.form.view.date.SViewTime;
import org.opensingular.lib.commons.util.Loggable;

@SInfoType(name = "ListaExemplo", spackage = RequirementsamplePackage.class)
public class STypeListaExemplo extends STypeComposite<SIComposite> implements Loggable {

    public STypeString nome2;
    public STypeString  sobrenome2;
    public STypeString  nomeMae2;
    public STypeString  nomeGato2;
    public STypeString  nomeDog2;
    public STypeDateTime dataHoraInicio;
    public STypeDate data;
    public STypeTime time;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome2 = this.addFieldString("nome2");
        nome2.asAtr().label("Nome");
        nomeGato2 = this.addFieldString("nomeGato2");
        nomeGato2.asAtr().label("Nome Gato");
        nomeDog2 = this.addFieldString("nomeDog2");
        nomeDog2.asAtr().label("Nome Dog");

        nome2.asAtrAnnotation().setAnnotated();

        sobrenome2 = this.addFieldString("sobrenome2");
        sobrenome2.asAtr().label("Sobrenome");
        sobrenome2.asAtrAnnotation().setAnnotated();
        nomeMae2 = this.addFieldString("nomeMae2");
        nomeMae2.asAtr().label("Nome Mãe");

        nomeMae2.asAtrAnnotation().setAnnotated();

        dataHoraInicio = this.addFieldDateTime("dataHoraInicio");
        dataHoraInicio.asAtr().label("Data/Hora InÃ­cio").required();

        SViewDateTime sViewDateTime = new SViewDateTime();
        sViewDateTime.setClearBtn(true);
        sViewDateTime.setTodayHighlight(true);
        sViewDateTime.setMode24hs(false);
        dataHoraInicio.withView(sViewDateTime);


        data = this.addFieldDate("data");
        data.asAtr().label("Data");
        data.asAtr().required();

        SViewCheckBox sView = new SViewCheckBox();
        sView.setAlignLabelOfCheckBox(Alignment.CENTER);

        SViewDate sViewDate = new SViewDate();
        sViewDate.setClearBtn(true);
        sViewDate.setTodayBtn(true);

        data.withView(sViewDate);

        time = this.addFieldTime("time");
        SViewTime sViewTime = new SViewTime();
        sViewTime.setMinuteStep(20);
        sViewTime.setMode24hs(true);
        time.withView(sViewTime);
        time.asAtr().label("time").required();

        nomeGato2.asAtr().dependsOn(time).exists(SingularPredicates.typeValueIsNull(time));

        nomeMae2.asAtr().dependsOn(data).exists(SingularPredicates.typeValueIsNull(data));
    }
}
