package br.net.mirante.singular.showcase.component.layout;

import br.net.mirante.singular.form.mform.MPacote;
import br.net.mirante.singular.form.mform.MTipoComposto;
import br.net.mirante.singular.form.mform.PacoteBuilder;
import br.net.mirante.singular.form.mform.basic.ui.AtrBasic;
import br.net.mirante.singular.form.mform.basic.ui.AtrBootstrap;

public class CaseSimpleGridPackage extends MPacote {

    @Override
    protected void carregarDefinicoes(PacoteBuilder pb) {
        final MTipoComposto<?> testForm = pb.createTipoComposto("testForm");

        testForm.addCampoString("nome")
                .as(AtrBasic.class).label("Nome")
                .as(AtrBootstrap::new).colPreference(6);
        testForm.addCampoInteger("idade")
                .as(AtrBasic.class).label("Idade")
                .as(AtrBootstrap::new).colPreference(2);
        testForm.addCampoEmail("email")
                .as(AtrBasic.class).label("E-mail")
                .as(AtrBootstrap::new).colPreference(8);

    }
}
