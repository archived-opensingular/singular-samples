package org.sample.form;

import java.util.Optional;
import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.SType;
import org.opensingular.form.STypeAttachmentList;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIBoolean;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeHTML;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.view.SViewAttachmentImage;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewListByForm;
import org.opensingular.form.view.SViewListByMasterDetail;
import org.opensingular.form.view.richtext.RichTextAction;
import org.opensingular.form.view.richtext.RichTextInsertContext;
import org.opensingular.form.view.richtext.SViewByRichText;
import org.opensingular.form.view.richtext.SViewByRichTextNewTab;

@SInfoType(spackage = RequirementsamplePackage.class)
public class STypeDadosPessoais extends STypeComposite<SIComposite> {

    public STypeString nomeCompleto;
    public STypeString nomeMae;
    public STypeString nomePai;
    public STypeTelefoneNacional telefone;
    public STypeAttachmentList documentos;
    public STypeBoolean naoTenhoFotoCachorro;
    public STypeAttachment fotoDoCachorro;
    public STypeAttachmentList documentacaoComprobatoria;
    public STypeList<STypeAddress, SIComposite> listEnderecos;
    public STypeBoolean brasileiro;
    public STypeHTML richText;
    public STypeHTML richText2;

    public STypeList<STypeListaExemplo, SIComposite> listaExemplo;

    public STypeString campo1;
    public STypeString campo2;


    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().label("Dados Pessoais");
        this.asAtrAnnotation().setAnnotated();

        campo1 = addFieldString("campo1");
        campo2 = addFieldString("campo2");
        campo1.asAtr().label("CAMPO 1").asAtrBootstrap().colPreference(6);
        campo2.asAtr().label("CAMPO 2").asAtrBootstrap().colPreference(6);

        campo1.asAtr().dependsOn(campo2)
                .enabled(t -> !t.findNearest(campo2).map(SInstance::isEmptyOfData).orElse(Boolean.TRUE));
        campo1.asAtrAnnotation().setAnnotated();
        campo2.asAtrAnnotation().setAnnotated();

        listaExemplo = this.addFieldListOf("listaExemplo", STypeListaExemplo.class);
        listaExemplo.withView(SViewListByMasterDetail::new);
        listaExemplo.asAtr().label("Lista Exemplo");
//        listaExemplo.asAtrAnnotation().setAnnotated();

        nomeCompleto = addField("nomeCompleto", STypeString.class);
        nomeMae = addField("nomeMae", STypeString.class);
        nomeMae.asAtr().enabled(false);
        nomePai = addField("nomePai", STypeString.class);
        telefone = addField("telefone", STypeTelefoneNacional.class);

        nomePai.asAtr().dependsOn(nomeCompleto);
        nomeCompleto.asAtr().label("Nome Completo").asAtrBootstrap().colPreference(6);
        nomeMae.asAtr().label("Nome Mãe").asAtrBootstrap().colPreference(6);
        nomePai.asAtr().label("Nome Pai").asAtrBootstrap().colPreference(6);
        nomePai.asAtrIndex().indexed(Boolean.TRUE);
        telefone.asAtr().label("Telefone").asAtrBootstrap().colPreference(6);
        telefone.asAtrIndex().indexed(Boolean.TRUE);

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
        listEnderecos.asAtrIndex().indexed(Boolean.TRUE);

        richText = this.addField("richText", STypeHTML.class);
        SViewByRichText sViewByRichText = new SViewByRichText();
        sViewByRichText.setDisablePageLayout(true);
        richText.withView(sViewByRichText);
        richText.asAtr().label("TESTE RICHT TEXT");


        richText2 = this.addField("richText2", STypeHTML.class);
        richText2.asAtr().label("TESTE RICHT TEXT 2");

        SViewByRichTextNewTab sViewByRichText2 = new SViewByRichTextNewTab();
        sViewByRichText2.addAction(createMockButton("selecionar"));
        sViewByRichText2.addAction(createMockButton("apagar"));
        richText2.withView(sViewByRichText2);

        this.withView(new SViewByBlock(), block -> block.newBlock()
                .add(campo1).add(campo2).add(listaExemplo)
                .add(nomeCompleto)
                .add(nomeMae)
                .add(nomePai)
                .add(telefone)
                .add(documentos)
                .add(richText));
    }

    private RichTextAction createMockButton(String label) {
//        String is = null;
//        if (this.getClass().getResource("/images/finish.gif") != null) {
//            is = this.getClass().getResource("/images/finish.gif").getFile();
//        }


        String linkIcon = "https://avatars1.githubusercontent.com/u/5500999?v=2&s=16";
        return new RichTextAction<RichTextInsertContext>() {
            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public String getIconUrl() {
                return linkIcon;
            }

            @Override
            public Optional<Class<? extends SType<?>>> getForm() {
                return Optional.of(STypeListaExemplo.class);
            }

            @Override
            public Class<? extends RichTextInsertContext> getType() {
                return RichTextInsertContext.class;
            }

            @Override
            public void onAction(RichTextInsertContext richTextContext, Optional<SInstance> sInstance) {
                richTextContext.setReturnValue("teste");
                System.out.println("teste");
            }

        };
    }
}