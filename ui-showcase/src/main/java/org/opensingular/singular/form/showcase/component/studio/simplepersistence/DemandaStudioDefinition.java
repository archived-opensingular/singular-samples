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

package org.opensingular.singular.form.showcase.component.studio.simplepersistence;

import org.opensingular.form.studio.StudioCRUDPermissionStrategy;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.definition.StudioTableDefinition;

/**
 * Persistence mapping for multiple Foreign Keys
 */
@CaseItem(componentName = "2. Multiple Foreign Keys", group = Group.STUDIO_PERSISTENCE,
resources = {
        @Resource(DemandaPackage.class),
        @Resource(DemandaRepository.class),
        @Resource(STypeDemanda.class),
        @Resource(PessoaRef.class),
        @Resource(value = DemandaStudioDefinition.class, extension = "sql")
})
public class DemandaStudioDefinition implements StudioDefinition {

    @Override
    public Class<DemandaRepository> getRepositoryClass() {
        return DemandaRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioTableDefinition studioDataTable) {
        studioDataTable.add("STypeDemanda", "titulo");
    }

    @Override
    public String getTitle() {
        return "Cadastro de Demandas";
    }

    @Override
    public StudioCRUDPermissionStrategy getPermissionStrategy() {
        return StudioCRUDPermissionStrategy.ALL;
    }
}
