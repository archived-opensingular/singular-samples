package org.opensingular.singular.form.showcase.component.form.importer;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseImporterPackage.class, name = "endereco")
public class STypeEndereco extends STypeComposite<SIComposite> {
    public STypeString rua;
    public STypeInteger numero;
    public STypeString bairro;
    public STypeString cidade;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        rua = this.addFieldString("rua");
        numero = this.addFieldInteger("numero");
        bairro = this.addFieldString("bairro");
        cidade = this.addFieldString("cidade");
    }
}
