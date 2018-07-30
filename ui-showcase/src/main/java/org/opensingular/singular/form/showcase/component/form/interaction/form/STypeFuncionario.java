package org.opensingular.singular.form.showcase.component.form.interaction.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeMonetary;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.form.interaction.CaseInteractionPackage;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

@SInfoType(spackage = CaseInteractionPackage.class)
public class STypeFuncionario extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeMonetary salario;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome", true);
        salario = this.addFieldMonetary("salario");

        nome
                .asAtr().label("Nome")
                .asAtrBootstrap().colPreference(8);

        salario.asAtr().label("Salário");
    }
}
