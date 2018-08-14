/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.formsamples.crud.types.antaq;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewTab;
import org.opensingular.form.view.list.SViewListByTable;
import org.opensingular.lib.commons.base.SingularProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.leftPad;

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
        telefones.withView(() -> new SViewListByTable()
            .enableNew()
                //TODO VERIFICAR NECESSIDADE DESSE TRUE...
                .configureEditButton(t -> true)
                .configureDeleteButton(t -> true));

        SViewTab tabbed = new SViewTab();

        tabbed.addTab("anexoBE", "Habilitação Empresa").add(habilitacaoEmpresa);
        tabbed.addTab(telefones);
        tabbed.navColPreference(2);
        withView(tabbed);

    }
}
