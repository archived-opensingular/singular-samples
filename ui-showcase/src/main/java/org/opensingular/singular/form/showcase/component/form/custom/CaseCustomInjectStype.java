package org.opensingular.singular.form.showcase.component.form.custom;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * É possivel passar dados para o formulario a partir de Injeção de Beans.
 */
@SInfoType(spackage = CaseCustomPackage.class, name = "CaseInject")
@CaseItem(componentName = "Inject",subCaseName = "Default", group = Group.CUSTOM)
public class CaseCustomInjectStype extends STypeComposite<SIComposite> {

    //@destacar:bloco
    @Inject
    private CaseCustomInjectService customInjectService;
    //@destacar:fim

    public STypeString injectOptions;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        this.asAtr().label("Escolha uma fruta");

        injectOptions = this.addFieldString("injectOptions");
        injectOptions.selectionOf(customInjectService.fruitsOptions());
    }
}
