/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.singular.form.showcase.component.form.interaction;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.SIBigDecimal;
import org.opensingular.form.type.core.STypeMonetary;
import org.opensingular.form.view.SViewListByMasterDetail;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.form.interaction.form.STypeFuncionario;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Listener que atualiza valores de itens de um mestre-detalhe.
 */
/*hidden*/@CaseItem(componentName = "Listeners", subCaseName = "Master/detail", group = Group.INTERACTION)
@SInfoType(spackage = CaseInteractionPackage.class, name = "MasterDetail")
public class CaseUpdateListenerMasterDetailSType extends STypeComposite<SIComposite> {

    public STypeMonetary salarioMaximo;
    public STypeList<STypeFuncionario, SIComposite> funcionarios;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        salarioMaximo = this.addFieldMonetary("salarioMaximo");
        funcionarios = this.addFieldListOf("funcionarios", STypeFuncionario.class);

        salarioMaximo.asAtr().label("Teto salarial");

        STypeFuncionario stFuncionario = funcionarios.getElementsType();

        SViewListByMasterDetail funcionariosView = new SViewListByMasterDetail()
                .col(stFuncionario.nome)
                .col(stFuncionario.salario);

        funcionarios
                .withView(funcionariosView)
                .asAtr().label("Experiências profissionais");

        stFuncionario.salario
                .withUpdateListener(this::updateSalario)
                .asAtr().dependsOn(salarioMaximo);
    }

    private void updateSalario(SIBigDecimal siSalario) {
        SIBigDecimal siSalarioMaximo = siSalario.findNearestOrException(salarioMaximo);

        BigDecimal vs = siSalario.getValue();
        BigDecimal vsm = siSalarioMaximo.getValue();
        if ((vs != null) && (vsm != null) && (vs.compareTo(vsm) > 0)) {
            siSalario.setValue(siSalarioMaximo.getValue());
        }
    }
}
