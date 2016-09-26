package br.net.mirante.singular.exemplos.ggtox.primariasimplificada.common;

import br.net.mirante.singular.exemplos.SelectBuilder;
import br.net.mirante.singular.exemplos.SelectBuilder.CidadeDTO;
import br.net.mirante.singular.exemplos.SelectBuilder.EstadoDTO;
import br.net.mirante.singular.exemplos.ggtox.primariasimplificada.domain.SubgrupoEntity;
import br.net.mirante.singular.exemplos.ggtox.primariasimplificada.form.SPackagePPSCommon;
import br.net.mirante.singular.exemplos.ggtox.primariasimplificada.listeners.IngredienteAtivoUpdateListener;
import br.net.mirante.singular.exemplos.ggtox.primariasimplificada.validators.ResiduoValidator;
import br.net.mirante.singular.form.*;
import br.net.mirante.singular.form.persistence.STypePersistentComposite;
import br.net.mirante.singular.form.provider.SSimpleProvider;
import br.net.mirante.singular.form.type.core.*;
import br.net.mirante.singular.form.type.core.attachment.STypeAttachment;
import br.net.mirante.singular.form.util.transformer.Value;
import br.net.mirante.singular.form.view.SViewListByMasterDetail;
import br.net.mirante.singular.form.view.SViewListByTable;

import java.util.Optional;

import static br.net.mirante.singular.exemplos.ggtox.primariasimplificada.form.SPackagePPSCommon.ppsService;
import static br.net.mirante.singular.exemplos.ggtox.primariasimplificada.form.STypePeticaoPrimariaSimplificada.OBRIGATORIO;
import static br.net.mirante.singular.exemplos.ggtox.primariasimplificada.form.STypePeticaoPrimariaSimplificada.QUANTIDADE_MINIMA;
import static br.net.mirante.singular.form.util.SingularPredicates.*;


@SInfoType(spackage = SPackagePPSCommon.class)
public class STypeEstudosResiduos extends STypePersistentComposite {

    private EstudoResiduo estudoResiduo;

    @Override
    protected void onLoadType(TypeBuilder tb) {
        super.onLoadType(tb);

        this.asAtr()
                .label("Estudo de Resíduos");

        estudoResiduo = new EstudoResiduo(this);

        estudoResiduo
                .ensaio
                .root
                .asAtr().dependsOn(estudoResiduo.origemEstudo)
                .exists(typeValueIsEqualsTo(estudoResiduo.origemEstudo, EstudoResiduo.ESTUDO_NOVO));
    }

    public class EstudoResiduo {

        public static final String ESTUDO_PUBLICADO              = "Publicado pela ANVISA";
        public static final String ESTUDO_MATRIZ                 = "Conforme matriz";
        public static final String ESTUDO_NOVO                   = "Novo";
        public static final String NOME_OUTRA_CULTURA_FIELD_NAME = "nomeOutraCultura";
        public static final String CULTURAS_PATH                 = "culturas";
        public static final String ORIGEM_ESTUDO_PATH            = "origemEstudo";
        public static final String CULTURA                       = "cultura";
        public static final String NOME_CULTURA                  = "nomeCultura";
        public static final String OUTRA_CULTURA                 = "outraCultura";
        public static final String APLICACOES                    = "aplicacoes";
        public static final String INTERVALO_PRETENDIDO          = "intervaloPretendido";

        private final STypeList<STypeComposite<SIComposite>, SIComposite> root;
        private final STypeComposite<SIComposite>                         rootType;
        final         STypeString                                         origemEstudo;
        public final  Ensaio                                              ensaio;

