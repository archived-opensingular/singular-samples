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

package org.opensingular.singular.form.showcase.component.form.custom.mapper;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.wicket.IWicketComponentMapper;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

/**
 * Custom Range Mapper
 */
@CaseItem(componentName = "Custom Mapper", subCaseName = "Range Slider", group = Group.CUSTOM,
resources = {@Resource(RangeSliderMapper.class), @Resource(value = RangeSliderMapper.class, extension = "js"),
        @Resource(value = STFaixaIdade.class), @Resource(value = SIFaixaIdade.class)})
@SInfoType(spackage = CaseCustomPackage.class, name = "RangeSlider")
public class CaseCustomRangeMapperSType extends STypeComposite<SIComposite> {

    public STFaixaIdade faixaIdade;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        faixaIdade = this.addField("faixaIdade", STFaixaIdade.class);

        faixaIdade.asAtr().label("Faixa de Idade");
        //@destacar:bloco
        faixaIdade.setAspect(IWicketComponentMapper.ASPECT_WICKET_MAPPER,
                () -> new RangeSliderMapper(faixaIdade.valorInicial, faixaIdade.valorFinal));
        //@destacar:fim
    }
}
