package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeAttachmentList;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIBoolean;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.view.SViewAttachmentImage;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewListByForm;

import javax.annotation.Nonnull;

@SInfoType(spackage = RequirementsamplePackage.class)
public class STypeDadosPessoais extends STypeComposite<SIComposite> {

    public STypeString                          nomeCompleto;
    public STypeString                          nomeMae;
    public STypeString                          nomePai;
    public STypeTelefoneNacional                telefone;
    public STypeAttachmentList                  documentos;
    public STypeBoolean                         naoTenhoFotoCachorro;
    public STypeAttachment                      fotoDoCachorro;
    public STypeAttachmentList                  documentacaoComprobatoria;
    public STypeList<STypeAddress, SIComposite> listEnderecos;
    public STypeBoolean                         brasileiro;


    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().label("Dados Pessoais");
        this.asAtrAnnotation().setAnnotated();

        nomeCompleto = addField("nomeCompleto", STypeString.class);
        nomeCompleto.asAtr().enabled(false);
        nomeMae = addField("nomeMae", STypeString.class);
        nomePai = addField("nomePai", STypeString.class);
        telefone = addField("telefone", STypeTelefoneNacional.class);

        nomeCompleto.asAtr().label("Nome Completo").asAtrBootstrap().colPreference(6);
        nomeMae.asAtr().label("Nome Mãe").asAtrBootstrap().colPreference(6);
        nomePai.asAtr().label("Nome Pai").asAtrBootstrap().colPreference(6);
        telefone.asAtr().label("Telefone").asAtrBootstrap().colPreference(6);

        nomeCompleto.asAtrAnnotation().setAnnotated();
        nomePai.asAtrAnnotation().setAnnotated();
        documentos = this.addFieldListOfAttachment("documentos", "documento");
        documentos.asAtr().label("Documentos");
        documentos.withMaximumSizeOf(10);
        documentos.withMiniumSizeOf(0);
        documentos.asAtr().required(false);
        documentos.asAtr().dependsOn(nomeCompleto);


        naoTenhoFotoCachorro = this.addFieldBoolean("naoTenhoFotoCachorro");
        naoTenhoFotoCachorro.asAtr().label("Não tenho cachorro");
        naoTenhoFotoCachorro.asAtr().required(false);
        naoTenhoFotoCachorro.asAtr().enabled(p -> p.findNearest(brasileiro).map(SIBoolean::getValue).orElse(Boolean.FALSE));

        fotoDoCachorro = this.addFieldAttachment("fotoDoCachorro");
        fotoDoCachorro.withView(SViewAttachmentImage::new);
        fotoDoCachorro.asAtr().required(false);
        fotoDoCachorro.asAtr().label("Foto do cachorro");
        fotoDoCachorro.asAtr().dependsOn(naoTenhoFotoCachorro);
        fotoDoCachorro.asAtr().enabled(fci -> !fci.findNearest(naoTenhoFotoCachorro).map(SIBoolean::getValue).orElse(Boolean.FALSE));

        documentacaoComprobatoria = this.addFieldListOfAttachment("documentacaoComprobatoria", "documento");
        documentacaoComprobatoria.asAtr().label("Documentação comprobatória de que não possui cachorro");
        documentacaoComprobatoria.asAtr().dependsOn(naoTenhoFotoCachorro);
        documentacaoComprobatoria.getElementsType().asAtr().allowedFileTypes("pdf");
        documentacaoComprobatoria.asAtr().required(false);
        documentacaoComprobatoria.withMiniumSizeOf(0);
        documentacaoComprobatoria.asAtr().enabled(fci -> fci.findNearest(naoTenhoFotoCachorro).map(SIBoolean::getValue).orElse(Boolean.FALSE));

        brasileiro = this.addFieldBoolean("brasileiro");
        brasileiro.asAtr().label("Brasileiro");
        brasileiro.asAtr().enabled(true);

        listEnderecos = this.addFieldListOf("listEnderecos", STypeAddress.class);
        listEnderecos.asAtr().label("Endereços");
        listEnderecos.withView(SViewListByForm::new);


        this.withView(new SViewByBlock(), block -> block.newBlock()
                .add(nomeCompleto)
                .add(nomeMae)
                .add(nomePai)
                .add(telefone)
                .add(documentos));

    }
}