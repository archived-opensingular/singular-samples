package org.sample.form.generic;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeLong;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.generic.STGenericComposite;
import org.sample.form.RequirementsamplePackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = RequirementsamplePackage.class)
public class STIdDescricao extends STGenericComposite<SIIdDescricao> {

    public STypeLong   id;
    public STypeString descricao;

    public STIdDescricao() {
        super(SIIdDescricao.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().displayString("${descricao!}");
        id = this.addFieldLong("id");
        descricao = this.addFieldString("descricao");
        descricao
                .asAtr()
                .maxLength(500);
    }
}
