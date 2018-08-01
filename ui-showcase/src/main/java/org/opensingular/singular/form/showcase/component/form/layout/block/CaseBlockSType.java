package org.opensingular.singular.form.showcase.component.form.layout.block;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.form.view.SViewByBlock;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

/**
 * Block
 */
/*hidden*/@CaseItem(componentName = "Block", subCaseName = "Default", group = Group.LAYOUT, resources = @Resource(CaseLayoutPackage.class))
@SInfoType(spackage = CaseLayoutPackage.class, name = "DefaultBlock")
public class CaseBlockSType extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeCPF cpf;
    public STypeDate dataNascimento;
    public STypeEMail email;
    public STypeTelefoneNacional telefone;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        cpf = this.addField("cpf", STypeCPF.class);
        dataNascimento = this.addFieldDate("dataNascimento");
        email = this.addField("email", STypeEMail.class);
        telefone = this.addField("telefone", STypeTelefoneNacional.class);

        nome
                .asAtr().label("Nome completo")
                .asAtrBootstrap().colPreference(6);

        cpf.asAtrBootstrap().colPreference(3);

        dataNascimento
                .asAtr().label("Data de nascimento");

        email.asAtrBootstrap().colPreference(3);

        telefone.asAtrBootstrap().colPreference(2);

        //@destacar:bloco
        SViewByBlock viewByBlock = new SViewByBlock();
        viewByBlock
                .newBlock("Dados Pessoais").add(nome).add(cpf).add(dataNascimento)
                .newBlock("Contato").add(email).add(telefone);
        this.withView(viewByBlock);
        //@destacar:fim
    }
}
