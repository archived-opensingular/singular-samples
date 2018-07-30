package org.opensingular.singular.form.showcase.component.form.help;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.util.STypeYearMonth;

import javax.annotation.Nonnull;

@SInfoType(spackage = CaseHelpPackage.class, name = "experiencia")
public class STypeExperiencia extends STypeComposite<SIComposite> {

    public STypeYearMonth dtInicioExperiencia;
    public STypeYearMonth dtFimExperiencia;
    public STypeString empresa;
    public STypeString cargo;
    public STypeString atividades;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        dtInicioExperiencia = this.addField("dtInicioExperiencia", STypeYearMonth.class, true);
        dtFimExperiencia = this.addField("dtFimExperiencia", STypeYearMonth.class);
        empresa = this.addFieldString("empresa", true);
        cargo = this.addFieldString("cargo", true);
        atividades = this.addFieldString("atividades");

        dtInicioExperiencia
                .asAtr().label("Data inicial")
                .asAtrBootstrap().colPreference(2);

        dtFimExperiencia
                .asAtr().label("Data final")
                .asAtrBootstrap().colPreference(2);

        empresa
                .asAtr().label("Empresa")
                .asAtrBootstrap().colPreference(8);

        cargo
                .asAtr().label("Cargo");

        atividades
                .withTextAreaView()
                .asAtr().label("Atividades Desenvolvidas");

        this.asAtr().label("Experiência");
    }
}
