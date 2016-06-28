/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.net.mirante.singular.exemplos.emec.credenciamentoescolagoverno.form;

import br.net.mirante.singular.form.SIComposite;
import br.net.mirante.singular.form.SInfoType;
import br.net.mirante.singular.form.STypeComposite;
import br.net.mirante.singular.form.STypeList;
import br.net.mirante.singular.form.TypeBuilder;
import br.net.mirante.singular.form.type.core.STypeBoolean;
import br.net.mirante.singular.form.type.country.brazil.STypeCPF;
import br.net.mirante.singular.form.type.country.brazil.STypeTelefoneNacional;
import br.net.mirante.singular.form.type.util.STypeEMail;
import br.net.mirante.singular.form.util.transformer.Value;
import br.net.mirante.singular.form.view.SViewByBlock;
import br.net.mirante.singular.form.view.SViewListByForm;
import br.net.mirante.singular.form.view.SViewListByTable;

@SInfoType(spackage = SPackageCredenciamentoEscolaGoverno.class)
public class STypeCorpoDirigente extends STypeComposite<SIComposite> {

    @Override
    protected void onLoadType(TypeBuilder tb) {
        super.onLoadType(tb);

        final STypeList<STypeComposite<SIComposite>, SIComposite> corpoDirigenteMembrosCPA = this.addFieldListOfComposite("corpoDirigenteMembrosCPA", "membro");

        corpoDirigenteMembrosCPA
                .withMiniumSizeOf(3);

        corpoDirigenteMembrosCPA
                .withView(SViewListByForm::new)
                .asAtr()
                .label("Corpo Dirigente e/ou Membro CPA")
                .itemLabel("Membro")
                .required();

        final STypeComposite<SIComposite> membro = corpoDirigenteMembrosCPA.getElementsType();

        membro
                .addFieldString("nome", true)
                .asAtr()
                .label("Nome")
                .asAtrBootstrap()
                .colPreference(6);

        membro.
                addField("cpf", STypeCPF.class)
                .asAtr()
                .required()
                .asAtrBootstrap()
                .colPreference(3);

        membro
                .addField("sexo", STypeSexo.class)
                .asAtr()
                .required()
                .asAtrBootstrap()
                .colPreference(3);

        membro
                .addFieldInteger("numeroRG", true)
                .asAtr()
                .label("RG")
                .asAtrBootstrap()
                .colPreference(3);

        membro
                .addFieldString("orgaoExpedidorRG", true)
                .asAtr()
                .label("Órgão Expedidor")
                .asAtrBootstrap()
                .colPreference(3);

        membro
                .addField("ufRG", STypeEstado.class)
                .asAtr()
                .required()
                .asAtrBootstrap()
                .colPreference(3);

        membro
                .addField("email", STypeEMail.class)
                .asAtr()
                .required()
                .asAtrBootstrap()
                .newRow();

        membro
                .addField("fax", STypeTelefoneNacional.class)
                .asAtr()
                .label("Fax")
                .asAtrBootstrap()
                .colPreference(3);

        membro
                .addField("telefone", STypeTelefoneNacional.class)
                .asAtr()
                .label("Telefones")
                .required()
                .asAtrBootstrap()
                .colPreference(3);

        membro
                .addFieldString("cargo", true)
                .asAtr()
                .label("Cargo")
                .asAtrBootstrap()
                .colPreference(6);

        final STypeBoolean membroCPA      = membro.addFieldBoolean("membroCPA", true);
        final STypeBoolean coordenadorCPA = membro.addFieldBoolean("coordenadorCPA", true);
        final STypeBoolean dirigente      = membro.addFieldBoolean("dirigente", true);

        membroCPA
                .asAtr()
                .label("Membro CPA")
                .asAtrBootstrap()
                .colPreference(2)
                .newRow();

        coordenadorCPA
                .asAtr()
                .label("Coordenador CPA")
                .asAtrBootstrap()
                .colPreference(2);

        dirigente
                .asAtr()
                .label("Dirigente")
                .asAtrBootstrap()
                .colPreference(2);

        corpoDirigenteMembrosCPA.addInstanceValidator(validatable -> {
            if (validatable.getInstance().stream().filter(membro_ -> Value.of(membro_, membroCPA).booleanValue()).count() >= 3
                    && validatable.getInstance().stream().filter(membro_ -> Value.of(membro_, coordenadorCPA).booleanValue()).count() >= 1) {
                validatable.error("É necessário ter no mínimo três membros e um coordenador do CPA definidos");
            }
        });

        this.withView(new SViewByBlock(), v -> {
            v.newBlock().add(corpoDirigenteMembrosCPA);
        });
    }

}