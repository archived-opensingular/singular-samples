package org.opensingular.formsamples.crud.types.antaq;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewTab;
import org.opensingular.lib.commons.base.SingularProperties;

@SInfoType(spackage = AntaqPackage.class, label = "Navegação Interior Passageiro/Misto no Longitudinal", name = "InteriorPassageirosCargasLong")
public class Resolucao912Form extends STypeComposite<SIComposite> {

    public final static boolean    OBRIGATORIO       = !SingularProperties.get().isTrue(SingularProperties.SINGULAR_DEV_MODE);
    public final static int        QUANTIDADE_MINIMA = OBRIGATORIO ? 1 : 0;
    public static final String     OPERACAO_PATH     = "operacao";

    public STypeHabilitacaoEmpresa habilitacaoEmpresa;

    @Override
    protected void onLoadType(TypeBuilder tb) {

        this.asAtr().label("Resolução 912 - Passageiros e Cargas na Navegação Interior de Percurso Longitudinal")
            .displayString("Res. 912 - Nav. Interior - Passageiros e Carga  Longitudinal: ${(dadosEmpresa.empresa.nomeFantasia)!} - (${(dadosEmpresa.empresa.cnpj)!})");

        habilitacaoEmpresa = this.addField("habilitacaoEmpresa", STypeHabilitacaoEmpresa.class);

        SViewTab tabbed = new SViewTab();

        tabbed.addTab("anexoBE", "Habilitação Empresa").add(habilitacaoEmpresa);
        tabbed.navColPreference(2);
        withView(tabbed);

    }
}
