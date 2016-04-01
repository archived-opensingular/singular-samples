/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.exemplos.notificacaosimplificada.form.dinamizado;

import br.net.mirante.singular.exemplos.notificacaosimplificada.domain.FormaFarmaceuticaBasica;
import br.net.mirante.singular.exemplos.notificacaosimplificada.domain.LinhaCbpf;
import br.net.mirante.singular.exemplos.notificacaosimplificada.form.STypeAcondicionamento;
import br.net.mirante.singular.exemplos.notificacaosimplificada.form.baixorisco.SPackageNotificacaoSimplificadaBaixoRisco;
import br.net.mirante.singular.exemplos.notificacaosimplificada.form.vocabulario.STypeCategoriaRegulatoria;
import br.net.mirante.singular.exemplos.notificacaosimplificada.service.DominioService;
import br.net.mirante.singular.form.mform.PackageBuilder;
import br.net.mirante.singular.form.mform.SIComposite;
import br.net.mirante.singular.form.mform.SIList;
import br.net.mirante.singular.form.mform.SInfoType;
import br.net.mirante.singular.form.mform.SInstance;
import br.net.mirante.singular.form.mform.SPackage;
import br.net.mirante.singular.form.mform.SType;
import br.net.mirante.singular.form.mform.STypeAttachmentList;
import br.net.mirante.singular.form.mform.STypeComposite;
import br.net.mirante.singular.form.mform.STypeList;
import br.net.mirante.singular.form.mform.STypeSimple;
import br.net.mirante.singular.form.mform.basic.view.SViewAutoComplete;
import br.net.mirante.singular.form.mform.basic.view.SViewListByMasterDetail;
import br.net.mirante.singular.form.mform.basic.view.SViewListByTable;
import br.net.mirante.singular.form.mform.core.STypeString;
import br.net.mirante.singular.form.mform.util.transformer.Value;
import org.apache.commons.lang3.tuple.Triple;

@SInfoType(spackage = SPackageNotificacaoSimplificadaDinamizado.class)
public class SPackageNotificacaoSimplificadaDinamizado extends SPackage {

    public static final String PACOTE = "mform.peticao.notificacaosimplificada.dinamizado";
    public static final String TIPO = "MedicamentoDinamizado";
    public static final String NOME_COMPLETO = PACOTE + "." + TIPO;

    public SPackageNotificacaoSimplificadaDinamizado() {
        super(PACOTE);
    }

    static DominioService dominioService(SInstance ins) {
        return ins.getDocument().lookupService(DominioService.class);
    }

