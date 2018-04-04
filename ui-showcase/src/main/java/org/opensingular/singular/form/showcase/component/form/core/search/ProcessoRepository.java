package org.opensingular.singular.form.showcase.component.form.core.search;

import org.opensingular.form.SInstance;

import java.util.ArrayList;
import java.util.List;

public class ProcessoRepository {

    private static final List<Processo> PROCESSOS;

    static {
        PROCESSOS = new ArrayList<>();
        Processo mirante = new Processo(1L, "Mirante Tecnologia");
        Processo anvisa = new Processo(2L, "Anvisa");
        Processo montreal = new Processo(3L, "Montreal");

        Processo pei = new Processo(4L, "petição-importação");
        pei.addFilho(new Processo(5L, "siscomex"));
        anvisa.addFilho(new Processo(6L, "unigru"));
        anvisa.addFilho(pei);

        Processo vendas = new Processo(7L, "vendas");
        vendas.addFilho(new Processo(8L, "comissões"));
        montreal.addFilho(vendas);

        Processo hotelaria = new Processo(9L, "hotelaria");
        hotelaria.addFilho(new Processo(10L, "remanejamento"));
        montreal.addFilho(hotelaria);

        mirante.addFilho(anvisa);
        mirante.addFilho(montreal);

        Processo singular = new Processo(11L, "Singular");

        Processo antaq = new Processo(12L, "antaq");
        antaq.addFilho(new Processo(13L, "outorga"));
        antaq.addFilho(new Processo(14L, "travessia"));

        Processo anvisaSingular = new Processo(15L, "anvisa-singular");
        anvisaSingular.addFilho(new Processo(16L, "ggtox"));
        anvisaSingular.addFilho(new Processo(17L, "ggmed"));
        anvisaSingular.addFilho(new Processo(18L, "ggtin"));

        singular.addFilho(antaq);
        singular.addFilho(anvisaSingular);

        PROCESSOS.add(mirante);
        PROCESSOS.add(singular);
    }

    public List<Processo> get(SInstance filter) {
        return PROCESSOS;
    }
}
