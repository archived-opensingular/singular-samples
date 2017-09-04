package org.opensingular.formsamples.crud.types.antaq;

import java.util.function.Predicate;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeAttachmentList;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.attachment.SIAttachment;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.form.util.SingularPredicates;
import org.opensingular.form.view.SMultiSelectionByCheckboxView;
import org.opensingular.form.view.SViewByBlock;

import static org.opensingular.form.util.SingularPredicates.allMatches;

@SInfoType(spackage = AntaqPackage.class, newable = false, name = "HabilitacaoEmpresa")
public class STypeHabilitacaoEmpresa extends STypeComposite<SIComposite> {

    public static final String              PDF = "pdf";

    public STypeComposite<SIComposite>      empresaBrasileiraComp;
    public STypeAttachment                  anexoEmp1;
    public STypeList<STypeString, SIString> cnae;
    public STypeComposite<SIComposite>      contratoSocialComp;

    public STypeAttachment                  anexoContratoSocial2;
    public STypeAttachment                  anexoContratoSocial3;
    public STypeAttachment                  anexoContratoSocial4;
    public STypeAttachment                  anexoContratoSocial5;

    public STypeComposite<SIComposite>      demontracoesContabeisComp;
    public STypeAttachment                  anexoCon1;
    public STypeAttachment                  anexoCon2;
    public STypeAttachment                  anexoCon3;

    public STypeComposite<SIComposite>      certidoesComp;
    public STypeBoolean                     enviarDeclaracaoReg;
    public STypeAttachment                  anexoCert1;
    public STypeAttachment                  anexoCert2;
    public STypeAttachment                  anexoCert3;
    public STypeAttachment                  anexoCert4;
    public STypeAttachment                  anexoCert5;
    public STypeAttachment                  anexoCert6;
    //    public STypeAttachment             anexoCert7;

    public STypeAttachment                  declaracaoRegularidade;
    public STypeAttachment                  declaracaoSimplesNacional;
    public STypeAttachmentList              outros;

