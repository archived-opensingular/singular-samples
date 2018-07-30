package org.opensingular.singular.form.showcase.component.form.interaction.form;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.generic.STGenericComposite;
import org.opensingular.singular.form.showcase.component.form.interaction.CaseInteractionPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInteractionPackage.class)
public class STItem extends STGenericComposite<SIItem> {

    public STypeString nome;
    public STypeInteger quantidade;

    public STItem() {
        super(SIItem.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        quantidade = this.addFieldInteger("quantidade");

        nome
                .asAtr().label("Nome").enabled(false)
                .asAtrBootstrap().colPreference(3);

        quantidade
                .asAtr().label("Quantidade")
                .asAtrBootstrap().colPreference(2);
    }
}
