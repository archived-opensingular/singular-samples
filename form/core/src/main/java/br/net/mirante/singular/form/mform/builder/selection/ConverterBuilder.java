package br.net.mirante.singular.form.mform.builder.selection;

import br.net.mirante.singular.form.mform.SInstance;
import br.net.mirante.singular.form.mform.SType;
import br.net.mirante.singular.form.mform.converter.AutoSICompositeConverter;
import br.net.mirante.singular.form.mform.converter.EnumSInstanceConverter;
import br.net.mirante.singular.form.mform.converter.SInstanceConverter;
import br.net.mirante.singular.form.mform.converter.SimpleSInstanceConverter;

import java.io.Serializable;


public class ConverterBuilder<T extends Serializable, I extends SInstance> extends AbstractBuilder {

    public ConverterBuilder(SType type) {
        super(type);
    }

    public <X extends Enum<X>> ProviderBuilder<T, I> enumConverter(Class<X> enumClass) {
        type.asAtrProvider().asAtrProvider().converter(new EnumSInstanceConverter<>(enumClass));
        return next();
    }


    public ProviderBuilder<T, I> converter(SInstanceConverter<T, I> converter) {
        type.asAtrProvider().asAtrProvider().converter(converter);
        return next();
    }

    public ProviderBuilder<T, I> autoConverter(Class resultClass) {
        type.asAtrProvider().asAtrProvider().converter(AutoSICompositeConverter.of(resultClass));
        return next();
    }

    public ProviderBuilder<T, I> simpleConverter() {
        type.asAtrProvider().asAtrProvider().converter(new SimpleSInstanceConverter<>());
        return next();
    }

    private ProviderBuilder<T, I> next() {
        return new ProviderBuilder<>(type);
    }

}