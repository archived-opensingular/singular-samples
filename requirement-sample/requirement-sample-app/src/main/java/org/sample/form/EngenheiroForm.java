package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(name = "Engenheiro", spackage = RequirementsamplePackage.class)
public class EngenheiroForm extends STypeComposite<SIComposite> {

    private STypeString nome;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = addField("nome", STypeString.class);
    }
}