package org.opensingular.requirementsamplemodule.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewTab;

@SInfoType(spackage = RequirementsamplePackage.class, name = "Requirementsample")
public class RequirementsampleForm extends STypeComposite<SIComposite> {

    public STypeDadosPessoais dadosPessoais;

    @Override
    protected void onLoadType(TypeBuilder tb) {
        dadosPessoais = addField("dadosPessoais", STypeDadosPessoais.class);
        SViewTab sViewTab = new SViewTab();
        sViewTab.addTab(dadosPessoais);
        this.withView(sViewTab);
        this.asAtr().label("Formulário de Requerimento").displayString("Requerimento de ${nome!}");
    }

}

