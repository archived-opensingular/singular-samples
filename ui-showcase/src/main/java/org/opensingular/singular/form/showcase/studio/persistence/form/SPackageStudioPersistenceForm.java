package org.opensingular.singular.form.showcase.studio.persistence.form;


import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SInfoPackage;
import org.opensingular.form.SPackage;

import javax.annotation.Nonnull;

@SInfoPackage(name = "org.opensingular.studio.persistence")
public class SPackageStudioPersistenceForm extends SPackage {
    @Override
    protected void onLoadPackage(@Nonnull PackageBuilder pb) {
        super.onLoadPackage(pb);
        pb.createType(STypeUF.class);
        pb.createType(STypeEndereco.class);
        pb.createType(STypePessoa.class);
    }
}
