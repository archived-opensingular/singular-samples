package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(name = "produto", spackage = SPackageStudioPersistenceForm.class)
public class STypeProduto extends STypeComposite<SIComposite> {

    public STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addField("nome", STypeString.class);
        selection().id(nome).display(nome).simpleProvider(ss -> {
            ss.add().set(nome, "Celular")
              .add().set(nome, "Camera Fotografica")
              .add().set(nome, "Notebook");
        });
    }
}
