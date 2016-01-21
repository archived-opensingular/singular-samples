package br.net.mirante.singular.form.mform.core;

import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;

import br.net.mirante.singular.form.mform.MInfoTipo;
import br.net.mirante.singular.form.mform.MTipoSimples;
import br.net.mirante.singular.form.mform.basic.view.MTextAreaView;
import br.net.mirante.singular.form.mform.options.MOptionsProvider;

@MInfoTipo(nome = "String", pacote = MPacoteCore.class)
public class MTipoString extends MTipoSimples<MIString, String> {

    public MTipoString() {
        super(MIString.class, String.class);
    }

    protected MTipoString(Class<? extends MIString> classeInstancia) {
        super(classeInstancia, String.class);
    }

    public boolean getValorAtributoTrim() {
        return getValorAtributo(MPacoteCore.ATR_TRIM);
    }

    public boolean getValorAtributoEmptyToNull() {
        return getValorAtributo(MPacoteCore.ATR_EMPTY_TO_NULL);
    }

    public MTipoString withValorAtributoTrim(boolean valor) {
        return (MTipoString) with(MPacoteCore.ATR_TRIM, valor);
    }

    public <T extends Enum<T>> MTipoString withSelectionOf(Class<T> enumType) {
        T[] ops = enumType.getEnumConstants();
        String[] nomes = new String[ops.length];
        for (int i = 0; i < ops.length; i++) {
            nomes[i] = ops[i].toString();
        }
        return (MTipoString) super.withSelectionOf(nomes);
    }

    /**
     * Configura o tipo para utilizar a view {@link MTextAreaView} e invoca o initializer 
     */
    @SafeVarargs
    public final MTipoString withTextAreaView(Consumer<MTextAreaView>...initializers) {
        withView(new MTextAreaView(), initializers);
        return this;
    }
    
    @Override
    public String converter(Object valor) {
        String s = super.converter(valor);
        if (s != null) {
            if (getValorAtributoEmptyToNull()) {
                if (getValorAtributoTrim()) {
                    s = StringUtils.trimToNull(s);
                } else if (StringUtils.isEmpty(s)) {
                    s = null;
                }
            } else if (getValorAtributoTrim()) {
                s = StringUtils.trim(s);
            }
        }
        return s;
    }

    @Override
    public String converterNaoNativoNaoString(Object valor) {
        return valor.toString();
    }

}