    @Override
    protected void onLoadType(TypeBuilder tb) {

        empresaBrasileiraComp = addFieldComposite("empresaBrasileiraComp");
        empresaBrasileiraComp
            .asAtrAnnotation()
            .setAnnotated();

        anexoEmp1 = empresaBrasileiraComp.addField("anexoEmp1", STypeAttachment.class);
        anexoEmp1
            .asAtr()
            .label("Comprovante de Inscrição no CNPJ")
            .allowedFileTypes(PDF)
            .required(Resolucao912Form.OBRIGATORIO)
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        cnae = empresaBrasileiraComp.addFieldListOf("cnae", STypeString.class);
        cnae.asAtr()
            .label("CNAE")
            .required(Resolucao912Form.OBRIGATORIO)
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();
        cnae.selectionOf(String.class, new SMultiSelectionByCheckboxView()).selfIdAndDisplay().simpleProviderOf("5021-1/02 - Carga ", "5022-0/02 - Passageiros");

        contratoSocialComp = addFieldComposite("contratoSocialComp");
        contratoSocialComp
            .asAtr()
            .subtitle("Pelo menos um dos anexos deve ser enviado.")
            .asAtrAnnotation()
            .setAnnotated();

        anexoContratoSocial2 = contratoSocialComp.addField("anexoContratoSocial2", STypeAttachment.class);
        anexoContratoSocial2
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Contrato/Estatuto Social")
            .asAtrBootstrap()
            .colPreference(6);

        anexoContratoSocial3 = contratoSocialComp.addField("anexoContratoSocial3", STypeAttachment.class);
        anexoContratoSocial3
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Declaração de Firma Individual")
            .asAtrBootstrap()
            .colPreference(6);

        anexoContratoSocial4 = contratoSocialComp.addField("anexoContratoSocial4", STypeAttachment.class);
        anexoContratoSocial4
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Requerimento de empresário")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoContratoSocial5 = contratoSocialComp.addField("anexoContratoSocial5", STypeAttachment.class);
        anexoContratoSocial5
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Ata de Eleição dos administradores")
            .subtitle("com mandato em vigor, para as sociedades por ações")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoContratoSocial2
            .asAtr()
            .required(allMatches((i) -> Resolucao912Form.OBRIGATORIO, this.atLeast(1, anexoContratoSocial2, anexoContratoSocial3, anexoContratoSocial4, anexoContratoSocial5)));

        anexoContratoSocial3
            .asAtr()
            .required(allMatches((i) -> Resolucao912Form.OBRIGATORIO, this.atLeast(1, anexoContratoSocial2, anexoContratoSocial3, anexoContratoSocial4, anexoContratoSocial5)));

        anexoContratoSocial4
            .asAtr()
            .required(allMatches((i) -> Resolucao912Form.OBRIGATORIO, this.atLeast(1, anexoContratoSocial2, anexoContratoSocial3, anexoContratoSocial4, anexoContratoSocial5)));

        anexoContratoSocial5
            .asAtr()
            .required(allMatches((i) -> Resolucao912Form.OBRIGATORIO, this.atLeast(1, anexoContratoSocial2, anexoContratoSocial3, anexoContratoSocial4, anexoContratoSocial5)));

        demontracoesContabeisComp = addFieldComposite("demontracoesContabeisComp");
        demontracoesContabeisComp
            .asAtrAnnotation()
            .setAnnotated();

        anexoCon1 = demontracoesContabeisComp.addField("anexoCon1", STypeAttachment.class);
        anexoCon1
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Balanço Patrimonial e demais Demonstrações Contábeis do último Exercício Social")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCon2 = demontracoesContabeisComp.addField("anexoCon2", STypeAttachment.class);
        anexoCon2
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Balanço de abertura no caso de empresa recém criada, relativo a sua constituição")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCon3 = demontracoesContabeisComp.addField("anexoCon3", STypeAttachment.class);
        anexoCon3
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Documentação contábil simplificada (somente para Micro Empresas e Empresas de Pequeno Porte optantes do Simples Nacional)")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        declaracaoSimplesNacional = demontracoesContabeisComp.addField("declaracaoSimplesNacional", STypeAttachment.class);
        declaracaoSimplesNacional
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Declaração de optante pelo Simples Nacional")
            .dependsOn(anexoCon3)
            .exists(p -> !p.findNearest(anexoCon3).filter(SIAttachment::isEmptyOfData).isPresent())
            .required(p -> !p.findNearest(anexoCon3).filter(SIAttachment::isEmptyOfData).isPresent())
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCon1
            .asAtr()
            .required(allMatches((i) -> Resolucao912Form.OBRIGATORIO, this.atLeast(1, anexoCon1, anexoCon2, anexoCon3)));

        anexoCon2
            .asAtr()
            .required(allMatches((i) -> Resolucao912Form.OBRIGATORIO, this.atLeast(1, anexoCon1, anexoCon2, anexoCon3)));

        anexoCon3
            .asAtr()
            .required(allMatches((i) -> Resolucao912Form.OBRIGATORIO, this.atLeast(1, anexoCon1, anexoCon2, anexoCon3)));

        certidoesComp = addFieldComposite("certidoesComp");
        certidoesComp
            .asAtr()
            .label("Certidões")
            .asAtrAnnotation()
            .setAnnotated();

        enviarDeclaracaoReg = certidoesComp.addFieldBoolean("enviarDeclaracaoReg");
        enviarDeclaracaoReg
            .withRadioView()
                .setInitialValue(Boolean.FALSE)
            .asAtr()
            .label("Deseja enviar a Declaração de Regularidade ?")
            .required(Resolucao912Form.OBRIGATORIO)
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        declaracaoRegularidade = certidoesComp.addField("declaracaoRegularidade", STypeAttachment.class);
        declaracaoRegularidade
            .asAtr()
            .allowedFileTypes(PDF)
            .label("Declaração de Regularidade")
            .dependsOn(enviarDeclaracaoReg)
            .exists(i -> i.findNearest(enviarDeclaracaoReg).filter(ii -> Boolean.TRUE.equals(ii.getValue())).isPresent())
            .required(i -> i.findNearest(enviarDeclaracaoReg).filter(ii -> Boolean.TRUE.equals(ii.getValue())).isPresent())
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCert1 = certidoesComp.addField("anexoCert1", STypeAttachment.class);
        anexoCert1
            .asAtr()
            .required(SingularPredicates.allMatches(i -> Resolucao912Form.OBRIGATORIO, this::masterDeclaracaoRegularidade))
            .allowedFileTypes(PDF)
            .label("Certidão Negativa de Falência / concordata / recuperação judicial / recuperação extrajudicial")
            .asAtrBootstrap()
            .newRow()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCert2 = certidoesComp.addField("anexoCert2", STypeAttachment.class);
        anexoCert2
            .asAtr()
            .required(SingularPredicates.allMatches(i -> Resolucao912Form.OBRIGATORIO, this::masterDeclaracaoRegularidade))
            .allowedFileTypes(PDF)
            .label("Certidão Conjunta Negativa de Débitos Relativos a Tributos Federais e a Dívida Ativa da União")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCert3 = certidoesComp.addField("anexoCert3", STypeAttachment.class);
        anexoCert3
            .asAtr()
            .required(SingularPredicates.allMatches(i -> Resolucao912Form.OBRIGATORIO, this::masterDeclaracaoRegularidade))
            .allowedFileTypes(PDF)
            .label("Prova de regularidade perante a Fazenda Estadual")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCert4 = certidoesComp.addField("anexoCert4", STypeAttachment.class);
        anexoCert4
            .asAtr()
            .required(SingularPredicates.allMatches(i -> Resolucao912Form.OBRIGATORIO, this::masterDeclaracaoRegularidade))
            .allowedFileTypes(PDF)
            .label("Prova de regularidade perante a Fazenda Municipal")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCert5 = certidoesComp.addField("anexoCert5", STypeAttachment.class);
        anexoCert5
            .asAtr()
            .required(SingularPredicates.allMatches(i -> Resolucao912Form.OBRIGATORIO, this::masterDeclaracaoRegularidade))
            .allowedFileTypes(PDF)
            .label("Prova de regularidade perante o FGTS")
            .asAtrBootstrap()
            .colPreference(6)
            .asAtrAnnotation()
            .setAnnotated();

        anexoCert6 = certidoesComp.addField("anexoCert6", STypeAttachment.class);
        anexoCert6
            .asAtr()
            .required(SingularPredicates.allMatches(i -> Resolucao912Form.OBRIGATORIO, this::masterDeclaracaoRegularidade))
            .allowedFileTypes(PDF)
            .label("Prova de regularidade perante o INSS")
            .asAtrBootstrap()
            .colPreference(6);

        outros = this.addFieldListOfAttachment("outros", "outroDocumento");
        outros
            .asAtr()
            .label("Outros")
            .asAtrBootstrap()
            .colPreference(12)
            .asAtrAnnotation()
            .setAnnotated();
        outros.getElementsType().asAtr().allowedFileTypes(PDF);

        this.withView(new SViewByBlock(), v -> v
            .newBlock("Empresa")
            .add(empresaBrasileiraComp)
            .newBlock("Contrato Social (enviar ao menos um)")
            .add(contratoSocialComp)
            .newBlock("Balanço Patrimonial/Demonstrações contábeis (enviar ao menos um)")
            .add(demontracoesContabeisComp)
            .newBlock()
            .add(certidoesComp)
            .newBlock("Outras declarações")
            .add(outros));
    }

    private Predicate<SInstance> atLeast(int quantity, STypeAttachment... types) {
        Predicate<SInstance> atLeast = (t) -> {
            int count = 0;
            for (STypeAttachment sType : types) {
                if (!t.findNearest(sType).filter(SIAttachment::isEmptyOfData).isPresent()) {
                    count++;
                }
            }

            if (count >= quantity) {
                return false;
            } else {
                return true;
            }
        };

        return atLeast;
    }

    private boolean masterDeclaracaoRegularidade(SInstance ins) {
        return !ins.findNearest(enviarDeclaracaoReg).filter(p -> Boolean.TRUE.equals(p.getValue())).isPresent();
    }

}
