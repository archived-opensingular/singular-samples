package org.opensingular.singular.form.showcase.component.form.core.search.form;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.generic.STGenericComposite;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class)
public class STFuncionario extends STGenericComposite<SIFuncionario> {

    public STypeString nome;
    public STypeString funcao;
    public STypeInteger idade;

    public STFuncionario() {
        super(SIFuncionario.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        funcao = this.addFieldString("funcao");
        idade = this.addFieldInteger("idade");
    }
}
