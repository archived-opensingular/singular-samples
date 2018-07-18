package org.opensingular.singular.form.showcase.component.form.layout.stypes;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeYearMonth;
import org.opensingular.form.view.SViewListByMasterDetail;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypeCargo extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeYearMonth dtInicio;
    public STypeYearMonth dtFim;
    public STypeList<STypePet, SIComposite> pets;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome", true);
        dtInicio = this.addField("dtInicio", STypeYearMonth.class, true);
        dtFim = this.addField("dtFim", STypeYearMonth.class);
        pets = this.addFieldListOf("pets", STypePet.class);

        nome
                .asAtr().label("Nome")
                .asAtrBootstrap().colPreference(4);

        dtInicio
                .asAtr().label("Data inicial")
                .asAtrBootstrap().colPreference(4);

        dtFim
                .asAtr().label("Data final")
                .asAtrBootstrap().colPreference(4);

        STypePet stPet = pets.getElementsType();

        //@destacar:bloco
        SViewListByMasterDetail petsView = new SViewListByMasterDetail()
                .col(stPet.nome)
                .col(stPet.tipo);
        pets
                .withView(petsView)
        //@destacar:fim
                .asAtr().label("Animais de estimação no trabalho");
    }
}
