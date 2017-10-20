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

package org.opensingular.singular.form.showcase.view;

import javax.inject.Inject;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensingular.singular.form.showcase.SpringWicketTester;
import org.opensingular.singular.form.showcase.view.page.form.crud.CrudPage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class HomeTest {

    private static final String ROOT_PATH = "pageBody:_Content",
            OPTIONS_FORM = ROOT_PATH + ":optionsForm",
            NEW_BUTTON = ROOT_PATH + ":form:insert";

    @Inject
    private SpringWicketTester springWicketTester;

    @Test
    @Ignore
    //TODO DANILO revisar porque este teste quebra aleatoriamente
    public void onlyShowTheNewButtonAfterTemplateIsSelected() {
        final WicketTester driver = springWicketTester.wt();

        driver.startPage(CrudPage.class);
        driver.assertRenderedPage(CrudPage.class);
        driver.assertInvisible(NEW_BUTTON);
        FormTester options = driver.newFormTester(OPTIONS_FORM, false);
        options.select("options", 0);
        driver.assertVisible(NEW_BUTTON);
    }
}
