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

package org.opensingular.sample.studio.repository;

import org.opensingular.form.SIComposite;
import org.opensingular.form.spring.SpringFormPersistenceInMemory;
import org.opensingular.sample.studio.dao.NormaDAO;
import org.opensingular.sample.studio.entity.NormaEntity;
import org.opensingular.sample.studio.form.Norma;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("normaRepository")
public class NormaRepository extends SpringFormPersistenceInMemory<Norma, SIComposite>
        implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private NormaDAO normaDAO;

    public NormaRepository() {
        super(Norma.class);
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (NormaEntity n : normaDAO.listAll()) {
            SIComposite instance = createInstance();
            instance.setValue("nome", n.getNome());
            insert(instance, null);
        }
    }
}