        public EstudoResiduo(STypeComposite<SIComposite> parentType) {

            root = parentType.addFieldListOfComposite(CULTURAS_PATH, CULTURA);
            rootType = root.getElementsType();

            final STypeComposite<SIComposite> cultura     = rootType.addFieldComposite(CULTURA);
            final STypeLong                   codCultura  = cultura.addField("codCultura", STypeLong.class);
            final STypeLong                   codSubgrupo = cultura.addField("codSubgrupo", STypeLong.class);
            final STypeString                 nomeCultura = cultura.addField(NOME_CULTURA, STypeString.class);

            final STypeString nomeOutraCultura = rootType.addFieldString(NOME_OUTRA_CULTURA_FIELD_NAME);

            final STypeComposite<SIComposite> emprego     = rootType.addFieldComposite("emprego");
            final STypeLong                   codEmprego  = emprego.addField("codEmprego", STypeLong.class);
            final STypeString                 nomeEmprego = emprego.addField("nomeEmprego", STypeString.class);

            final STypeBoolean outraCultura = rootType.addFieldBoolean(OUTRA_CULTURA);

            final STypeBoolean                parteComestivel     = rootType.addFieldBoolean("parteComestivel");
            final STypeInteger                intervaloPretendido = rootType.addFieldInteger(INTERVALO_PRETENDIDO);
            final STypeComposite<SIComposite> norma               = rootType.addFieldComposite("norma");
            final STypeInteger                idNorma             = norma.addFieldInteger("idNorma");
            final STypeString                 descricaoNorma      = norma.addFieldString("descricaoNorma");
            final STypeInteger                aplicacoes          = rootType.addFieldInteger(APLICACOES);
            final STypeString                 observacoes         = rootType.addFieldString("observacoes");

            origemEstudo = rootType.addFieldString(ORIGEM_ESTUDO_PATH);

            final STypeString                 estudoPublicado       = rootType.addFieldString("estudoPublicado");
            final STypeString                 numeroEstudo          = rootType.addFieldString("numeroEstudo");
            final STypeComposite<SIComposite> unidadeMediadaDosagem = rootType.addFieldComposite("unidadeMediadaDosagem");
            final STypeInteger                idDosagem             = unidadeMediadaDosagem.addFieldInteger("idDosagem");
            final STypeString                 siglaDosagem          = unidadeMediadaDosagem.addFieldString("siglaDosagem");
            final STypeBoolean                adjuvante             = rootType.addFieldBoolean("adjuvante");

            ensaio = new Ensaio(rootType);

            final STypeAttachment estudoResiduo = rootType.addFieldAttachment("estudoResiduo");

            root
                    .withView(new SViewListByMasterDetail()
                            .col("Cultura", si -> {
                                if (si instanceof SIComposite) {
                                    return Optional
                                            .ofNullable(((SIComposite) si).getField("cultura"))
                                            .map(sic -> ((SIComposite) sic).getField("nomeCultura"))
                                            .map(SInstance::getValue)
                                            .map(Object::toString)
                                            .orElse(Value.of(si, NOME_OUTRA_CULTURA_FIELD_NAME));
                                } else {
                                    return null;
                                }
                            })
                            .col(emprego)
                            .col(origemEstudo)
                            .largeSize()
                    );

            cultura
                    .asAtrBootstrap()
                    .colPreference(6)
                    .asAtr()
                    .label("Cultura")
                    .required(OBRIGATORIO)
                    .dependsOn(outraCultura, origemEstudo)
                    .exists(allMatches(typeValueIsNotEqualsTo(outraCultura, Boolean.TRUE)));

            cultura
                    .selection()
                    .id(codCultura)
                    .display(nomeCultura)
                    .simpleProvider((SSimpleProvider) builder -> {
                        ppsService(builder.getCurrentInstance()).buscarCulturas()
                                .forEach(culturaEntity -> builder.add()
                                        .set(codCultura, culturaEntity.getCod())
                                        .set(codSubgrupo, Optional.ofNullable(culturaEntity.getSubgrupo()).map(SubgrupoEntity::getCod).orElse(null))
                                        .set(nomeCultura, culturaEntity.getNome()));
                    });

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
                    .simpleProvider((SSimpleProvider) builder -> ppsService(builder.getCurrentInstance())
                            .buscarModalidadesDeEmprego()
                            .forEach(empregoEntity -> {
                                builder
                                        .add()
                                        .set(codEmprego, empregoEntity.getCod())
                                        .set(nomeEmprego, empregoEntity.getNome());
                            }));

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
                    .simpleProvider(builder -> ppsService(builder.getCurrentInstance())
                            .buscarNormas()
                            .forEach(normaEntity -> builder.add()
                                    .set(idNorma, normaEntity.getCod())
                                    .set(descricaoNorma, normaEntity.getNome())));

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


            origemEstudo
                    .withRadioView()
                    .selectionOf(ESTUDO_MATRIZ, ESTUDO_PUBLICADO, ESTUDO_NOVO)
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Origem do Estudo")
                    .asAtrBootstrap()
                    .newRow();

            estudoPublicado
                    .asAtr()
                    .required(OBRIGATORIO)
                    .maxLength(20)
                    .label("Código do Estudo Publicado pela ANVISA")
                    .dependsOn(origemEstudo)
                    .exists(typeValueIsEqualsTo(origemEstudo, ESTUDO_PUBLICADO))
                    .asAtrBootstrap().newRow();

            numeroEstudo
                    .asAtr()
                    .label("Número do Estudo")
                    .required(OBRIGATORIO)
                    .dependsOn(origemEstudo)
                    .exists(typeValueIsEqualsTo(origemEstudo, ESTUDO_NOVO))
                    .asAtrBootstrap()
                    .colPreference(4);

            unidadeMediadaDosagem
                    .asAtr()
                    .required(OBRIGATORIO)
                    .dependsOn(origemEstudo)
                    .exists(typeValueIsEqualsTo(origemEstudo, ESTUDO_NOVO))
                    .label("Unidade de medida da dosagem")
                    .asAtrBootstrap()
                    .colPreference(4);

            unidadeMediadaDosagem
                    .selection()
                    .id(idDosagem)
                    .display(siglaDosagem)
                    .simpleProvider(builder -> {
                        ppsService(builder.getCurrentInstance()).buscarTipoDeDose()
                                .forEach(doseEntity -> builder.add()
                                        .set(idDosagem, doseEntity.getCod())
                                        .set(siglaDosagem, doseEntity.getNome()));
                    });

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
                    .dependsOn(origemEstudo)
                    .exists(typeValueIsEqualsTo(origemEstudo, ESTUDO_NOVO))
                    .asAtrBootstrap()
                    .colPreference(4);


            estudoResiduo
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Estudo de Resíduo")
                    .dependsOn(origemEstudo)
                    .exists(typeValueIsEqualsTo(origemEstudo, ESTUDO_NOVO));
        }

    }

