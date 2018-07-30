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

import org.apache.wicket.validation.validator.UrlValidator;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.wicket.IWicketComponentMapper;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

/**
 * Custom String Mapper
 */
/*hidden*/@CaseItem(componentName = "Custom Mapper", subCaseName = "Vídeo", group = Group.CUSTOM,
/*hidden*/      resources = {@Resource(VideoMapper.class), @Resource(CaseCustomPackage.class)})
@SInfoType(spackage = CaseCustomPackage.class, name = "Video")
public class CaseCustomVideoMapperSType extends STypeComposite<SIComposite> {

    public STypeString video;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        video = this.addFieldString("video");
        video
            .addInstanceValidator(v -> {
                if (!new UrlValidator().isValid(v.getInstance().getValue())) {
                    v.error("URL inválida");
                }
            })
            //@destacar
            .setAspect(IWicketComponentMapper.ASPECT_WICKET_MAPPER, VideoMapper::new)
            .asAtr().label("Vídeo").required(true);
    }
}
