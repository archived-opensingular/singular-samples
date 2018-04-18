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
    public STypeString  nomeGato;
    public STypeString  nomeDog;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        nome = this.addFieldString("nome");
        nome.asAtr().label("Nome");
        nomeGato = this.addFieldString("nomeGato");
        nomeGato.asAtr().label("Nome Gato");
        nomeDog = this.addFieldString("nomeDog");
        nomeDog.asAtr().label("Nome Dog");

        nome.asAtrAnnotation().setAnnotated();

        sobrenome = this.addFieldString("sobrenome");
        sobrenome.asAtr().label("Sobrenome");
        sobrenome.asAtrAnnotation().setAnnotated();
        nomeMae = this.addFieldString("nomeMae");
        nomeMae.asAtr().label("Nome Mãe");

        nomeMae.asAtrAnnotation().setAnnotated();

    }
}
