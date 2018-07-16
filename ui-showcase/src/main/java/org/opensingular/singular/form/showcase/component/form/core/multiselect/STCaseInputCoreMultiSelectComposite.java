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

package org.opensingular.singular.form.showcase.component.form.core.multiselect;

import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.converter.SInstanceConverter;
import org.opensingular.form.type.generic.STGenericComposite;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.core.CaseInputCorePackage;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * Para usar um tipo composto na seleção, este deve extender o STypeComposite. <br/>
 * É permitido se mudar quais campos serão utilizados como chave e valor.
 */
@CaseItem(componentName = "Multi Select", subCaseName = "Tipo Composto", group = Group.INPUT,
        resources = {@Resource(SICaseInputCoreMultiSelectComposite.class), @Resource(STComponenteQuimico.class),
                @Resource(SIComponenteQuimico.class)})
@SInfoType(spackage = CaseInputCorePackage.class, name = "TipoComposto")
public class STCaseInputCoreMultiSelectComposite extends STGenericComposite<SICaseInputCoreMultiSelectComposite> {

    public STypeList<STComponenteQuimico, SIComponenteQuimico> componentesQuimicos;

    public STCaseInputCoreMultiSelectComposite() {
        super(SICaseInputCoreMultiSelectComposite.class);
    }

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        componentesQuimicos = this.addFieldListOf("componentesQuimicos", STComponenteQuimico.class);

        componentesQuimicos.selectionOf(ComponenteQuimico.class)
                .id(ComponenteQuimico::getNome)
                .display("${formulaQuimica} - ${nome}")
                .converter(new SIComponenteQuimicoConverter())
                .simpleProviderOf(
                        new ComponenteQuimico("Água", "h2o"),
                        new ComponenteQuimico("Água Oxigenada", "h2o2"),
                        new ComponenteQuimico("Gás Oxigênio", "o2"),
                        new ComponenteQuimico("Açúcar", "C12H22O11")
                );
    }

    private class SIComponenteQuimicoConverter implements SInstanceConverter<ComponenteQuimico, SIComponenteQuimico>{
        @Override
        public void fillInstance(SIComponenteQuimico ins, ComponenteQuimico obj) {
            ins.nome().setValue(obj.getNome());
            ins.formulaQuimica().setValue(obj.getFormulaQuimica());
        }

        @Override
        public ComponenteQuimico toObject(SIComponenteQuimico ins) {
            return new ComponenteQuimico(ins.formulaQuimica().getValue(), ins.nome().getValue());
        }
    }

    public static class ComponenteQuimico implements Serializable {
        private String nome;
        private String formulaQuimica;

        public ComponenteQuimico(String formulaQuimica, String nome) {
            this.formulaQuimica = formulaQuimica;
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getFormulaQuimica() {
            return formulaQuimica;
        }

        public void setFormulaQuimica(String formulaQuimica) {
            this.formulaQuimica = formulaQuimica;
        }

    }
}
