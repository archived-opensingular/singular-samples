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

import org.opensingular.form.document.SDocument;
import org.opensingular.form.spring.SpringSDocumentFactory;
import org.opensingular.form.type.core.attachment.IAttachmentPersistenceHandler;
import org.opensingular.form.type.core.attachment.handlers.InMemoryAttachmentPersistenceHandler;
import org.opensingular.lib.commons.context.RefService;
import org.opensingular.lib.commons.context.ServiceRegistryLocator;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.opensingular.form.type.core.attachment.handlers.FileSystemAttachmentPersistenceHandler
        .newTemporaryHandler;

@Named("showcaseDocumentFactory")
public class ShowcaseDocumentFactory extends SpringSDocumentFactory {
    @Override
    protected void setupDocument(SDocument document) {
        try {
            document.setAttachmentPersistenceTemporaryHandler(RefService.of(newTemporaryHandler()));
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Could not create temporary file folder, using memory instead", e);
            document.setAttachmentPersistenceTemporaryHandler(RefService.of(new InMemoryAttachmentPersistenceHandler()));
        }
        IAttachmentPersistenceHandler<?> persist = ServiceRegistryLocator.locate().lookupServiceOrException(IAttachmentPersistenceHandler.class);
        document.setAttachmentPersistencePermanentHandler(RefService.ofToBeDescartedIfSerialized(persist));
    }


}
