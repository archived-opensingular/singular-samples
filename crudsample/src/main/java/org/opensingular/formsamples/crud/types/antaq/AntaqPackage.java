package org.opensingular.formsamples.crud.types.antaq;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SInfoPackage;
import org.opensingular.form.SPackage;

import javax.annotation.Nonnull;

@SInfoPackage(name = "br.org.anvisa.toxicologia")
public class AntaqPackage extends SPackage {

    public static Boolean OBRIGATORIO = Boolean.TRUE;

    @Override
    protected void onLoadPackage(@Nonnull PackageBuilder pb) {
        super.onLoadPackage(pb);
        pb.createType(Resolucao912Form.class);
    }
}
