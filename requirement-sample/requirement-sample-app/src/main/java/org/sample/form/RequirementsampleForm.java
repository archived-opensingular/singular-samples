package org.sample.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewTab;

@SInfoType(spackage = RequirementsamplePackage.class, name = "Requirementsample", label = "Formulário de Requerimento")
public class RequirementsampleForm extends STypeComposite<SIComposite> {

    public STypeDadosPessoais dadosPessoais;
    public EngenheiroForm engenheiroForm;

    @Override
    protected void onLoadType(TypeBuilder tb) {
        this.asAtrIndex().indexed(Boolean.TRUE);
        dadosPessoais = addField("dadosPessoais", STypeDadosPessoais.class);
        engenheiroForm = addField("engenheiroForm", EngenheiroForm.class);

        dadosPessoais.asAtrIndex().indexed(Boolean.TRUE);

        SViewTab sViewTab = new SViewTab();
        sViewTab.addTab(dadosPessoais, "Dados Pessoais");
        sViewTab.addTab(engenheiroForm, "Engenheiro");
        withView(sViewTab);

        asAtr().label("Formulário de Requerimento")
                .displayString("Requerimento de ${dadosPessoais.nomeCompleto!}");
    }

}

