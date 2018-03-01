package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeCEP;

import javax.annotation.Nonnull;

@SInfoType(name = "Endereco", spackage = SPackageStudioPersistenceForm.class)
public class STypeEndereco extends STypeComposite<SIComposite> {
    STypeCEP cep;
    STypeUF uf;
    STypeString endereco;
    STypeInteger numero;
    STypeString bairro;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        createType();
        cep.asAtr().label("CEP").asAtrBootstrap().colPreference(2);
        uf.asAtr().label("UF").asAtrBootstrap().colPreference(3);
        endereco.asAtr().label("Endereço").asAtrBootstrap().newRow().colPreference(6);
        numero.asAtr().label("Numero").asAtrBootstrap().colPreference(2);
        bairro.asAtr().label("Bairro").asAtrBootstrap().colPreference(4);
    }

    private void createType() {
        cep = addField("cep", STypeCEP.class);
        uf = addField("uf", STypeUF.class);
        endereco = addField("endereco", STypeString.class);
        numero = addField("numero", STypeInteger.class);
        bairro = addField("bairro", STypeString.class);
    }
}
