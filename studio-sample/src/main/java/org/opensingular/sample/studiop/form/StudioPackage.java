package org.opensingular.sample.studiop.form;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SInfoPackage;
import org.opensingular.form.SPackage;

import javax.annotation.Nonnull;

@SInfoPackage(name = "org.opensingular.sample.studio")
public class StudioPackage extends SPackage {

    @Override
    protected void onLoadPackage(@Nonnull PackageBuilder pb) {
        super.onLoadPackage(pb);
        pb.createType(STPresente.class);
        pb.createType(STFerramenta.class);
    }
}