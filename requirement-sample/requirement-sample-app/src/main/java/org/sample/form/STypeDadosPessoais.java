package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
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
import org.opensingular.form.type.util.STypeLatitudeLongitudeGMaps;
import org.opensingular.form.view.SViewAttachmentImage;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewCheckBox;
import org.opensingular.form.view.list.SViewListByMasterDetail;
import org.opensingular.form.view.richtext.SViewByRichText;
import org.opensingular.form.view.richtext.SViewByRichTextNewTab;
import org.opensingular.form.wicket.IWicketComponentMapper;
import org.opensingular.form.wicket.mapper.chosen.ChosenMapper;
import org.opensingular.requirement.sei30.features.SILinkSEI;
import org.opensingular.requirement.sei30.features.SIModeloSEI;
import org.opensingular.requirement.sei30.features.SViewSEIRichText;
import org.sample.form.generic.SIIdDescricao;
import org.sample.form.generic.STIdDescricao;

import javax.annotation.Nonnull;
import java.util.Arrays;

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
    public STypeLatitudeLongitudeGMaps coordenada;
    public STypeList<STIdDescricao, SIIdDescricao> paises;


    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().label("Dados Pessoais");
        this.asAtrAnnotation().setAnnotated();


        coordenada = this.addField("coordenada", STypeLatitudeLongitudeGMaps.class);
        coordenada
                .asAtr().subtitle("subtitle maps").label("Maps").required();


        nomeCompleto = addField("nomeCompleto", STypeString.class);
        nomeMae = addField("nomeMae", STypeString.class);
        nomeMae.asAtr().enabled(false);
        nomePai = addField("nomePai", STypeString.class);
        telefone = addField("telefone", STypeTelefoneNacional.class);

        nomePai.asAtr().dependsOn(nomeCompleto);
        nomeCompleto.asAtr().label("Nome Completo").subtitle("teste subtitle").asAtrBootstrap().colPreference(4).colMd(4);
        nomeMae.asAtr().label("Nome Mãe").asAtrBootstrap().colPreference(4).colMd(4);
        nomePai.asAtr().label("Nome Pai").regexMask("[a-zA-Z\\u0410-\\u044f\\u0401\\u0451\\xc0-\\xff\\xb5 ]").asAtrBootstrap().colPreference(6).colMd(4);
        nomePai.asAtrIndex().indexed(Boolean.TRUE);
        telefone.asAtr().label("Telefone").asAtrBootstrap().colPreference(6);
        telefone.asAtrIndex().indexed(Boolean.TRUE);

        nomeCompleto.asAtrAnnotation().setAnnotated();
        nomePai.asAtrAnnotation().setAnnotated();
        documentos = this.addFieldListOfAttachment("documentos", "documento");
        documentos.asAtr().label("Documentos");
        documentos.withMaximumSizeOf(10);
        documentos.withMinimumSizeOf(0);
        documentos.asAtr().required(false);
        documentos.asAtr().dependsOn(nomeCompleto);


        naoTenhoFotoCachorro = this.addFieldBoolean("naoTenhoFotoCachorro");
        naoTenhoFotoCachorro.asAtr().label("Não tenho cachorro").subtitle("teste subtitle");
        naoTenhoFotoCachorro.asAtr().required(false);

        fotoDoCachorro = this.addFieldAttachment("fotoDoCachorro");
        fotoDoCachorro.withView(SViewAttachmentImage::new);
        fotoDoCachorro.asAtr().required(false);
        fotoDoCachorro.asAtr().label("Foto do cachorro").subtitle("teste subtitle");
        fotoDoCachorro.asAtr().dependsOn(naoTenhoFotoCachorro);
        fotoDoCachorro.asAtr().enabled(fci -> !fci.findNearest(naoTenhoFotoCachorro).map(SIBoolean::getValue).orElse(Boolean.FALSE));

        documentacaoComprobatoria = this.addFieldListOfAttachment("documentacaoComprobatoria", "documento");
        documentacaoComprobatoria.asAtr().label("Documentação comprobatória de que não possui cachorro");
        documentacaoComprobatoria.asAtr().dependsOn(naoTenhoFotoCachorro);
        documentacaoComprobatoria.getElementsType().asAtr().allowedFileTypes("pdf");
        documentacaoComprobatoria.asAtr().required(false);
        documentacaoComprobatoria.withMinimumSizeOf(0);
        documentacaoComprobatoria.asAtr().enabled(fci -> fci.findNearest(naoTenhoFotoCachorro).map(SIBoolean::getValue).orElse(Boolean.FALSE));

        brasileiro = this.addFieldBoolean("brasileiro");
        brasileiro.asAtr().label("Brasileiro").subtitle("teste subtitle");
        brasileiro.withView(SViewCheckBox::new);
        brasileiro.asAtr().enabled(true);

        listEnderecos = this.addFieldListOf("listEnderecos", STypeAddress.class);
        listEnderecos.asAtr().label("Endereços");
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
        richText2.withView(sViewByRichText2);


        richText3.withView(SViewSEIRichText
                .configProtocoloToIdSEIAction(SILinkSEI::getProtocolo)
                .configureModeloSEIAction(SIModeloSEI::getProtocoloModelo)
                .getConfiguration()
                .setDoubleClickDisabledForCssClasses("")
                .getView());

        paises = this.addFieldListOf("paises", STIdDescricao.class);
        paises
                .asAtr()
                .label("Países já visitados");

        paises
                .selection()
                .id(paises.getElementsType().id)
                .display(paises.getElementsType().descricao)
                .simpleProvider(builder -> Arrays.asList("Argentina", "Brasil", "Canadá", "Estado Unidos", "Finlândia", "Grã Bretanha")
                        .forEach(pais -> builder.add()
                                .set(paises.getElementsType().id, null)
                                .set(paises.getElementsType().descricao, pais)));

        paises
                .setAspect(IWicketComponentMapper.ASPECT_WICKET_MAPPER, () -> new ChosenMapper().setEmptyLabel("Selecione"));


        this.withView(new SViewByBlock(), block -> block.newBlock()
                .add(nomeMae)
                .add(nomePai)
                .add(telefone)
                .add(documentos)
                .add(coordenada)
                .add(richText)
                .add(paises));
    }




}