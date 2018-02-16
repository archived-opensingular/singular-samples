package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewTab;

@SInfoType(spackage = RequirementsamplePackage.class, name = "Requirementsample", label = "Example Requirement Form")
public class RequirementsampleForm extends STypeComposite<SIComposite> {

    public STypeDadosPessoais dadosPessoais;

    @Override
    protected void onLoadType(TypeBuilder tb) {
        this.asAtrIndex().indexed(true);
        dadosPessoais = addField("dadosPessoais", STypeDadosPessoais.class);
        dadosPessoais.asAtrIndex().indexed(true);
        SViewTab sViewTab = new SViewTab();
        sViewTab.addTab(dadosPessoais);
        this.withView(sViewTab);
        this.asAtr().label("Formulário de Requerimento").displayString("Requerimento de ${nome!}");
    }

}

