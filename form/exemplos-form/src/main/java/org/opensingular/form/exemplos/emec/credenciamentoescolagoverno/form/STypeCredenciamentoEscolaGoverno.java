/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.opensingular.form.exemplos.emec.credenciamentoescolagoverno.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewTab;

@SInfoType(spackage = SPackageCredenciamentoEscolaGoverno.class)
public class STypeCredenciamentoEscolaGoverno extends STypeComposite<SIComposite>{

    @Override
    protected void onLoadType(TypeBuilder tb) {
        super.onLoadType(tb);
        
        this.asAtr().label("Credenciamento de Escola de Governo");
        
        
        SViewTab tabbed = this.setView(SViewTab::new);
        tabbed.addTab(addField("mantenedora", STypeMantenedora.class), "Mantenedora");
//        tabbed.addTab("mantida", "Mantida");
        tabbed.addTab(addField("corpoDirigenteMembrosCPA", STypeCorpoDirigente.class), "Corpo Dirigente/CPA");
        tabbed.addTab(addField("PDI", STypePDI.class), "Informações do PDI");
        tabbed.addTab(addField("projetoPedagogico", STypePDIProjetoPedagogico.class), "Projeto Pedagógico");
        tabbed.addTab(addField("documentos", STypePDIDocumentos.class), "Documentos");
        tabbed.addTab(addRegimentoEstatuto(), "Regimento/Estatuto");
        // configuração do tamanho da coluna de navegação das abas
        tabbed.navColPreference(2).navColMd(3).navColSm(3).navColXs(4);
        
    }
    
    private STypeComposite<SIComposite> addRegimentoEstatuto() {
        final STypeComposite<SIComposite> regimentoEstatuto = this.addFieldComposite("regimentoEstatuto");
        //TODO - richtext
        regimentoEstatuto.addFieldString("textoRegimento", true)
            .withTextAreaView()
            .asAtrBootstrap().colPreference(12);
        
        regimentoEstatuto.setView(SViewByBlock::new).newBlock("25 Texto do Regimento").add("textoRegimento");
        return regimentoEstatuto;
    }
}
