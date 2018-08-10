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

import org.opensingular.form.*;
import org.opensingular.form.provider.SSimpleProvider;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeLong;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.form.util.SingularPredicates;
import org.opensingular.form.view.SViewListByMasterDetail;

import javax.annotation.Nonnull;

import static org.opensingular.form.util.SingularPredicates.typeValueIsFalseOrNull;
import static org.opensingular.form.util.SingularPredicates.typeValueIsTrue;
import static org.opensingular.formsamples.crud.types.toxicologia.ToxicologiaPackage.OBRIGATORIO;

@SInfoType(spackage = ToxicologiaPackage.class, name = "EstudoResiduo")
public class EstudoResiduoForm extends STypeComposite<SIComposite> {

    public STypeString nomeOutraCultura;
    public STypeComposite<SIComposite> cultura;
    public STypeComposite<SIComposite> emprego;
    public STypeBoolean outraCultura;
    public STypeBoolean parteComestivel;
    public STypeInteger intervaloPretendido;
    public STypeComposite<SIComposite> norma;
    public STypeInteger aplicacoes;
    public STypeString observacoes;
    public STypeComposite<SIComposite> unidadeMediadaDosagem;
    public STypeString outraUnidadeMediaDescricao;
    public STypeBoolean adjuvante;
    public STypeBoolean outraUnidadeMedia;
    public STypeAttachment estudoResiduo;

