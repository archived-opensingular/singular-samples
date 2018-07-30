package org.opensingular.singular.form.showcase.component.form.core.multiselect.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInputCorePackage.class)
public class STypeArquivo extends STypeComposite<SIComposite> {

    public STypeString fileName;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        fileName = this.addFieldString("fileName");
    }
}
