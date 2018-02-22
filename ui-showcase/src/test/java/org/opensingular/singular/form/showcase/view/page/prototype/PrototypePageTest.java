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

package org.opensingular.singular.form.showcase.view.page.prototype;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.opensingular.form.SDictionary;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.context.SFormConfig;
import org.opensingular.form.document.RefType;
import org.opensingular.form.io.SFormXMLUtil;
import org.opensingular.singular.form.showcase.SpringWicketTester;
import org.opensingular.singular.form.showcase.dao.form.Prototype;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;

import static org.assertj.core.api.Assertions.assertThat;
import static org.opensingular.form.wicket.helpers.TestFinders.findOnForm;

@Ignore("Problems with maven environment")
@RunWith(Enclosed.class)
public class PrototypePageTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(locations = {"/applicationContext.xml"})
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
    public static class Base {
        @Inject
        private SpringWicketTester springWicketTester;
        @Inject @Named("formConfigWithDatabase")
        private SFormConfig<String> singularFormConfig;
        private WicketTester        driver;
        private SDictionary         dictionary;
        private SIComposite         currentInstance;
        private FormTester          form;

        @Before
        public void setUp() {
            driver = springWicketTester.wt();
            dictionary = SDictionary.create();
            dictionary.loadPackage(SPackagePrototype.class);
            createInstance();
        }

        private void createInstance() {
            RefType refType = RefType.of(() -> dictionary.getType(STypePrototype.class));
            currentInstance = (SIComposite) singularFormConfig.getDocumentFactory().createInstance(refType);
        }

        @Test
        public void rendersPrototypeOnScreen() {

            SIList campo = (SIList) currentInstance.getField(STypePrototype.CHILDREN_FIELD);
            assertThat(campo).isNotNull();

            SIComposite field = (SIComposite) campo.addNew();

            field.getField(STypePrototype.NAME_FIELD).setValue("Abacate");

            startPage();

            driver.assertContains("Abacate");
        }

        private AbstractLink findMasterDetailLink() {
            return findOnForm(AbstractLink.class, form.getForm(), (b) -> true)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o botão de adicionar do mestre detalhe"));
        }

        private void startPage() {
            driver.startPage(new PrototypePage() {
                @Override
                protected void loadOrBuildModel() {
                    super.loadOrBuildModel();
                    this.prototype = new Prototype();
                    SFormXMLUtil.toStringXML(currentInstance).ifPresent(x -> this.prototype.setXml(x));
                }

            });
        }
    }

}