    public STypeList<STEnsaio, SIComposite> ensaios;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {

        nomeOutraCultura = this.addFieldString("nomeOutraCultura");

        cultura = this.addFieldComposite("cultura");
        final STypeLong codCultura = cultura.addField("codCultura", STypeLong.class);
        final STypeString nomeCultura = cultura.addField("nomeCultura", STypeString.class);

        emprego = this.addFieldComposite("emprego");
        final STypeLong codEmprego = emprego.addField("codEmprego", STypeLong.class);
        final STypeString nomeEmprego = emprego.addField("nomeEmprego", STypeString.class);

        outraCultura = this.addFieldBoolean("outraCultura");

        parteComestivel = this.addFieldBoolean("parteComestivel");
        intervaloPretendido = this.addFieldInteger("intervaloPretendido");
        norma = this.addFieldComposite("norma");
        final STypeInteger idNorma = norma.addFieldInteger("idNorma");
        final STypeString descricaoNorma = norma.addFieldString("descricaoNorma");
        aplicacoes = this.addFieldInteger("aplicacoes");
        observacoes = this.addFieldString("observacoes");

        unidadeMediadaDosagem = this.addFieldComposite("unidadeMediadaDosagem");
        outraUnidadeMediaDescricao = this.addFieldString("outraUnidadeMediaDescricao");
        final STypeInteger idDosagem = unidadeMediadaDosagem.addFieldInteger("idDosagem");
        final STypeString siglaDosagem = unidadeMediadaDosagem.addFieldString("siglaDosagem");
        adjuvante = this.addFieldBoolean("adjuvante");
        outraUnidadeMedia = this.addFieldBoolean("outraUnidadeMedia");

        ensaios = this.addFieldListOf("ensaios", STEnsaio.class);
        STEnsaio ensaio = ensaios.getElementsType();

        estudoResiduo = this.addFieldAttachment("estudoResiduo");

        cultura
                .asAtrBootstrap()
                .colPreference(6)
                .asAtr()
                .label("Cultura")
                .required(OBRIGATORIO)
                .dependsOn(outraCultura)
                .exists(typeValueIsTrue(outraCultura).negate());

        cultura
                .selection()
                .id(codCultura)
                .display(nomeCultura)
                .simpleProvider((SSimpleProvider) builder -> builder
                        .add().set(codCultura, "1").set(nomeCultura, "Milho")
                        .add().set(codCultura, "2").set(nomeCultura, "Feijão")
                        .add().set(codCultura, "3").set(nomeCultura, "Arroz"));

        nomeOutraCultura
                .asAtrBootstrap()
                .colPreference(6)
                .asAtr()
                .label("Nome da Cultura")
                .required(OBRIGATORIO)
                .dependsOn(outraCultura)
                .exists(typeValueIsTrue(outraCultura));

        emprego
                .asAtrBootstrap()
                .colPreference(6)
                .asAtr()
                .required(OBRIGATORIO)
                .label("Emprego");

        emprego
                .selection()
                .id(codEmprego)
                .display(nomeEmprego)
                .simpleProvider((SSimpleProvider) builder -> builder
                        .add()
                        .set(codEmprego, "1")
                        .set(nomeEmprego, "Raiz"));

        outraCultura
                .asAtr()
                .label("Outra cultura")
                .asAtrBootstrap()
                .colPreference(6);

        parteComestivel
                .asAtr()
                .label("Parte Comestível?")
                .asAtrBootstrap()
                .colPreference(6);

        intervaloPretendido
                .asAtr()
                .required(OBRIGATORIO)
                .label("Intervalo de Segurança Pretendido (em dias)")
                .asAtrBootstrap()
                .colPreference(6);

        norma
                .asAtr()
                .required(OBRIGATORIO)
                .label("Norma")
                .asAtrBootstrap()
                .colPreference(3);

        norma
                .selection()
                .id(idNorma)
                .display(descricaoNorma)
                .simpleProvider(builder -> builder.add()
                        .set(idNorma, "1")
                        .set(descricaoNorma, "Norma ISO 9001"));

        aplicacoes
                .asAtr()
                .required(OBRIGATORIO)
                .label("Nº de Aplicações")
                .asAtrBootstrap()
                .colPreference(3);

        observacoes
                .asAtr()
                .maxLength(1000)
                .label("Observações")
                .asAtrBootstrap()
                .colPreference(12);

        observacoes
                .withTextAreaView();


        unidadeMediadaDosagem
                .asAtr()
                .required(OBRIGATORIO)
                .dependsOn(outraUnidadeMedia)
                .exists(SingularPredicates.allMatches(typeValueIsFalseOrNull(outraUnidadeMedia)))
                .label("Unidade de medida da dosagem")
                .asAtrBootstrap()
                .colPreference(4);

        unidadeMediadaDosagem
                .selection()
                .id(idDosagem)
                .display(siglaDosagem)
                .simpleProvider(builder -> builder.add()
                        .set(idDosagem, "1")
                        .set(siglaDosagem, "XXX"));

        outraUnidadeMediaDescricao
                .asAtr()
                .label("Unidade de medida da dosagem")
                .required(OBRIGATORIO)
                .dependsOn(outraUnidadeMedia)
                .exists(SingularPredicates.allMatches(typeValueIsTrue(outraUnidadeMedia)))
                .asAtrBootstrap()
                .colPreference(4);

        adjuvante
                .withSelectView()
                .selectionOf(Boolean.class)
                .selfId()
                .display(bool -> bool ? "Sim" : "Não")
                .simpleConverter();

        adjuvante
                .asAtr()
                .required(OBRIGATORIO)
                .label("Adjuvante")
                .asAtrBootstrap()
                .colPreference(2);

        outraUnidadeMedia
                .asAtr()
                .label("Outra unidade de medida da dosagem")
                .asAtrBootstrap()
                .colPreference(12);

        estudoResiduo
                .asAtr()
                .required(OBRIGATORIO)
                .label("Estudo de resíduo (em formato PDF)");

        estudoResiduo
                .asAtr()
                .allowedFileTypes("pdf");

        ensaios
                .withView(new SViewListByMasterDetail()
                        .col(ensaio.codigoEnsaio, "ID")
                        .col(ensaio.estado, "Estado", "${nome} - ${sigla}")
                        .col(ensaio.cidade, "Cidade", "${nome}")
                )
                .asAtr().label("Ensaio");

    }

}