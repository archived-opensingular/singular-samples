package org.opensingular.formsamples.crud.types.antaq;

import static org.apache.commons.lang3.StringUtils.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByTable;
import org.opensingular.form.view.SViewTab;
import org.opensingular.lib.commons.base.SingularProperties;

@SInfoType(spackage = AntaqPackage.class, label = "Navegação Interior Passageiro/Misto no Longitudinal", name = "InteriorPassageirosCargasLong")
public class Resolucao912Form extends STypeComposite<SIComposite> {

    public final static boolean             OBRIGATORIO       = !SingularProperties.get().isTrue(SingularProperties.SINGULAR_DEV_MODE);
    public final static int                 QUANTIDADE_MINIMA = OBRIGATORIO ? 1 : 0;
    public static final String              OPERACAO_PATH     = "operacao";

    public STypeHabilitacaoEmpresa          habilitacaoEmpresa;
    public STypeList<STypeString, SIString> telefones;

    @Override
    protected void onLoadType(TypeBuilder tb) {

        this.asAtr().label("Resolução 9121 - Passageiros e Cargas na Navegação Interior de Percurso Longitudinal")
            .displayString("Res. 912 - Nav. Interior - Passageiros e Carga  Longitudinal: ${(dadosEmpresa.empresa.nomeFantasia)!} - (${(dadosEmpresa.empresa.cnpj)!})");

        habilitacaoEmpresa = this.addField("habilitacaoEmpresa", STypeHabilitacaoEmpresa.class);

        telefones = this.addFieldListOf("telefones", STypeString.class);
        telefones.getElementsType().withSelectionFromSimpleProvider(ins -> {
            List<Serializable> list = new ArrayList<>();
            for (int i = 0; i < 200; i++)
                list.add(leftPad(String.valueOf(i), 20, '0'));
            return list;
        });
        telefones.asAtr().label("Telefones");
        telefones.setView(() -> new SViewListByTable()
            .enableNew()
            .enabledInsert()
            .enableDelete());

        SViewTab tabbed = new SViewTab();

        tabbed.addTab("anexoBE", "Habilitação Empresa").add(habilitacaoEmpresa);
        tabbed.addTab(telefones);
        tabbed.navColPreference(2);
        withView(tabbed);

    }
}