    @Override
    protected void carregarDefinicoes(PackageBuilder pb) {
        pb.getDictionary().loadPackage(SPackageNotificacaoSimplificadaBaixoRisco.class);


        final STypeComposite<?> notificacaoSimplificada = pb.createCompositeType(TIPO);
        notificacaoSimplificada.asAtrBasic().label("Notificação Simplificada - Medicamento Dinamizado");

        STypeCategoriaRegulatoria classe = notificacaoSimplificada.addField("classe", STypeCategoriaRegulatoria.class);


        STypeString nomeComercial = notificacaoSimplificada.addFieldString("nomeComercialMedicamento");
        nomeComercial
                .asAtrBasic()
                .required()
                .label("Nome Comercial do Medicamento")
                .asAtrBootstrap()
                .colPreference(8);


        final STypeComposite<?> linhaProducao = notificacaoSimplificada.addFieldComposite("linhaProducao");
        STypeSimple idLinhaProducao = linhaProducao.addFieldInteger("id");
        STypeSimple descricaoLinhaProducao = linhaProducao.addFieldString("descricao");

        linhaProducao
                .asAtrBasic()
                .required()
                .label("Linha de Produção")
                .asAtrBootstrap()
                .colPreference(4);
        linhaProducao.setView(SViewAutoComplete::new);
        linhaProducao.withSelectionFromProvider(descricaoLinhaProducao, (ins, filter) -> {
            final SIList<?> list = ins.getType().newList();
            for (LinhaCbpf lc : dominioService(ins).linhasProducao(filter)) {
                final SIComposite c = (SIComposite) list.addNew();
                c.setValue(idLinhaProducao, lc.getId());
                c.setValue(descricaoLinhaProducao, lc.getDescricao());
            }
            return list;
        });


        final STypeComposite<?> configuracaoLinhaProducao = notificacaoSimplificada.addFieldComposite("configuracaoLinhaProducao");
        STypeSimple idConfiguracaoLinhaProducao = configuracaoLinhaProducao.addFieldInteger("id");
        STypeSimple idLinhaProducaoConfiguracao = configuracaoLinhaProducao.addFieldInteger("idLinhaProducao");
        STypeSimple descConfiguracaoLinhaProducao = configuracaoLinhaProducao.addFieldString("descricao");

        configuracaoLinhaProducao
                .asAtrBasic()
                .label("Descrição")
                .required()
                .dependsOn(linhaProducao)
                .visible(i -> Value.notNull(i, idLinhaProducao))
                .asAtrBootstrap()
                .colPreference(4);
        configuracaoLinhaProducao
                .withSelectView()
                .withSelectionFromProvider(descConfiguracaoLinhaProducao, (optionsInstance, lb) -> {
                    Integer id = (Integer) Value.of(optionsInstance, idLinhaProducao);
                    for (Triple p : dominioService(optionsInstance).configuracoesLinhaProducaoDinamizado(id)) {
                        lb
                                .add()
                                .set(idConfiguracaoLinhaProducao, p.getLeft())
                                .set(idLinhaProducaoConfiguracao, p.getMiddle())
                                .set(descConfiguracaoLinhaProducao, p.getRight());
                    }
                });

        final STypeComposite<?> formaFarmaceutica = notificacaoSimplificada.addFieldComposite("formaFarmaceutica");
        SType<?> idFormaFormaceutica = formaFarmaceutica.addFieldInteger("id");
        STypeSimple descFormaFormaceutica = formaFarmaceutica.addFieldString("descricao");
        formaFarmaceutica
                .asAtrBasic()
                .required()
                .label("Forma Farmacêutica")
                .dependsOn(configuracaoLinhaProducao)
                .visible(i -> Value.notNull(i, idConfiguracaoLinhaProducao))
                .asAtrBootstrap()
                .colPreference(4);
        formaFarmaceutica
                .withSelectView()
                .withSelectionFromProvider(descFormaFormaceutica, (ins, filter) -> {
                    final SIList<?> list = ins.getType().newList();
                    for (FormaFarmaceuticaBasica ffb : dominioService(ins).formasFarmaceuticas(filter)) {
                        final SIComposite c = (SIComposite) list.addNew();
                        c.setValue(idFormaFormaceutica, ffb.getId());
                        c.setValue(descFormaFormaceutica, ffb.getDescricao());
                    }
                    return list;
                });


        final STypeList<STypeComposite<SIComposite>, SIComposite> formulasHomeopaticas = notificacaoSimplificada.addFieldListOfComposite("formulasHomeopaticas", "formulaHomeopatica");
        formulasHomeopaticas
                .withMiniumSizeOf(1)
                .withView(SViewListByTable::new)
                .asAtrBasic()
                .label("Descrição")
                .dependsOn(configuracaoLinhaProducao)
                .visible(i -> Value.notNull(i, idConfiguracaoLinhaProducao));

        final STypeComposite<?> formulaHomeopatica = formulasHomeopaticas.getElementsType();
        final STypeComposite<?> descricaoDinamizada = formulaHomeopatica.addFieldComposite("descricaoDinamizada");
        STypeSimple idDescricaoDinamizada = descricaoDinamizada.addFieldInteger("id");
        STypeSimple idConfiguracaoLinhaProducaoDescricaoDinamizada = descricaoDinamizada.addFieldInteger("configuracaoLinhaProducao");
        STypeSimple descricaoDescricaoDinamizada = descricaoDinamizada.addFieldString("descricao");
        descricaoDinamizada
                .asAtrBasic()
                .label("Descrição")
                .required()
                .asAtrBootstrap()
                .colPreference(6);
        descricaoDinamizada
                .withSelectView()
                .withSelectionFromProvider(descricaoDescricaoDinamizada, (optionsInstance, lb) -> {
                    Integer id = (Integer) Value.of(optionsInstance, idConfiguracaoLinhaProducao);
                    for (Triple p : dominioService(optionsInstance).descricoesHomeopaticas(id)) {
                        lb
                                .add()
                                .set(idDescricaoDinamizada, p.getLeft())
                                .set(idConfiguracaoLinhaProducaoDescricaoDinamizada, p.getMiddle())
                                .set(descricaoDescricaoDinamizada, p.getRight());
                    }
                });


        final STypeComposite<?> diluicao = formulaHomeopatica.addFieldComposite("diluicao");
        SType<?> idConcentracacao = diluicao.addFieldInteger("id");
        STypeSimple idSubstanciaConcentracao = diluicao.addFieldInteger("idSubstancia");
        STypeSimple descConcentracao = diluicao.addFieldString("descricao");
        diluicao
                .asAtrBasic()
                .required()
                .label("Faixa de Diluição / Potência")
                .dependsOn(descricaoDinamizada)
                .asAtrBootstrap()
                .colPreference(6);
        diluicao
                .withSelectView()
                .withSelectionFromProvider(descConcentracao, (optionsInstance, lb) -> {
                    Integer id = (Integer) Value.of(optionsInstance, idDescricaoDinamizada);
                    for (Triple p : dominioService(optionsInstance).diluicoes(id)) {
                        lb
                                .add()
                                .set(idConcentracacao, p.getLeft())
                                .set(idSubstanciaConcentracao, p.getMiddle())
                                .set(descConcentracao, p.getRight());
                    }
                });


        final STypeList<STypeAcondicionamento, SIComposite> acondicionamentos = notificacaoSimplificada.addFieldListOf("acondicionamentos", STypeAcondicionamento.class);
        acondicionamentos
                .withView(new SViewListByMasterDetail()
                        .col(acondicionamentos.getElementsType().embalagemPrimaria.descricaoEmbalagemPrimaria, "Embalagem primária")
                        .col(acondicionamentos.getElementsType().embalagemSecundaria.descricaoEmbalagemSecundaria, "Embalagem secundária")
                        .col(acondicionamentos.getElementsType().quantidade)
                        .col(acondicionamentos.getElementsType().descricaoUnidadeMedida)
                        .col(acondicionamentos.getElementsType().estudosEstabilidade, "Estudo de estabilidade")
                        .col(acondicionamentos.getElementsType().prazoValidade))
                .asAtrBasic().label("Acondicionamento");


        final STypeAttachmentList layoutsBula = notificacaoSimplificada
                .addFieldListOfAttachment("layoutsBula", "layoutBula");
        layoutsBula
                .asAtrBasic()
                .required()
                .label("Layout bula");


        final STypeAttachmentList layoutsRotulagem = notificacaoSimplificada
                .addFieldListOfAttachment("layoutsRotulagem", "layoutRotulagem");
        layoutsRotulagem
                .asAtrBasic()
                .required()
                .label("Layout rotulagem");

        final STypeAttachmentList indicacoesPropostas = notificacaoSimplificada
                .addFieldListOfAttachment("indicacoesPropostas", "indicacaoProposta");
        indicacoesPropostas
                .asAtrBasic()
                .required()
                .label("Referências das indicações propostas");

    }

}

