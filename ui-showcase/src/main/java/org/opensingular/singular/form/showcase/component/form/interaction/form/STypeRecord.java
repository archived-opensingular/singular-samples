package org.opensingular.singular.form.showcase.component.form.interaction.form;

import org.opensingular.form.SInfoType;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.generic.STGenericComposite;
import org.opensingular.singular.form.showcase.component.form.interaction.CaseInteractionPackage;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseInteractionPackage.class)
public class STypeRecord extends STGenericComposite<SIItem> {

    public STypeString text;
    public STypeDate date;

    public STypeRecord() {
        super(SIItem.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        text = this.addFieldString("text");
        date = this.addFieldDate("date");

        text
                .asAtr().label("Text")
                .asAtrBootstrap().colPreference(3);

        date
                .asAtr().label("Date")
                .asAtrBootstrap().colPreference(2);
    }
}
