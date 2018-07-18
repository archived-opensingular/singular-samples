package org.opensingular.singular.form.showcase.component.form.layout.stypes;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.SViewListByMasterDetail;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseLayoutPackage.class)
public class STypeExperienciaList extends STypeComposite<SIComposite> {

    public STypeList<STypeExperienciaProfissional, SIComposite> experiencias;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        experiencias = this.addFieldListOf("experiencias", STypeExperienciaProfissional.class);

        experiencias
                .withView(SViewListByMasterDetail::new)
                .asAtr().label("Experiências profissionais");
    }
}
