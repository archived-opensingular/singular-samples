package br.net.mirante.singular.form.mform;

import java.util.Collection;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import br.net.mirante.singular.form.mform.core.AtrFormula;
import br.net.mirante.singular.form.mform.core.MPacoteCore;

@MFormTipo(nome = "MTipoSimples", pacote = MPacoteCore.class)
public class MTipoSimples<I extends MISimples<TIPO_NATIVO>, TIPO_NATIVO> extends MTipo<I> {

    private final Class<TIPO_NATIVO> classeTipoNativo;

    private transient Converter converter;

    private MProviderOpcoes providerOpcoes;

    public MTipoSimples() {
        this.classeTipoNativo = null;
    }

    protected MTipoSimples(Class<? extends I> classeInstancia, Class<TIPO_NATIVO> classeTipoNativo) {
        super(classeInstancia);
        this.classeTipoNativo = classeTipoNativo;
    }

    public MProviderOpcoes getProviderOpcoes() {
        return providerOpcoes;
    }

    public MProviderOpcoes selectionOf(Collection<TIPO_NATIVO> opcoes) {
        providerOpcoes = new MProviderOpcoesFixoSimples(this, opcoes);
        return providerOpcoes;
    }

    public MProviderOpcoes selectionOf(TIPO_NATIVO... opcoes) {
        providerOpcoes = new MProviderOpcoesFixoSimples(this, opcoes);
        return providerOpcoes;
    }

    public MTipoSimples<I, TIPO_NATIVO> withSelectionOf(TIPO_NATIVO... opcoes) {
        providerOpcoes = new MProviderOpcoesFixoSimples(this, opcoes);
        return this;
    }
    
    public AtrFormula asFormula() {
        return MTranslatorParaAtributo.of(this, new AtrFormula());
    }

    public TIPO_NATIVO converter(Object valor) {
        if (valor == null) {
            return null;
        } else if (classeTipoNativo.isInstance(valor)) {
            return classeTipoNativo.cast(valor);
        } else if (valor instanceof String) {
            return fromString((String) valor);
        }
        return converterNaoNativoNaoString(valor);
    }

    protected TIPO_NATIVO converterNaoNativoNaoString(Object valor) {
        return converterUsandoApache(valor);
    }

    protected String toStringPersistencia(TIPO_NATIVO valorOriginal) {
        if (valorOriginal == null) {
            return null;
        }
        return valorOriginal.toString();
    }

    public String toStringDisplay(TIPO_NATIVO valor) {
        return toStringPersistencia(valor);
    }

    public TIPO_NATIVO fromString(String valor) {
        throw new RuntimeException("Não implementado");
    }

    @Override
    public <T extends Object> T converter(Object valor, Class<T> classeDestino) {
        if (valor == null) {
            return null;
        } else if (classeDestino.isAssignableFrom(classeTipoNativo)) {
            return classeDestino.cast(converter(valor));
        } else if (classeDestino.isInstance(valor)) {
            return classeDestino.cast(valor);
        } else if (classeDestino.isAssignableFrom(String.class)) {
            if (classeTipoNativo.isInstance(valor)) {
                return classeDestino.cast(toStringPersistencia(classeTipoNativo.cast(valor)));
            }
            return classeDestino.cast(valor.toString());
        } else {
            Converter converter = ConvertUtils.lookup(valor.getClass(), classeDestino);
            if (converter != null) {
                return classeDestino.cast(converter.convert(classeDestino, valor));
            }
        }

        throw createErroConversao(valor, classeDestino);
    }

    protected final TIPO_NATIVO converterUsandoApache(Object valor) {
        if (converter == null) {
            converter = ConvertUtils.lookup(classeTipoNativo);
            if (converter == null) {
                throw createErroConversao(valor);
            }
        }
        return classeTipoNativo.cast(converter.convert(classeTipoNativo, valor));
    }

    public final Class<TIPO_NATIVO> getClasseTipoNativo() {
        return classeTipoNativo;
    }

    protected final RuntimeException createErroConversao(Object valor) {
        return createErroConversao(valor, null, null, null);
    }

    protected final RuntimeException createErroConversao(Object valor, Class<?> tipoDestino) {
        return createErroConversao(valor, tipoDestino, null, null);
    }

    protected final RuntimeException createErroConversao(Object valor, Class<?> tipoDestino, String complemento, Exception e) {
        String msg = "O tipo '" + getClass().getName() + "' não consegue converter o valor '" + valor + "' do tipo "
                + valor.getClass().getName();
        if (tipoDestino != null) {
            msg += " para o tipo '" + tipoDestino.getName() + "'";
        }
        if (complemento != null) {
            msg += complemento;
        }
        if (e != null) {
            return new RuntimeException(msg, e);
        }
        return new RuntimeException(msg);
    }
}