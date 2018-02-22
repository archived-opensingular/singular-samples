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

package org.opensingular.singular.form.showcase.view.page.showcase;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensingular.singular.form.showcase.SpringWicketTester;
import org.opensingular.singular.form.showcase.component.ShowCaseTable;
import org.opensingular.singular.form.showcase.view.page.ComponentPage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ComponentPageTest {

    @Inject
    private SpringWicketTester springWicketTester;

    @Inject
    private ShowCaseTable showCaseTable;

    @Test
    public void testRendering() {
        showCaseTable.getGroups().forEach(group -> {
            group.getItens().forEach(item -> {
                springWicketTester.wt().startPage(ComponentPage.class, new PageParameters().add("cn", item.getComponentName().toLowerCase()));
                springWicketTester.wt().assertRenderedPage(ComponentPage.class);
            });
        });
    }

}