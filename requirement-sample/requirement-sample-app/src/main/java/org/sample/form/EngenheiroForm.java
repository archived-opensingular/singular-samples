package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.list.SViewListByMasterDetail;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SInfoType(name = "Engenheiro", spackage = RequirementsamplePackage.class)
public class EngenheiroForm extends STypeComposite<SIComposite> {

    public STypeList<STypeExperienciaProfissional, SIComposite> experienciasProfissionais;

    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addField("nome", STypeString.class);
        nome.asAtr().label("Sobrenome");

        experienciasProfissionais = this.addFieldListOf("experienciasProfissionais", STypeExperienciaProfissional.class);


        STypeExperienciaProfissional stExperienciaProfissional = experienciasProfissionais.getElementsType();
        //@destacar:bloco
        SViewListByMasterDetail experienciaView = new SViewListByMasterDetail()
                .setSortableColumn(stExperienciaProfissional.inicio, false)
                .setDisableSort(true)
                .configureEditButtonPerRow("Editando", null, DefaultIcons.FOLDER_OPEN)
                .configureDeleteButtonPerRow(f -> false)
                .label("Informar Experiência Anterior");

        experienciasProfissionais.withView(experienciaView).withInitListener(this::fillWithBlankValues);
    }

    private void fillWithBlankValues(SIList<SIComposite> list) {
        STypeExperienciaProfissional type = experienciasProfissionais.getElementsType();
        for (int i = 0; i < 15; i++) {
            SIComposite experiencia = list.addNew();
            experiencia.setValue(type.atividades, "Reuniões" + i );
            experiencia.setValue(type.empresa, "Corp." +  i );
            experiencia.setValue(type.cargo, "Gerente");
            LocalDate localDate = LocalDate.now();
            experiencia.setValue(type.fim, Date.from(localDate.plusMonths(i).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            experiencia.setValue(type.inicio, Date.from(localDate.minusMonths(i).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
    }
}