    public class Ensaio {

        public final STypeList<STypeComposite<SIComposite>, SIComposite> root;
        public final STypeComposite<SIComposite>                         rootType;
        public       STypeString                                         codigoEnsaio;
        public       STypeComposite<SIComposite>                         estado;
        public       STypeString                                         nomeEstado;
        public       STypeString                                         siglaEstado;
        public       STypeComposite<SIComposite>                         cidade;
        public       STypeInteger                                        idCidade;
        public       STypeString                                         nomeCidade;
        public       Amostra                                             amostra;

        public Ensaio(STypeComposite<SIComposite> parent) {
            this.root = parent.addFieldListOfComposite("ensaios", "ensaio");
            this.rootType = root.getElementsType();
            create();
            configure();
        }

        private void create() {
            codigoEnsaio = rootType.addField("codigoEnsaio", STypeString.class);
            estado = rootType.addFieldComposite("estado");
            nomeEstado = estado.addFieldString("nome");
            siglaEstado = estado.addFieldString("sigla");
            cidade = rootType.addFieldComposite("cidade");
            idCidade = cidade.addFieldInteger("id");
            nomeCidade = cidade.addFieldString("nome");
        }

        private void configure() {

            codigoEnsaio
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("ID do Ensaio")
                    .asAtrBootstrap()
                    .colPreference(3);

            estado
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Estado")
                    .asAtrBootstrap()
                    .colPreference(3);

            estado.selectionOf(EstadoDTO.class)
                    .id(EstadoDTO::getSigla)
                    .display("${nome} - ${sigla}")
                    .autoConverterOf(EstadoDTO.class)
                    .simpleProvider(ins -> SelectBuilder.buildEstados());

            cidade
                    .asAtr()
                    .required(inst -> OBRIGATORIO)
                    .label("Cidade")
                    .enabled(inst -> Value.notNull(inst, siglaEstado))
                    .dependsOn(estado)
                    .asAtrBootstrap()
                    .colPreference(3);

            cidade.selectionOf(CidadeDTO.class)
                    .id(CidadeDTO::getId)
                    .display(CidadeDTO::getNome)
                    .autoConverterOf(CidadeDTO.class)
                    .simpleProvider(i -> SelectBuilder.buildMunicipiosFiltrado((String) Value.of(i, (STypeSimple) estado.getField(siglaEstado.getNameSimple()))));

            amostra = new Amostra(rootType);

            root
                    .withView(new SViewListByMasterDetail()
                            .col(codigoEnsaio, "ID")
                            .col(estado, "Estado")
                            .col(cidade, "Cidade")
                    )
                    .asAtr().label("Ensaio");

        }
    }


