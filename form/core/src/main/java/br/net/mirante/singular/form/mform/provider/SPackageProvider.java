package br.net.mirante.singular.form.mform.provider;

import br.net.mirante.singular.form.mform.AtrRef;
import br.net.mirante.singular.form.mform.PackageBuilder;
import br.net.mirante.singular.form.mform.SPackage;
import br.net.mirante.singular.form.mform.SType;
import br.net.mirante.singular.form.mform.converter.SIConverter;
import br.net.mirante.singular.form.mform.converter.SInstanceConverter;
import br.net.mirante.singular.form.mform.converter.STypeConverter;

public class SPackageProvider extends SPackage {

    private static final String NAME = "singular.form.provider";

    public static final AtrRef<STypeProvider, SIProvider, FilteredPagedProvider> PROVIDER  = new AtrRef<>(SPackageProvider.class, "provider", STypeProvider.class, SIProvider.class, FilteredPagedProvider.class);
    public static final AtrRef<STypeConverter, SIConverter, SInstanceConverter>  CONVERTER = new AtrRef<>(SPackageProvider.class, "converter", STypeConverter.class, SIConverter.class, SInstanceConverter.class);

    public SPackageProvider() {
        super(NAME);
    }

    @Override
    protected void carregarDefinicoes(PackageBuilder pb) {
        pb.createType(STypeProvider.class);
        pb.createType(STypeConverter.class);
        pb.createAttributeIntoType(SType.class, PROVIDER);
        pb.createAttributeIntoType(SType.class, CONVERTER);
    }

}