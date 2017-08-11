package org.opensingular.formsamples.crud.types.toxicologia;

import org.opensingular.form.*;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.util.transformer.Value;
import org.opensingular.form.view.SViewListByMasterDetail;

import javax.annotation.Nonnull;

import static org.opensingular.formsamples.crud.types.toxicologia.ToxicologiaPackage.OBRIGATORIO;

@SInfoType(spackage = ToxicologiaPackage.class, name = "ensaio")
public class STEnsaio extends STypeComposite<SIComposite> {
    public STypeString codigoEnsaio;
    public STypeComposite<SIComposite> estado;
    public STypeString nomeEstado;
    public STypeString siglaEstado;
    public STypeComposite<SIComposite> cidade;
    public STypeInteger idCidade;
    public STypeString nomeCidade;
    public STypeString ufCidade;
    public STypeList<STAmostra, SIComposite> amostras;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        codigoEnsaio = this.addField("codigoEnsaio", STypeString.class);
        estado = this.addFieldComposite("estado");
        nomeEstado = estado.addFieldString("nomeEstado");
        siglaEstado = estado.addFieldString("siglaEstado");
        cidade = this.addFieldComposite("cidade");
        idCidade = cidade.addFieldInteger("idCidade");
        nomeCidade = cidade.addFieldString("nomeCidade");
        ufCidade = cidade.addFieldString("ufCidade");

        amostras = this.addFieldListOf("amostras", STAmostra.class);
        STAmostra amostra = amostras.getElementsType();

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

        estado.selection()
                .id(siglaEstado)
                .display("${nomeEstado} - ${siglaEstado}")
                .simpleProvider(builder -> {
                    builder.add()
                            .set(nomeEstado, "Distrito Federal")
                            .set(siglaEstado, "DF");
                });

        cidade
                .asAtr()
                .required(inst -> OBRIGATORIO)
                .label("Cidade")
                .enabled(inst -> Value.notNull(inst, siglaEstado))
                .dependsOn(estado)
                .asAtrBootstrap()
                .colPreference(3);

        cidade.selection()
                .id(idCidade)
                .display(nomeCidade)
                .simpleProvider(builder -> {
                    builder.add()
                            .set(idCidade, "1")
                            .set(nomeCidade, "Brasilia");
                });

        amostras.withView(new SViewListByMasterDetail()
                .col(amostra.id, "Id")
                .col(amostra.dose, "Dose")
                .col(amostra.ativos.nomeComumPortugues, "Ingrediente Ativo")
                .col(amostra.residuo, "Resíduo")
                .col(amostra.dat, "DAT")
        ).asAtr().label("Amostras");
    }
}
