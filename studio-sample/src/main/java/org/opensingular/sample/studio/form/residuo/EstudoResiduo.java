package org.opensingular.sample.studio.form.residuo;


import org.opensingular.form.*;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByMasterDetail;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@SInfoType(name = "EstudoResiduo", spackage = ResiduoPackage.class)
public class EstudoResiduo extends STypeComposite<SIComposite> {

    @Inject
    @Named("modalidadeDeEmpregoRepository")
    private SpringFormPersistenceInMemory<ModalidadeDeEmprego, SIComposite> modalidadeDeEmpregoRepository;

    @Inject
    @Named(" culturaRepository")
    private SpringFormPersistenceInMemory<Cultura, SIComposite> culturaRepository;

    @Inject
    @Named(" normaRepository")
    private SpringFormPersistenceInMemory<Norma, SIComposite> normaRepository;

    @Inject
    @Named(" tipoDoseRepository")
    private SpringFormPersistenceInMemory<TipoDose, SIComposite> tipoDoseRepository;

    public Cultura cultura;
    public ModalidadeDeEmprego modalidadeDeEmprego;
    public TipoDose tipoDose;
    public Norma norma;
    public STypeBoolean parteComestivel;
    public STypeBoolean adjuvante;
    public STypeInteger intervaloSeguranca;
    public STypeInteger numeroAplicacoes;
    public STypeString observacao;
    public STypeList<Ensaio, SIComposite> ensaios;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {

        cultura = addField("cultura", Cultura.class);
        modalidadeDeEmprego = addField("modalidadeDeEmprego", ModalidadeDeEmprego.class);
        tipoDose = addField("tipoDose", TipoDose.class);
        norma = addField("norma", Norma.class);
        parteComestivel = addField("parteComestivel", STypeBoolean.class);
        adjuvante = addField("adjuvante", STypeBoolean.class);
        intervaloSeguranca = addField("intervaloSeguranca", STypeInteger.class);
        numeroAplicacoes = addField("numeroAplicacoes", STypeInteger.class);
        observacao = addField("observacao", STypeString.class);
        ensaios = addFieldListOf("ensaios", Ensaio.class);

        cultura.selection().id(cultura.nome).display(cultura.nome).simpleProvider(s -> {
            List<SIComposite> culturas = culturaRepository.loadAll();
            for (SIComposite c : culturas) {
                s.add().set(cultura.nome, c.getValue("nome"));
            }
        });
        cultura.asAtr().label("Cultura").asAtrBootstrap().colPreference(6);

        modalidadeDeEmprego.selection().id(modalidadeDeEmprego.nome).display(modalidadeDeEmprego.nome).simpleProvider(s -> {
            List<SIComposite> culturas = modalidadeDeEmpregoRepository.loadAll();
            for (SIComposite c : culturas) {
                s.add().set(modalidadeDeEmprego.nome, c.getValue("nome"));
            }
        });
        modalidadeDeEmprego.asAtr().label("Modalidade de emprego").asAtrBootstrap().colPreference(6);

        tipoDose.selection().id(tipoDose.nome).display(tipoDose.nome).simpleProvider(s -> {
            List<SIComposite> culturas = tipoDoseRepository.loadAll();
            for (SIComposite c : culturas) {
                s.add().set(tipoDose.nome, c.getValue("nome"));
            }
        });
        tipoDose.asAtr().label("Tipo de dose").asAtrBootstrap().colPreference(6);

        norma.selection().id(norma.nome).display(norma.nome).simpleProvider(s -> {
            List<SIComposite> culturas = normaRepository.loadAll();
            for (SIComposite c : culturas) {
                s.add().set(norma.nome, c.getValue(norma.nome));
            }
        });
        norma.asAtr().label("Norma").asAtrBootstrap().colPreference(6);

        parteComestivel.asAtr().label("Parte comestivel").asAtrBootstrap().colPreference(3);
        adjuvante.asAtr().label("Adjuvante").asAtrBootstrap().colPreference(3);

        intervaloSeguranca.asAtr().label("Intervalo de segurança").asAtrBootstrap().newRow().colPreference(3);
        numeroAplicacoes.asAtr().label("Intervalo de segurança").asAtrBootstrap().colPreference(3);

        observacao.asAtr().label("Observação").asAtrBootstrap().newRow().colPreference(12);
        observacao.withTextAreaView();

        Ensaio ensaio = ensaios.getElementsType();
        ensaios.asAtr().label("Ensaio").asAtrBootstrap().colPreference(12);
        ensaios.withView(new SViewListByMasterDetail(), view -> view
                .col(ensaio.codigo, "Código")
                .col(ensaio.cidade, "Cidade"));
    }
}
