package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(name = "UF", spackage = SPackageStudioPersistenceForm.class)
public class STypeUF extends STypeComposite<SIComposite> {

    public STypeString sigla;
    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        createType();
        this.selection().id(sigla).display(nome).simpleProvider(b -> b
                .add()
                .set(sigla, "DF")
                .set(nome, "Distrito Federal")
                .add()
                .set(sigla, "AM")
                .set(nome, "Amazonas")
        );
    }

    private void createType() {
        sigla = addField("sigla", STypeString.class);
        nome = addField("nome", STypeString.class);
    }

}