    public class Amostra {

        private final STypeList<STypeComposite<SIComposite>, SIComposite> root;
        private final STypeComposite<SIComposite>                         rootType;
        public final  STypeAtivoAmostra                                   ativoAmostra;

        public Amostra(STypeComposite<SIComposite> parentType) {

            root = parentType.addFieldListOfComposite("amostras", "amostra");
            rootType = root.getElementsType();

            final STypeString  id      = rootType.addFieldString("id");
            final STypeDecimal dose    = rootType.addFieldDecimal("dose");
            final STypeInteger dat     = rootType.addFieldInteger("dat");
            final STypeDecimal loq     = rootType.addFieldDecimal("loq");
            final STypeDecimal residuo = rootType.addFieldDecimal("residuo");

            ativoAmostra = rootType.addField("ativos", STypeAtivoAmostra.class);

            final STypeBoolean                                        tempoMaior30Dias    = rootType.addFieldBoolean("tempoMaior30Dias");
            final STypeAttachment                                     estudoEstabilidade  = rootType.addFieldAttachment("estudoEstabilidade");
            final STypeBoolean                                        metabolito          = rootType.addFieldBoolean("metabolito");
            final STypeList<STypeComposite<SIComposite>, SIComposite> metabolitos         = rootType.addFieldListOfComposite("metabolitos", "metabolito");
            final STypeString                                         descricaoMetabolito = metabolitos.getElementsType().addFieldString("descricao");
            final STypeDecimal                                        loqMetabolito       = metabolitos.getElementsType().addFieldDecimal("loqMetabolito");
            final STypeDecimal                                        residuoMetabolito   = metabolitos.getElementsType().addFieldDecimal("residuoMetabolito");

            root
                    .withView(new SViewListByMasterDetail()
                            .col(id, "Id")
                            .col(dose, "Dose")
                            .col(ativoAmostra.nomeComumPortugues, "Ingrediente Ativo")
                            .col(residuo, "Residuo")
                            .col(dat, "DAT")
                    )
                    .asAtr().label("Amostras");

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
                    .addInstanceValidator(new ResiduoValidator(loq))
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Resíduo (mg/KG)")
                    .fractionalMaxLength(4);

            ativoAmostra
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Ingrediente Ativo da Amostra (informados na seção de ativos)")
                    .asAtrBootstrap()
                    .colPreference(6);

            ativoAmostra
                    .asAtr()
                    .dependsOn(rootType.getDictionary().getType(STypeIngredienteAtivoPeticaoPrimariaSimplificada.class).getField(STypeIngredienteAtivoPeticaoPrimariaSimplificada.FIELD_NAME_LIST_ATIVOS));

            ativoAmostra
                    .withUpdateListener(new IngredienteAtivoUpdateListener<>());

            tempoMaior30Dias
                    .asAtr()
                    .label("Tempo Entre Análise e Colheita Maior que 30 Dias")
                    .asAtrBootstrap().colPreference(6)
                    .newRow();

            estudoEstabilidade
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Estudo de Estabilidade")
                    .dependsOn(tempoMaior30Dias)
                    .exists(typeValueIsTrue(tempoMaior30Dias));

            metabolito
                    .withRadioView()
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Metabólito");

            metabolitos
                    .withMiniumSizeOf(QUANTIDADE_MINIMA)
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
                    .addInstanceValidator(new ResiduoValidator(loqMetabolito))
                    .asAtr()
                    .required(OBRIGATORIO)
                    .label("Resíduo (mg/KG)")
                    .fractionalMaxLength(4);
        }

    }

}