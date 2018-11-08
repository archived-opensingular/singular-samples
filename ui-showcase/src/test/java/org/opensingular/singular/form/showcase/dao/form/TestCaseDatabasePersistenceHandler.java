/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.singular.form.showcase.dao.form;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensingular.form.io.IOUtil;
import org.opensingular.form.type.core.attachment.IAttachmentPersistenceHandler;
import org.opensingular.form.type.core.attachment.IAttachmentRef;
import org.opensingular.lib.commons.util.TempFileUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestCaseDatabasePersistenceHandler {

    @Inject private IAttachmentPersistenceHandler persistenceService;
    
    @Test public void createProperReference() throws IOException {
        byte[]         content = "i".getBytes();
        File           f       = TempFileUtils.createTempFile(content);
        IAttachmentRef ref     = persistenceService.addAttachment(f, 1, "teste", "1123123123123");
        assertThat(ref.getId()).isNotEmpty();
        assertThat(IOUtils.toByteArray(ref.getContentAsInputStream())).isEqualTo(content);
        assertThat(ref.getSize()).isEqualTo(1);
    }
    
    @Test public void worksWithByteArrayAlso(){
        byte[] content = "np".getBytes();
//        IAttachmentRef ref = persistenceService.addAttachment(content);
//        assertThat(ref.getId()).isNotEmpty();
//        assertThat(ref.getHashSHA1())
//            .isEqualTo("003fffd5649fc27c0fc0d15a402a4fe5b0444ce7");
//        assertThat(ref.getContentAsByteArray()).isEqualTo(content);
//        assertThat(ref.getSize()).isEqualTo(2);
    }
    
    @Test public void savesToDatabaseOnAdding(){
        byte[] content = "1234".getBytes();
//        IAttachmentRef ref = persistenceService.addAttachment(new ByteArrayInputStream(content));
//        assertThat(persistenceService.getAttachment(ref.getHashSHA1())).isNotNull()
//            .isEqualsToByComparingFields(ref);
    }

    
    @Test public void listsAllStoredFiles(){
//        IAttachmentRef  ref1 = persistenceService.addAttachment("i".getBytes()),
//                        ref2 = persistenceService.addAttachment("1234".getBytes());
//        assertThat(persistenceService.getAttachments())
//            .containsOnly(ref1, ref2);
    }
    
    @Test public void retrieveSpecificFile(){
//        persistenceService.addAttachment("i".getBytes());
//        IAttachmentRef ref2 = persistenceService.addAttachment("1234".getBytes());
//        persistenceService.addAttachment("123456".getBytes());
//
//        assertThat(persistenceService.getAttachment(ref2.getHashSHA1()))
//            .isEqualTo(ref2);
    }
    
    @Test public void deleteSpecificFile(){
//        persistenceService.addAttachment("i".getBytes());
//        IAttachmentRef ref2 = persistenceService.addAttachment("1234".getBytes());
//        persistenceService.addAttachment("123456".getBytes());
//
//        persistenceService.deleteAttachment(ref2.getId());
//        assertThat(persistenceService.getAttachments()).hasSize(2)
//            .doesNotContain(ref2);
    }
}
