package org.opensingular.singular.form.showcase.component.form.core.subtitle;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDecimal;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class)
public class STypeDadosProfissionais extends STypeComposite<SIComposite> {

    public STypeString profissao;
    public STypeString funcao;
    public STypeDecimal rendimento;
    public STypeDecimal redimentoFamilia;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        profissao = this.addFieldString("profissao");
        funcao = this.addFieldString("funcao");
        rendimento = this.addFieldDecimal("rendimento");
        redimentoFamilia = this.addFieldDecimal("redimentoFamilia");

        profissao.asAtr().label("Profissão");
        funcao.asAtr().label("Função");

        rendimento.asAtr().label("Rendimento");
        rendimento.asAtrBootstrap().colPreference(6);

        redimentoFamilia
                .asAtr().label("Rendimento Familiar")
                // @destacar
                .subtitle("O somatório de todo rendimento da mesma família.")
                .asAtrBootstrap().colPreference(6);

        this.asAtr().label("Dados Profissionais");
    }
}
