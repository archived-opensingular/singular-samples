package org.sample.form;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.lib.commons.util.Loggable;

@SInfoType(name = "ListaExemplo", spackage = RequirementsamplePackage.class)
public class STypeListaExemplo extends STypeComposite<SIComposite> implements Loggable {

    public STypeString nome;
    public STypeString  sobrenome;
    public STypeString  nomeMae;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        nome.asAtr().label("Nome");

        nome.asAtrAnnotation().setAnnotated();

        sobrenome = this.addFieldString("sobrenome");
        sobrenome.asAtr().label("Sobrenome").enabled(false);
        sobrenome.asAtrAnnotation().setAnnotated();
        nomeMae = this.addFieldString("nomeMae");
        nomeMae.asAtr().label("Nome Mãe");

        nomeMae.asAtrAnnotation().setAnnotated();

    }
}
