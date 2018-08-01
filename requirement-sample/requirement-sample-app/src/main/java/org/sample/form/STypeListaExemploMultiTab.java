package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewByBlock;

import javax.annotation.Nonnull;

@SInfoType(name = "ListaExemploMultiTab", spackage = RequirementsamplePackage.class)
public class STypeListaExemploMultiTab extends STypeComposite<SIComposite> {

    public STypeListaExemplo exemplo2;
    public STypeListaMultiTab exemplo3;

    public STypeString nomeGato2;
    public STypeString nomeGato3;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtrIndex().indexed(Boolean.TRUE);
        exemplo2 = addField("exemplo2", STypeListaExemplo.class);
        exemplo2.asAtrIndex().indexed(Boolean.TRUE);

        exemplo3 = addField("exemplo3", STypeListaMultiTab.class);
        exemplo3.asAtrIndex().indexed(Boolean.TRUE);


        nomeGato2 = this.addFieldString("nomeGato2");
        nomeGato2.asAtr().label("Nome Gato");
        nomeGato3 = this.addFieldString("nomeGato3");
        nomeGato3.asAtr().label("Nome Gato3").subtitle("teste");


        this.withView(new SViewByBlock(), block -> block.newBlock()
                .add(exemplo2)
                .add(nomeGato2).add(nomeGato3).add(exemplo3));

        this.asAtr().label("EXEMPLO");
    }
}
