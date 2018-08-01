package org.opensingular.singular.form.showcase.component.form.layout.block;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

/**
 * Podemos utilizar um STypeComposite como bloco de agrupamento
 */
@CaseItem(componentName = "Block", subCaseName = "Composite", group = Group.LAYOUT, resources = @Resource(CaseLayoutPackage.class))
@SInfoType(spackage = CaseLayoutPackage.class)
public class CaseBlockWithCompositeSType extends STypeComposite<SIComposite> {

    public STypeString nome;
    public STypeCPF cpf;
    public STypeDate dataNascimento;
    public STypeAddress endereco;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        cpf = this.addField("cpf", STypeCPF.class);
        dataNascimento = this.addFieldDate("dataNascimento");
        endereco = this.addField("endereco", STypeAddress.class);

        nome
                .asAtr().label("Nome completo")
                .asAtrBootstrap().colPreference(6);

        cpf
                .asAtrBootstrap().colPreference(3);

        dataNascimento
                .asAtr().label("Data de nascimento");

        //@destacar:bloco
        SViewByBlock viewByBlock = new SViewByBlock();
        viewByBlock
                .newBlock("Dados Pessoais").add(nome).add(cpf).add(dataNascimento)
                .newBlock("Endereço").add(endereco);
        this.withView(viewByBlock);
        //@destacar:fim
    }
}
