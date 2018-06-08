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

package org.opensingular.singular.form.showcase.component.form.core;

import java.util.Optional;
import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.SType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeHTML;
import org.opensingular.form.view.richtext.RichTextAction;
import org.opensingular.form.view.richtext.RichTextSelectionContext;
import org.opensingular.form.view.richtext.SViewByRichTextNewTab;
import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;

@CaseItem(componentName = "HTML", subCaseName = "Editor Rico em Nova Aba", group = Group.INPUT)
@SInfoType(spackage = CaseInputCorePackage.class, name = "PortletRichText")
public class CaseInputCorePortletRichTextSType extends STypeComposite<SIComposite> {

    public STypeHTML parecer;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        parecer = this.addField("parecer", STypeHTML.class);
        parecer.asAtr()
                .label("Parecer Técnico");
        SViewByRichTextNewTab viewByRichTextNewTab = new SViewByRichTextNewTab();
        addCapsLockSelectionButton(viewByRichTextNewTab);
        parecer.withView(viewByRichTextNewTab);
    }

    private void addCapsLockSelectionButton(SViewByRichTextNewTab viewByRichTextNewTab) {
        viewByRichTextNewTab.addAction(new RichTextAction<RichTextSelectionContext>() {
            @Override
            public String getLabel() {
                return "Uppercase selected text";
            }

            @Override
            public Icon getIcon() {
                return (Icon) () -> "fa fa-arrow-up";
            }

            @Override
            public Optional<Class<? extends SType<?>>> getForm() {
                return Optional.empty();
            }

            @Override
            public Class getType() {
                return RichTextSelectionContext.class;
            }

            @Override
            public void onAction(RichTextSelectionContext richTextActionContext, Optional<SInstance> sInstance) {
                richTextActionContext.setReturnValue(richTextActionContext.getTextSelected().toUpperCase());
            }

        });
    }
}