package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByTable;

import javax.annotation.Nonnull;

@SInfoType(name = "Engenheiro", spackage = RequirementsamplePackage.class)
public class EngenheiroForm extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeList<STypeString, SIString> listaNomes;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addField("nome", STypeString.class);

        listaNomes = this.addFieldListOf("listaNomes", STypeString.class);
        listaNomes.asAtr().label("Nomes");
        listaNomes.withView(SViewListByTable::new);
        listaNomes.withInitListener(this::createOptions);
    }


    private void createOptions(SIList<SIString> list) {
        for (int i = 0; i < 20; i++) {
            SIString experiencia = list.addNew();
            experiencia.setValue("Nome" + i);
        }
    }
}