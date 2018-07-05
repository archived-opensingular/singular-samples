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
import org.opensingular.form.type.core.STypePassword;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeLatitudeLongitudeGMaps;
import org.opensingular.form.view.SViewAttachmentImage;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewCheckBoxLabelAbove;
import org.opensingular.form.view.SViewListByMasterDetail;
import org.opensingular.form.view.SViewPassword;
import org.opensingular.form.view.SViewListByTable;
import org.opensingular.form.view.richtext.RichTextAction;
import org.opensingular.form.view.richtext.RichTextContentContext;
import org.opensingular.form.view.richtext.RichTextInsertContext;
import org.opensingular.form.view.richtext.RichTextSelectionContext;
import org.opensingular.form.view.richtext.SViewByRichText;
import org.opensingular.form.view.richtext.SViewByRichTextNewTab;
import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.requirement.sei30.features.SILinkSEI;
import org.opensingular.requirement.sei30.features.SIModeloSEI;
import org.opensingular.requirement.sei30.features.SViewSEIRichText;

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
    public STypeHTML richText3;

    public STypeList<STypeListaExemplo, SIComposite> listaExemplo;

    public STypeString campo1;
    public STypePassword campo2;
    public STypeLatitudeLongitudeGMaps coordenada;


    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().label("Dados Pessoais");
        this.asAtrAnnotation().setAnnotated();

        coordenada = this.addField("coordenada", STypeLatitudeLongitudeGMaps.class);
        coordenada
                .asAtr().subtitle("subtitle maps").label("Maps").required();

        campo1 = addFieldString("campo1");
        campo2 = addFieldPassword("campo2");
        campo1.asAtr().label("CAMPO 1").asAtrBootstrap().colPreference(6);
        campo2.asAtr().label("CAMPO 2").asAtrBootstrap().colPreference(6);
        campo2.withValueAttributeTrim(false);
        campo2.withView(new SViewPassword().setResetPassword(false));
        campo1.withValueAttributeTrim(true);

        campo1.asAtrAnnotation().setAnnotated();
        campo2.asAtrAnnotation().setAnnotated();

        listaExemplo = this.addFieldListOf("listaExemplo", STypeListaExemplo.class);
        listaExemplo.withView(SViewListByTable::new);
        listaExemplo.asAtr().label("Lista Exemplo");

        nomeCompleto = addField("nomeCompleto", STypeString.class);
        nomeMae = addField("nomeMae", STypeString.class);
        nomeMae.asAtr().enabled(false);
        nomePai = addField("nomePai", STypeString.class);
        telefone = addField("telefone", STypeTelefoneNacional.class);

        nomePai.asAtr().dependsOn(nomeCompleto);
        nomeCompleto.asAtr().label("Nome Completo").subtitle("teste subtitle").asAtrBootstrap().colPreference(4).colMd(4);
        nomeMae.asAtr().label("Nome Mãe").asAtrBootstrap().colPreference(4).colMd(4);
        nomePai.asAtr().label("Nome Pai").asAtrBootstrap().colPreference(4).colMd(4);
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
        naoTenhoFotoCachorro.asAtr().label("Não tenho cachorro").subtitle("teste subtitle");
        naoTenhoFotoCachorro.asAtr().required(false);

        fotoDoCachorro = this.addFieldAttachment("fotoDoCachorro");
        fotoDoCachorro.withView(SViewAttachmentImage::new);
        fotoDoCachorro.asAtr().required(false);
        fotoDoCachorro.asAtr().label("Foto do cachorro").subtitle("teste subtitle");;
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
        brasileiro.asAtr().label("Brasileiro").subtitle("teste subtitle");
        brasileiro.withView(SViewCheckBoxLabelAbove::new);
        brasileiro.asAtr().enabled(true);

        listEnderecos = this.addFieldListOf("listEnderecos", STypeAddress.class);
        listEnderecos.asAtr().label("Endereços");
        listEnderecos.getElementsType().bairro.asAtr().required(true);
        listEnderecos.withView(SViewListByMasterDetail::new);
        listEnderecos.asAtrIndex().indexed(Boolean.TRUE);

        richText = this.addField("richText", STypeHTML.class);
        SViewByRichText sViewByRichText = new SViewByRichText();
        sViewByRichText.setDisablePageLayout(true);
        richText.withView(sViewByRichText);
        richText.asAtr().label("TESTE RICHT TEXT");


        richText2 = this.addField("richText2", STypeHTML.class);
        richText2.asAtr().label("TESTE RICHT TEXT 2");


        richText3 = this.addField("richText3", STypeHTML.class);
        richText3.asAtr().label("TESTE RICHT TEXT 3");

        SViewByRichTextNewTab sViewByRichText2 = new SViewByRichTextNewTab();
        sViewByRichText2.addAction(createMockInsertButton("Inserir"));
        sViewByRichText2.addAction(createMockSelectButton("selecionar"));
        sViewByRichText2.addAction(createMockContentButton("conteudo"));
        richText2.withView(sViewByRichText2);


        richText3.withView(SViewSEIRichText
                .configProtocoloToIdSEIAction(SILinkSEI::getProtocolo)
                .configureModeloSEIAction(SIModeloSEI::getProtocoloModelo)
                .getConfiguration()
                .setDoubleClickDisabledForCssClasses("")
                .getView());


        this.withView(new SViewByBlock(), block -> block.newBlock()
                .add(campo1).add(campo2).add(listaExemplo)
                .add(nomeCompleto)
                .add(nomeMae)
                .add(nomePai)
                .add(telefone)
                .add(documentos)
                .add(coordenada)
                .add(richText));
    }


    private RichTextAction createMockInsertButton(String label) {

        return new RichTextAction<RichTextInsertContext>() {
            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public Icon getIcon() {
                return DefaultIcons.ARCHIVE;
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
            }

        };
    }

    private RichTextAction createMockSelectButton(String label) {

        return new RichTextAction<RichTextSelectionContext>() {
            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public Icon getIcon() {
                return DefaultIcons.USERS;
            }

            @Override
            public Optional<Class<? extends SType<?>>> getForm() {
                return Optional.of(STypeListaExemplo.class);
            }

            @Override
            public Class<? extends RichTextSelectionContext> getType() {
                return RichTextSelectionContext.class;
            }

            @Override
            public void onAction(RichTextSelectionContext richTextContext, Optional<SInstance> sInstance) {
                richTextContext.setReturnValue(richTextContext.getTextSelected().toUpperCase());
            }

        };
    }

    private RichTextAction createMockContentButton(String label) {

        return new RichTextAction<RichTextContentContext>() {
            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public Icon getIcon() {
                return DefaultIcons.ROCKET;
            }

            @Override
            public Optional<Class<? extends SType<?>>> getForm() {
                return Optional.of(STypeListaExemplo.class);
            }

            @Override
            public Class<? extends RichTextContentContext> getType() {
                return RichTextContentContext.class;
            }

            @Override
            public void onAction(RichTextContentContext richTextContext, Optional<SInstance> sInstance) {
                richTextContext.setReturnValue(richTextContext.getContent() + " FIM.");
            }

        };
    }
}