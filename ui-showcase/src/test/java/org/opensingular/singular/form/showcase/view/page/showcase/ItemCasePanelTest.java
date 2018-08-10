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

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensingular.form.context.SFormConfig;
import org.opensingular.singular.form.showcase.SpringWicketTester;
import org.opensingular.singular.form.showcase.component.CaseBaseForm;
import org.opensingular.singular.form.showcase.view.page.ItemCasePanel;
import org.opensingular.singular.form.showcase.view.page.form.FormItemCasePanel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;

import static org.junit.Assert.assertNotNull;
import static org.opensingular.lib.wicket.util.util.WicketUtils.$m;

/**
 * TODO TESTE BUGADO, QUEBRA A BUILD NO SERVER??
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ItemCasePanelTest {

    CaseBaseForm cb;

    @Inject @Named("formConfigWithDatabase")
    private SFormConfig<String> singularFormConfig;

    @Inject
    private SpringWicketTester springWicketTester;

    @Before
    public void setUp() {
//        cb = new CaseBaseForm(CaseInputCoreIntegerPackage.class, "Numeric", "Integer", AnnotationMode.NONE);
    }

    @Test
    public void testRendering() {
        ItemCasePanel<?> icp = new FormItemCasePanel("icp", $m.ofValue(cb));
        assertNotNull(icp);
    }

    @Test
    public void testSaveForm() {
        final WicketTester wt = springWicketTester.wt();
        ItemCasePanel<?> icp = new FormItemCasePanel("icp", $m.ofValue(cb));
        wt.startComponentInPage(icp);
        FormTester formTester = wt.newFormTester("icp:form");
        assertNotNull(formTester);
    }
}