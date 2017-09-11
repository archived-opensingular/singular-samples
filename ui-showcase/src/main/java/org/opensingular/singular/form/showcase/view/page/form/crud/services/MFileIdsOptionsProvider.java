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

package org.opensingular.singular.form.showcase.view.page.form.crud.services;

import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;
import org.opensingular.form.provider.SSimpleProvider;
import org.opensingular.form.util.transformer.SCompositeListBuilder;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component("filesChoiceProvider")
public class MFileIdsOptionsProvider implements SSimpleProvider {

    @Override
    public void fill(SCompositeListBuilder builder) {
        /* Utilitario necessário para se ler arquivos .war no Wildfly */
        VirtualFile folder = VFS.getChild(this.getClass().getResource("/example_files").getFile());
        folder.getChildren().forEach(child -> builder.add().set("fileName", child.getName()));
    }
}