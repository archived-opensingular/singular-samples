package org.opensingular.singular.form.showcase.component.form.core.subtitle;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class)
public class STypeDadosPessoais extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeEMail email;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        email = this.addFieldEmail("email");

        nome.asAtr().label("Nome");
        email.asAtr().label("E-mail");

        this.asAtr().label("Dados Pessoais")
        // @destacar
            .subtitle("Favor preencher todos os dados pessoais.");
    }
}
