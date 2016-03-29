/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.exemplos.notificacaosimpliciada.baixocusto;

import br.net.mirante.singular.exemplos.notificacaosimpliciada.NotificacaoSimplificadaProviderUtils;
import br.net.mirante.singular.form.mform.PackageBuilder;
import br.net.mirante.singular.form.mform.SIComposite;
import br.net.mirante.singular.form.mform.SPackage;
import br.net.mirante.singular.form.mform.SType;
import br.net.mirante.singular.form.mform.STypeAttachmentList;
import br.net.mirante.singular.form.mform.STypeComposite;
import br.net.mirante.singular.form.mform.STypeList;
import br.net.mirante.singular.form.mform.STypeSimple;
import br.net.mirante.singular.form.mform.basic.view.SViewListByForm;
import br.net.mirante.singular.form.mform.basic.view.SViewListByMasterDetail;
import br.net.mirante.singular.form.mform.basic.view.SViewListByTable;
import br.net.mirante.singular.form.mform.basic.view.SViewSelectionBySearchModal;
import br.net.mirante.singular.form.mform.basic.view.SViewTab;
import br.net.mirante.singular.form.mform.core.STypeInteger;
import br.net.mirante.singular.form.mform.core.STypeString;
import br.net.mirante.singular.form.mform.core.attachment.STypeAttachment;
import br.net.mirante.singular.form.mform.util.transformer.Value;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class SPackageNotificacaoSimplificadaBaixoRisco extends SPackage {

    public static final String PACOTE = "mform.peticao.notificacaosimplificada";
    public static final String TIPO = "MedicamentoBaixoRisco";
    public static final String NOME_COMPLETO = PACOTE + "." + TIPO;

    public SPackageNotificacaoSimplificadaBaixoRisco() {
        super(PACOTE);
    }

    @Override
    protected void carregarDefinicoes(PackageBuilder pb) {

        final STypeComposite<?> notificacaoSimplificada = pb.createCompositeType(TIPO);
        notificacaoSimplificada.asAtrBasic().label("Notificação Simplificada - Medicamento de Baixo Risco");

        final STypeComposite<?> linhaProducao = notificacaoSimplificada.addFieldComposite("linhaProducao");
        STypeSimple idLinhaProducao = linhaProducao.addFieldInteger("id");
        STypeSimple descricaoLinhaProducao = linhaProducao.addFieldString("descricao");

        linhaProducao
                .asAtrBasic()
                .label("Linha de Produção");
        linhaProducao.setView(SViewSelectionBySearchModal::new);
        linhaProducao.withSelectionFromProvider(descricaoLinhaProducao, (optionsInstance, lb) -> {
            for (Pair p : NotificacaoSimplificadaProviderUtils.linhasProducao()) {
                lb
                        .add()
                        .set(idLinhaProducao, p.getKey())
                        .set(descricaoLinhaProducao, p.getValue());
            }
        });


        final STypeComposite<?> configuracaoLinhaProducao = notificacaoSimplificada.addFieldComposite("configuracaoLinhaProducao");
        STypeSimple idConfiguracaoLinhaProducao = configuracaoLinhaProducao.addFieldInteger("id");
        STypeSimple idLinhaProducaoConfiguracao = configuracaoLinhaProducao.addFieldInteger("idLinhaProducao");
        STypeSimple descConfiguracaoLinhaProducao = configuracaoLinhaProducao.addFieldString("descricao");

        configuracaoLinhaProducao
                .asAtrBasic()
                .label("Descrição")
                .dependsOn(linhaProducao)
                .visivel(i -> Value.notNull(i, idLinhaProducao));
        configuracaoLinhaProducao
                .withSelectView()
                .withSelectionFromProvider(descConfiguracaoLinhaProducao, (optionsInstance, lb) -> {
                    Integer id = (Integer) Value.of(optionsInstance, idLinhaProducao);
                    for (Triple p : NotificacaoSimplificadaProviderUtils.configuracoesLinhaProducao(id)) {
                        lb
                                .add()
                                .set(idConfiguracaoLinhaProducao, p.getLeft())
                                .set(idLinhaProducaoConfiguracao, p.getMiddle())
                                .set(descConfiguracaoLinhaProducao, p.getRight());
                    }
                });


        final STypeList<STypeComposite<SIComposite>, SIComposite> substancias = notificacaoSimplificada.addFieldListOfComposite("substancias", "concentracaoSubstancia");
        substancias
                .withView(SViewListByTable::new)
                .asAtrBasic()
                .label("Substâncias")
                .dependsOn(configuracaoLinhaProducao)
                .visivel(i -> Value.notNull(i, idConfiguracaoLinhaProducao));

        final STypeComposite<?> concentracaoSubstancia = substancias.getElementsType();
        final STypeComposite<?> substancia = concentracaoSubstancia.addFieldComposite("substancia");
        STypeSimple idSubstancia = substancia.addFieldInteger("id");
        STypeSimple idConfiguracaoLinhaProducaoSubstancia = substancia.addFieldInteger("configuracaoLinhaProducao");
        STypeSimple substanciaDescricao = substancia.addFieldString("descricao");
        substancia
                .asAtrBasic()
                .label("Substância")
                .asAtrBootstrap()
                .colPreference(6);
        substancia
                .withSelectView()
                .withSelectionFromProvider(substanciaDescricao, (optionsInstance, lb) -> {
                    Integer id = (Integer) Value.of(optionsInstance, idConfiguracaoLinhaProducao);
                    for (Triple p : NotificacaoSimplificadaProviderUtils.substancias(id)) {
                        lb
                                .add()
                                .set(idSubstancia, p.getLeft())
                                .set(idConfiguracaoLinhaProducaoSubstancia, p.getMiddle())
                                .set(substanciaDescricao, p.getRight());
                    }
                });


        final STypeComposite<?> concentracao = concentracaoSubstancia.addFieldComposite("concentracao");
        SType<?> idConcentracacao = concentracao.addFieldInteger("id");
        STypeSimple idSubstanciaConcentracao = concentracao.addFieldInteger("idSubstancia");
        STypeSimple descConcentracao = concentracao.addFieldString("descricao");
        concentracao
                .asAtrBasic()
                .label("Concentração")
                .dependsOn(substancia)
                .asAtrBootstrap()
                .colPreference(6);
        concentracao
                .withSelectView()
                .withSelectionFromProvider(substanciaDescricao, (optionsInstance, lb) -> {
                    Integer id = (Integer) Value.of(optionsInstance, idSubstancia);
                    for (Triple p : NotificacaoSimplificadaProviderUtils.concentracoes(id)) {
                        lb
                                .add()
                                .set(idConcentracacao, p.getLeft())
                                .set(idSubstanciaConcentracao, p.getMiddle())
                                .set(descConcentracao, p.getRight());
                    }
                });


        STypeString nomeComercial = notificacaoSimplificada.addFieldString("nomeComercialMedicamento");
        nomeComercial
                .asAtrBasic()
                .label("Nome Comercial do Medicamento")
                .asAtrBootstrap()
                .colPreference(8);

        final STypeComposite<?> formaFarmaceutica = notificacaoSimplificada.addFieldComposite("formaFarmaceutica");
        SType<?> idFormaFormaceutica = formaFarmaceutica.addFieldInteger("id");
        STypeSimple descFormaFormaceutica = formaFarmaceutica.addFieldString("descricao");
        formaFarmaceutica
                .asAtrBasic()
                .label("Forma Farmacêutica")
                .asAtrBootstrap()
                .colPreference(4);
        formaFarmaceutica
                .withSelectView()
                .withSelectionFromProvider(descFormaFormaceutica, (optionsInstance, lb) -> {
                    for (Pair p : NotificacaoSimplificadaProviderUtils.formasFarmaceuticas()) {
                        lb
                                .add()
                                .set(idFormaFormaceutica, p.getKey())
                                .set(descFormaFormaceutica, p.getValue());
                    }
                });


        final STypeList<STypeComposite<SIComposite>, SIComposite> acondicionamentos = notificacaoSimplificada.addFieldListOfComposite("acondicionamentos", "acondicionamento");

        STypeComposite<SIComposite> acondicionamento = acondicionamentos.getElementsType();

        STypeComposite<SIComposite> embalagemPrimaria = acondicionamento.addFieldComposite("embalagemPrimaria");
        STypeString idEmbalagemPrimaria = embalagemPrimaria.addFieldString("id");
        STypeString descricaoEmbalagemPrimaria = embalagemPrimaria.addFieldString("descricao");
        {
            embalagemPrimaria
                    .asAtrBootstrap()
                    .colPreference(6)
                    .asAtrBasic()
                    .label("Embalagem primária")
                    .getTipo().setView(SViewSelectionBySearchModal::new);
            embalagemPrimaria.withSelectionFromProvider(descricaoEmbalagemPrimaria, (optionsInstance, lb) -> {
                for (Pair p : NotificacaoSimplificadaProviderUtils.embalagensPrimarias()) {
                    lb
                            .add()
                            .set(idEmbalagemPrimaria, p.getKey())
                            .set(descricaoEmbalagemPrimaria, p.getValue());
                }
            });
        }

        STypeComposite<SIComposite> embalagemSecundaria = acondicionamento.addFieldComposite("embalagemSecundaria");
        STypeString idEmbalagemSecundaria = embalagemSecundaria.addFieldString("id");
        STypeString descricaoEmbalagemSecundaria = embalagemSecundaria.addFieldString("descricao");
        {
            embalagemSecundaria
                    .asAtrBootstrap()
                    .colPreference(6)
                    .asAtrBasic()
                    .label("Embalagem secundária")
                    .getTipo().setView(SViewSelectionBySearchModal::new);
            embalagemSecundaria.withSelectionFromProvider(descricaoEmbalagemSecundaria, (optionsInstance, lb) -> {
                for (Pair p : NotificacaoSimplificadaProviderUtils.embalagensSecundarias()) {
                    lb
                            .add()
                            .set(idEmbalagemSecundaria, p.getKey())
                            .set(descricaoEmbalagemSecundaria, p.getValue());
                }
            });

        }
        STypeInteger quantidade = acondicionamento.addFieldInteger("quantidade", true);
        quantidade
                .asAtrBootstrap()
                .colPreference(3)
                .asAtrBasic()
                .label("Quantidade");

        STypeComposite<SIComposite> unidadeMedida = acondicionamento.addFieldComposite("unidadeMedida");
        STypeString idUnidadeMedida = unidadeMedida.addFieldString("id");
        STypeString descricaoUnidadeMedida = unidadeMedida.addFieldString("descricao");
        unidadeMedida
                .asAtrBootstrap()
                .colPreference(3)
                .asAtrBasic()
                .label("Unidade de medida")
                .getTipo().setView(SViewSelectionBySearchModal::new);
        unidadeMedida.withSelectionFromProvider(descricaoUnidadeMedida, (optionsInstance, lb) -> {
            for (Pair p : NotificacaoSimplificadaProviderUtils.unidadesMedida()) {
                lb
                        .add()
                        .set(idUnidadeMedida, p.getKey())
                        .set(descricaoUnidadeMedida, p.getValue());
            }
        });

        final STypeAttachmentList estudosEstabilidade = acondicionamento.addFieldListOfAttachment("estudosEstabilidade", "estudoEstabilidade");

        estudosEstabilidade.asAtrBasic()
                .label("Estudo de estabilidade")
                .displayString("<#list _inst as c>${c.name}<#sep>, </#sep></#list>");
        {

            STypeAttachment f = estudosEstabilidade.getElementsType();
            SType<?> nomeArquivo = (STypeSimple) f.getField(f.FIELD_NAME);
            nomeArquivo.asAtrBasic().label("Nome do Arquivo");
        }

        {
            final STypeAttachmentList layoutsRotulagem = acondicionamento.addFieldListOfAttachment("layoutsRotulagem", "layoutRotulagem");
            layoutsRotulagem.asAtrBasic().label("Layout da rotulagem");

            STypeAttachment f = layoutsRotulagem.getElementsType();
            SType<?> nomeArquivo = (STypeSimple) f.getField(f.FIELD_NAME);
            nomeArquivo.asAtrBasic().label("Nome do Arquivo");
        }


        STypeList<STypeComposite<SIComposite>, SIComposite> locaisFabricacao = acondicionamento.addFieldListOfComposite("locaisFabricacao", "localFabricacao");
        STypeComposite<SIComposite> localFabricacao = locaisFabricacao.getElementsType();

        STypeInteger tipoLocalFabricacao = localFabricacao.addFieldInteger("tipoLocalFabricacao");
        tipoLocalFabricacao
                .asAtrBasic()
                .label("Tipo de local de fabricação");
        tipoLocalFabricacao
                .withRadioView()
                .withSelection()
                .add(1, "Produção Própria")
                .add(2, "Empresa Internacional")
                .add(3, "Empresa Terceirizada")
                .add(4, "Outro Local de Fabricação");


        STypeComposite<SIComposite> empresaPropria = localFabricacao.addFieldComposite("empresaPropria");
        empresaPropria.addFieldString("razaoSocial")
                .asAtrBasic().label("Razão Social");
        empresaPropria.addFieldCNPJ("cnpj")
                .asAtrBasic().label("CNPJ");
        empresaPropria.addFieldString("endereco")
                .asAtrBasic().label("Endereço");
        empresaPropria.asAtrBasic()
                .dependsOn(tipoLocalFabricacao)
                .visivel(i -> Integer.valueOf(1).equals(Value.of(i, tipoLocalFabricacao)));


        STypeComposite<SIComposite> empresaInternacional = localFabricacao.addFieldComposite("empresaInternacional");

        STypeString idEmpresaInternacional = empresaInternacional.addFieldString("id");
        STypeString razaoSocialInternacional = empresaInternacional.addFieldString("razaoSocial");
        razaoSocialInternacional.asAtrBasic().label("Razão Social");

        STypeString enderecoInternacional = empresaInternacional.addFieldString("endereco");

        empresaInternacional
                .asAtrBasic()
                .dependsOn(tipoLocalFabricacao)
                .visivel(i -> Integer.valueOf(2).equals(Value.of(i, tipoLocalFabricacao)));
        empresaInternacional
                .withSelectionFromProvider(razaoSocialInternacional, (optionsInstance, lb) -> {
                    for (Triple p : NotificacaoSimplificadaProviderUtils.empresaInternacional()) {
                        lb
                                .add()
                                .set(idEmpresaInternacional, p.getLeft())
                                .set(razaoSocialInternacional, p.getMiddle())
                                .set(enderecoInternacional, p.getRight());
                    }
                })
                .asAtrBasic().label("Empresa internacional")
                .getTipo().setView(SViewSelectionBySearchModal::new);

        STypeComposite<SIComposite> empresaTerceirizada = localFabricacao.addFieldComposite("empresaTerceirizada");


        empresaTerceirizada
                .asAtrBasic()
                .dependsOn(tipoLocalFabricacao)
                .visivel(i -> Integer.valueOf(3).equals(Value.of(i, tipoLocalFabricacao)));


        STypeComposite<SIComposite> empresa = empresaTerceirizada.addFieldComposite("empresa");
        STypeString idEmpresa = empresa.addFieldString("id");
        STypeString razaoSocial = empresa.addFieldString("razaoSocial");
        razaoSocial.asAtrBasic().label("Razão Social");
        STypeString endereco = empresa.addFieldString("endereco");
        empresa
                .asAtrBasic().label("Empresa")
                .getTipo().withView(SViewSelectionBySearchModal::new);

        empresa.withSelectionFromProvider(razaoSocial, (optionsInstance, lb) -> {
            for (Triple t : NotificacaoSimplificadaProviderUtils.empresaTerceirizada()) {
                lb
                        .add()
                        .set(idEmpresa, t.getLeft())
                        .set(razaoSocial, t.getMiddle())
                        .set(endereco, t.getRight());
            }
        });

        STypeList<STypeComposite<SIComposite>, SIComposite> etapasFabricacao = empresaTerceirizada.addFieldListOfComposite("etapasFabricacao", "etapaFabricacao");
        STypeComposite<SIComposite> etapaFabricacao = etapasFabricacao.getElementsType();
        STypeString idEtapaFabricacao = etapaFabricacao.addFieldString("id");
        STypeString descricaoEtapaFabricacao = etapaFabricacao.addFieldString("descricao");

        etapaFabricacao
                .setView(SViewSelectionBySearchModal::new);

        etapaFabricacao.withSelectionFromProvider(descricaoEtapaFabricacao, (optionsInstance, lb) -> {
            for (Pair p : NotificacaoSimplificadaProviderUtils.etapaFabricacao()) {
                lb
                        .add()
                        .set(idEtapaFabricacao, p.getKey())
                        .set(descricaoEtapaFabricacao, p.getValue());
            }
        });

        etapasFabricacao
                .withView(SViewListByForm::new);
        etapasFabricacao
                .asAtrBasic().label("Etapa de fabricação");




        STypeComposite<SIComposite> outroLocalFabricacao = localFabricacao.addFieldComposite("outroLocalFabricacao");

        STypeString idOutroLocalFabricacao = outroLocalFabricacao.addFieldString("id");
        STypeString razaoSocialOutroLocalFabricacao = outroLocalFabricacao.addFieldString("razaoSocial");
        razaoSocialOutroLocalFabricacao.asAtrBasic().label("Razão Social");
        STypeString enderecoOutroLocalFabricacao = outroLocalFabricacao.addFieldString("endereco");
        outroLocalFabricacao
                .asAtrBasic().label("Outro local de fabricação")
                .dependsOn(tipoLocalFabricacao)
                .visivel(i -> Integer.valueOf(4).equals(Value.of(i, tipoLocalFabricacao)));

        outroLocalFabricacao
                .withSelectionFromProvider(razaoSocialOutroLocalFabricacao, (optionsInstance, lb) -> {
                    for (Triple p : NotificacaoSimplificadaProviderUtils.outroLocalFabricacao()) {
                        lb
                                .add()
                                .set(idOutroLocalFabricacao, p.getLeft())
                                .set(razaoSocialOutroLocalFabricacao, p.getMiddle())
                                .set(enderecoOutroLocalFabricacao, p.getRight());
                    }
                })
                .asAtrBasic().label("Outro local de fabricação")
                .getTipo().setView(SViewSelectionBySearchModal::new);

        locaisFabricacao
                .withView(new SViewListByMasterDetail()
                        .col(tipoLocalFabricacao, "Tipo")
                        .col(tipoLocalFabricacao, "Local")
                        .col(tipoLocalFabricacao, "Etapas"))
                .asAtrBasic().label("Local de fabricação");

        STypeInteger prazoValidade = acondicionamento.addFieldInteger("prazoValidade", true);
        prazoValidade.asAtrBasic().label("Prazo de validade (meses)");

        acondicionamentos
                .withView(new SViewListByMasterDetail()
                        .col(descricaoEmbalagemPrimaria, "Embalagem primária")
                        .col(descricaoEmbalagemSecundaria, "Embalagem secundária")
                        .col(quantidade)
                        .col(descricaoUnidadeMedida)
                        .col(estudosEstabilidade, "Estudo de estabilidade")
                        .col(prazoValidade))
                .asAtrBasic().label("Acondicionamento");


        final STypeAttachmentList layoutsRotulagem = notificacaoSimplificada
                .addFieldListOfAttachment("layoutsRotulagem", "layout");
        layoutsRotulagem
                .asAtrBasic()
                .label("Layouts Rotulagem");

        // config tabs
        SViewTab tabbed = notificacaoSimplificada.setView(SViewTab::new);
        tabbed.addTab("medicamento", "Medicamento")
                .add(linhaProducao)
                .add(configuracaoLinhaProducao)
                .add(substancias)
                .add(formaFarmaceutica)
                .add(nomeComercial);
        tabbed.addTab("acondicionamento", "Acondicionamento")
                .add(acondicionamentos);
        tabbed.addTab("layoutsRotulagem", "Rotulagem")
                .add(layoutsRotulagem);

    }

}

