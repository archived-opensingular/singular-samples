package br.net.mirante.singular.showcase.validation;

import br.net.mirante.singular.form.mform.MPacote;
import br.net.mirante.singular.form.mform.MTipoComposto;
import br.net.mirante.singular.form.mform.PacoteBuilder;
import br.net.mirante.singular.form.mform.basic.ui.AtrBasic;
import br.net.mirante.singular.form.mform.core.AtrCore;
import br.net.mirante.singular.form.mform.core.MIInteger;
import br.net.mirante.singular.form.mform.core.MTipoInteger;

import java.util.Optional;

public class CaseValidationBetweenFieldsPackage extends MPacote {

    @Override
    protected void carregarDefinicoes(PacoteBuilder pb) {

        MTipoComposto<?> tipoMyForm = pb.createTipoComposto("testForm");

        MTipoInteger valorInicial = tipoMyForm.addCampoInteger("valorInicial");
        valorInicial.as(AtrBasic::new).label("Valor Inicial");
        valorInicial.as(AtrCore::new).obrigatorio(true);

        MTipoInteger valorFinal = tipoMyForm.addCampoInteger("valorFinal");
        valorFinal.as(AtrBasic::new).label("Valor Final");
        valorFinal.as(AtrCore::new).obrigatorio(true);

        valorFinal.addInstanceValidator(validatable -> {

            MIInteger mivFinal = validatable.getInstance();
            Optional<MIInteger> mivInicial = mivFinal.findNearest(valorInicial);

            if (mivInicial.isPresent()
                    && mivFinal.getInteger().compareTo(mivInicial.get().getInteger()) < 0) {
                validatable.error("O valor do campo final deve ser maior que o valor do campo inicial");
            }

        });

    }
}
