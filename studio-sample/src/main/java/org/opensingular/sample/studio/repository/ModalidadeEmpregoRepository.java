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
import org.opensingular.sample.studio.dao.ModalidadeEmpregoDAO;
import org.opensingular.sample.studio.entity.ModalidadeEmpregoEntity;
import org.opensingular.sample.studio.form.ModalidadeDeEmprego;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("modalidadeDeEmpregoRepository")
public class ModalidadeEmpregoRepository extends SpringFormPersistenceInMemory<ModalidadeDeEmprego, SIComposite>
        implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private ModalidadeEmpregoDAO modalidadeEmpregoDAO;

    public ModalidadeEmpregoRepository() {
        super(ModalidadeDeEmprego.class);
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (ModalidadeEmpregoEntity m : modalidadeEmpregoDAO.listAll()) {
            SIComposite instance = createInstance();
            instance.setValue("nome", m.getNome());
            insert(instance, null);
        }
    }
}