package org.opensingular.singular.form.showcase.component.form.core.search;

import org.opensingular.form.SInstance;

import java.util.ArrayList;
import java.util.List;

public class ProcessoRepository  {

    private static final List<Processo> PROCESSOS;

    static {
        PROCESSOS = new ArrayList<>();
        Processo mirante = new Processo(0L, "Mirante Tecnologia");
        Processo anvisa = new Processo(1L, "Anvisa");
        Processo montreal = new Processo(2L, "Montreal");

        Processo pei = new Processo(11L, "petição-importação");
        pei.addChild(new Processo(112L, "siscomex"));
        anvisa.addChild(new Processo(12L, "unigru"));
        anvisa.addChild(pei);

        Processo vendas = new Processo(21L, "vendas");
        vendas.addChild(new Processo(221L, "comissões"));
        montreal.addChild(vendas);
        Processo hotelaria = new Processo(22L, "hotelaria");
        hotelaria.addChild(new Processo(223L, "remanejamento"));
        montreal.addChild(hotelaria);

        mirante.addChild(anvisa);
        mirante.addChild(montreal);

        Processo singular = new Processo(3L, "Singular");
        Processo antaq = new Processo(31L, "antaq");
        antaq.addChild(new Processo(312L, "outorga"));
        antaq.addChild(new Processo(313L, "travessia"));

        Processo anvisaSingular = new Processo(4L, "anvisa-singular");
        anvisaSingular.addChild(new Processo(41L, "ggtox"));
        anvisaSingular.addChild(new Processo(42L, "ggmed"));
        anvisaSingular.addChild(new Processo(43L, "ggtin"));

        singular.addChild(antaq);
        singular.addChild(anvisaSingular);

        PROCESSOS.add(mirante);
        PROCESSOS.add(singular);
    }

    public List<Processo> get(SInstance filter) {
        return PROCESSOS;
    }
}
