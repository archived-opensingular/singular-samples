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

package org.opensingular.formsamples.crud.types.toxicologia;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeDecimal;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.form.view.SViewListByTable;

import javax.annotation.Nonnull;

import static org.opensingular.form.util.SingularPredicates.typeValueIsTrue;
import static org.opensingular.formsamples.crud.types.toxicologia.ToxicologiaPackage.OBRIGATORIO;

@SInfoType(spackage = ToxicologiaPackage.class, name = "amostra")
public class STAmostra extends STypeComposite<SIComposite> {

    public STypeString id;
    public STypeDecimal dose;
    public STypeInteger dat;
    public STypeDecimal loq;
    public STypeDecimal residuo;

    public STAtivoAmostra ativos;

    public STypeBoolean tempoMaior30Dias;
    public STypeAttachment estudoEstabilidade;
    public STypeBoolean metabolito;
    public STypeList<STypeComposite<SIComposite>, SIComposite> metabolitos;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        id = this.addFieldString("id");
        dose = this.addFieldDecimal("dose");
        dat = this.addFieldInteger("dat");
        loq = this.addFieldDecimal("loq");
        residuo = this.addFieldDecimal("residuo");

        ativos = this.addField("ativos", STAtivoAmostra.class);

        tempoMaior30Dias = this.addFieldBoolean("tempoMaior30Dias");
        estudoEstabilidade = this.addFieldAttachment("estudoEstabilidade");
        metabolito = this.addFieldBoolean("metabolito");
        metabolitos = this.addFieldListOfComposite("metabolitos", "metabolito");
        STypeString descricaoMetabolito = metabolitos.getElementsType().addFieldString("descricao");
        STypeDecimal loqMetabolito = metabolitos.getElementsType().addFieldDecimal("loqMetabolito");
        STypeDecimal residuoMetabolito = metabolitos.getElementsType().addFieldDecimal("residuoMetabolito");

        id
                .asAtr()
                .label("ID da Amostra")
                .required(OBRIGATORIO)
                .asAtrBootstrap()
                .colPreference(4);

        dose
                .asAtrBootstrap()
                .colPreference(4)
                .asAtr()
                .label("Dose")
                .required(OBRIGATORIO);

        dat
                .asAtr()
                .required(OBRIGATORIO)
                .label("DAT")
                .asAtrBootstrap()
                .colPreference(4);

        loq
                .asAtr()
                .required(OBRIGATORIO)
                .label("LoQ (mg/KG)")
                .fractionalMaxLength(4);

        residuo
//                .addInstanceValidator(new ResiduoValidator(loq))
                .asAtr()
                .dependsOn(loq)
                .required(OBRIGATORIO)
                .label("Resíduo (mg/KG)")
                .fractionalMaxLength(4);

        ativos
                .asAtr()
                .required(OBRIGATORIO)
                .label("Ingrediente Ativo da Amostra (informados na seção de ativos)")
                .asAtrBootstrap()
                .colPreference(6);

//        ativos
//                .withUpdateListener(new IngredienteAtivoUpdateListener<>());

        tempoMaior30Dias
                .asAtr()
                .label("Tempo Entre Análise e Colheita Maior que 30 Dias")
                .asAtrBootstrap().colPreference(6)
                .newRow();

        estudoEstabilidade
                .asAtr()
                .required(OBRIGATORIO)
                .label("Estudo de Estabilidade (em formato PDF)")
                .dependsOn(tempoMaior30Dias)
                .exists(typeValueIsTrue(tempoMaior30Dias));

        estudoEstabilidade
                .asAtr()
                .allowedFileTypes("pdf");
//                .maxFileSize(SIZE_OF_100MB);

        metabolito
                .withRadioView()
                .asAtr()
                .required(OBRIGATORIO)
                .label("Metabólito");

        metabolitos
                .withMinimumSizeOf(1)
                .withView(SViewListByTable::new)
                .asAtr()
                .label("Metabólitos")
                .dependsOn(metabolito)
                .exists(typeValueIsTrue(metabolito));

        descricaoMetabolito
                .asAtr()
                .required(OBRIGATORIO)
                .label("Descrição");

        loqMetabolito
                .asAtr()
                .required(OBRIGATORIO)
                .label("LoQ (mg/KG)")
                .fractionalMaxLength(4);

        residuoMetabolito
//                .addInstanceValidator(new ResiduoValidator(loqMetabolito))
                .asAtr()
                .required(OBRIGATORIO)
                .label("Resíduo (mg/KG)")
                .fractionalMaxLength(4);
    }
}
