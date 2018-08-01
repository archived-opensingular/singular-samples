package org.sample.form;

import java.util.Optional;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
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
import org.opensingular.form.type.util.STypeLatitudeLongitudeGMaps;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewListByMasterDetail;
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
public class STypeListaComponentes extends STypeComposite<SIComposite> {

    public STypeBoolean naoTenhoFotoCachorro;
    public STypeAttachmentList documentacaoComprobatoria;
    public STypeHTML richText;
    public STypeHTML richText2;
    public STypeHTML richText3;

    public STypeList<STypeListaExemplo, SIComposite> listaExemplo;

    public STypeLatitudeLongitudeGMaps coordenada;


    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().label("Componentes");
        this.asAtrAnnotation().setAnnotated();

        coordenada = this.addField("coordenada", STypeLatitudeLongitudeGMaps.class);
        coordenada
                .asAtr().subtitle("subtitle maps").label("Maps").required();

        listaExemplo = this.addFieldListOf("listaExemplo", STypeListaExemplo.class);
        listaExemplo.withView(SViewListByMasterDetail::new);
        listaExemplo.asAtr().label("Lista Exemplo");


        naoTenhoFotoCachorro = this.addFieldBoolean("naoTenhoFotoCachorro");
        naoTenhoFotoCachorro.asAtr().label("Não tenho cachorro").subtitle("teste subtitle");
        naoTenhoFotoCachorro.asAtr().required(false);

        documentacaoComprobatoria = this.addFieldListOfAttachment("documentacaoComprobatoria", "documento");
        documentacaoComprobatoria.asAtr().label("Documentação comprobatória de que não possui cachorro");
        documentacaoComprobatoria.asAtr().dependsOn(naoTenhoFotoCachorro);
        documentacaoComprobatoria.getElementsType().asAtr().allowedFileTypes("pdf");
        documentacaoComprobatoria.asAtr().required(false);
        documentacaoComprobatoria.withMiniumSizeOf(0);
        documentacaoComprobatoria.asAtr().enabled(fci -> fci.findNearest(naoTenhoFotoCachorro).map(SIBoolean::getValue).orElse(Boolean.FALSE));

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

        this.withView(new SViewByBlock(), block -> block.newBlock());

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
                sInstance.ifPresent(s -> {
                    STypeListaExemplo sTypeModal = (STypeListaExemplo) sInstance.get().getType();
                    String value = s.getField(sTypeModal.nome2).getValue();
                    if(StringUtils.isNotEmpty(value)){
                        richTextContext.setReturnValue(value);
                    } else {
                        richTextContext.setReturnValue("");
                        /*Por default se o valor returnValue for null então não é realizado nenhuma ação*/
                    }
                });
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