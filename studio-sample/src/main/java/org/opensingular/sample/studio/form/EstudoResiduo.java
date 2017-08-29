package org.opensingular.sample.studio.form;


import org.opensingular.form.*;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByMasterDetail;
import org.opensingular.sample.studio.repository.ModalidadeEmpregoRepository;
import org.opensingular.sample.studio.repository.NormaRepository;
import org.opensingular.sample.studio.repository.TipoDoseRepository;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.List;

@SInfoType(name = "EstudoResiduo", spackage = ResiduoPackage.class)
public class EstudoResiduo extends STypeComposite<SIComposite> {

    public CulturaRef cultura;
    public ModalidadeEmpregoRef modalidadeDeEmprego;
    public TipoDoseRef tipoDose;
    public NormaRef norma;
    public STypeBoolean parteComestivel;
    public STypeBoolean adjuvante;
    public STypeInteger intervaloSeguranca;
    public STypeInteger numeroAplicacoes;
    public STypeString observacao;
    public STypeList<Ensaio, SIComposite> ensaios;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {

        cultura = addField("cultura", CulturaRef.class);
        modalidadeDeEmprego = addField("modalidadeDeEmprego", ModalidadeEmpregoRef.class);
        tipoDose = addField("tipoDose", TipoDoseRef.class);
        norma = addField("norma", NormaRef.class);
        parteComestivel = addField("parteComestivel", STypeBoolean.class);
        adjuvante = addField("adjuvante", STypeBoolean.class);
        intervaloSeguranca = addField("intervaloSeguranca", STypeInteger.class);
        numeroAplicacoes = addField("numeroAplicacoes", STypeInteger.class);
        observacao = addField("observacao", STypeString.class);
        ensaios = addFieldListOf("ensaios", Ensaio.class);

        cultura.asAtr().required().label("Cultura").asAtrBootstrap().colPreference(6);
        modalidadeDeEmprego.asAtr().required().label("Modalidade de emprego").asAtrBootstrap().colPreference(6);
        tipoDose.asAtr().required().label("Tipo de dose").asAtrBootstrap().colPreference(6);
        norma.asAtr().required().label("Norma").asAtrBootstrap().colPreference(6);

        parteComestivel.asAtr().label("Parte comestivel").asAtrBootstrap().colPreference(3);
        adjuvante.asAtr().label("Adjuvante").asAtrBootstrap().colPreference(3);

        intervaloSeguranca.asAtr().required().label("Intervalo de segurança pretendido (em dias)").asAtrBootstrap().newRow().colPreference(3);
        numeroAplicacoes.asAtr().required().label("Nº de aplicações").asAtrBootstrap().colPreference(3);

        observacao.asAtr().label("Observação").asAtrBootstrap().newRow().colPreference(12);
        observacao.withTextAreaView();

        Ensaio ensaio = ensaios.getElementsType();
        ensaios.asAtr().label("Ensaio").asAtrBootstrap().colPreference(12);
        ensaios.withView(new SViewListByMasterDetail(), view -> view
                .col(ensaio.codigo, "Código")
                .col(ensaio.cidade, "Cidade"));
    }
}
