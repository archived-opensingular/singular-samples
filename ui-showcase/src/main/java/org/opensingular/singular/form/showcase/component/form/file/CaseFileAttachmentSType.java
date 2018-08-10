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

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;

import javax.annotation.Nonnull;

/**
 * Campo para anexar arquivos
 */
@CaseItem(componentName = "Attachment", group = Group.FILE, resources = {@Resource(PageWithAttachment.class),
        @Resource(CaseFilePackage.class)})
@SInfoType(spackage = CaseFilePackage.class, name = "Attachment")
public class CaseFileAttachmentSType extends STypeComposite<SIComposite> {

    public STypeAttachment anexo;
    public STypeAttachment foto;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        anexo = this.addField("anexo", STypeAttachment.class);
        foto = this.addField("foto", STypeAttachment.class);

        anexo
                .asAtr().label("Anexo").required(true)
                .asAtrBootstrap().colPreference(6);

        foto
                .asAtr().label("Foto").required(false)
                .allowedFileTypes("jpg", "image/png")
                .asAtrBootstrap().colPreference(6);
    }
}
