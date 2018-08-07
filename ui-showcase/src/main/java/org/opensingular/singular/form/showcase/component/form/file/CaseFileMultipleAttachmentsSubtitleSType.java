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

package org.opensingular.singular.form.showcase.component.form.file;

import org.apache.commons.lang3.StringUtils;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeAttachmentList;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

import javax.annotation.Nonnull;

/**
 * Campo para anexar vários arquivos
 */
@CaseItem(componentName = "Multiple Attachments", group = Group.FILE, subCaseName = "Subtitle", resources = @Resource(CaseFilePackage.class))
@SInfoType(spackage = CaseFilePackage.class, name = "MultipleAttachmentsSubtitle")
public class CaseFileMultipleAttachmentsSubtitleSType extends STypeComposite<SIComposite> {

    public STypeAttachmentList layoutsRotulagem;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        layoutsRotulagem = this.addFieldListOfAttachment("layoutsRotulagem", "layout");

        layoutsRotulagem
                .withMiniumSizeOf(1)
                .withMaximumSizeOf(4)
                .asAtr().label("Layouts Rotulagem")
                //@destacar
                .subtitle("Informar apenas os layouts de embalagem em uso");

        layoutsRotulagem.getElementsType().asAtr()
            .allowedFileTypes("image/png", "image/jpeg", "pdf", "zip");

        this.asAtr().displayString(cc -> cc.instance()
            .findNearest(layoutsRotulagem)
            .map(SInstance::toStringDisplay)
            .orElse(StringUtils.EMPTY));
    }
}
