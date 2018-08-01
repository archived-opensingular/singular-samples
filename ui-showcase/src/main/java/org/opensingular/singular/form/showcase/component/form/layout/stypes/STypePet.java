package org.opensingular.singular.form.showcase.component.form.layout.stypes;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypePet extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeString tipo;
    public STypeInteger idade;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome", true);
        tipo = this.addFieldString("tipo", true);
        idade = this.addFieldInteger("idade");

        nome
                .asAtr().label("Nome")
                .asAtrBootstrap().colPreference(4);

        tipo.selectionOf("Gatinho", "Cachorrinho", "Papagaio");
        tipo
                .withSelectView()
                .asAtr().label("Tipo")
                .asAtrBootstrap().colPreference(4);
        idade
                .asAtr().label("Idade")
                .asAtrBootstrap().colPreference(4);
    }
}
