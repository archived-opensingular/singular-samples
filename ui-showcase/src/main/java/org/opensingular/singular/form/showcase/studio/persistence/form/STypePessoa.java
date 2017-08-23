package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeEMail;

import javax.annotation.Nonnull;

@SInfoType(name = "Pessoa", spackage = SPackageStudioPersistenceForm.class)
public class STypePessoa extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeInteger idade;
    public STypeCPF cpf;
    public STypeEMail email;
    public STypeTelefoneNacional telefone;

    private void createTypes() {
        nome = addField("nome", STypeString.class);
        cpf = addField("cpf", STypeCPF.class);
        idade = addField("idade", STypeInteger.class);
        email = addField("email", STypeEMail.class);
        telefone = addField("telefone", STypeTelefoneNacional.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        createTypes();
        nome.asAtr().label("Nome").asAtrBootstrap().colPreference(6);
        cpf.asAtr().label("CPF").asAtrBootstrap().colPreference(4);
        idade.asAtr().label("Idade").asAtrBootstrap().colPreference(2);
        email.asAtr().label("Email").asAtrBootstrap().colPreference(6);
        telefone.asAtr().asAtrBootstrap().colPreference(4);
    }
}