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

package org.opensingular.requirementsamplemodule.config;

import org.opensingular.requirement.studio.init.RequirementStudioAppInitializer;
import org.opensingular.requirementsamplemodule.RequirementsampleModule;
import org.opensingular.requirementsamplemodule.spring.RequirementSamplePersistenceConfiguration;
import org.opensingular.requirement.commons.spring.SingularDefaultPersistenceConfiguration;


public class RequirementSampleInitializer implements RequirementStudioAppInitializer {
    @Override
    public String moduleCod() {
        return RequirementsampleModule.REQUIREMENT_SAMPLE;
    }

    @Override
    public String[] springPackagesToScan() {
        return new String[]{"org.opensingular"};
    }

    @Override
    public Class<? extends SingularDefaultPersistenceConfiguration> persistenceConfiguration() {
        return RequirementSamplePersistenceConfiguration.class;
    }


}