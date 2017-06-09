package org.opensingular.singular.form.showcase.view.page.showcase;

import static org.junit.Assert.assertNotNull;
import static org.opensingular.lib.wicket.util.util.WicketUtils.$m;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opensingular.form.context.SFormConfig;
import org.opensingular.singular.form.showcase.SpringWicketTester;
import org.opensingular.singular.form.showcase.component.CaseBaseForm;
import org.opensingular.singular.form.showcase.view.page.FormItemCasePanel;
import org.opensingular.singular.form.showcase.view.page.ItemCasePanel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        ItemCasePanel icp = new FormItemCasePanel("icp", $m.ofValue(cb));
        assertNotNull(icp);
    }

    @Test
    public void testeSaveForm() {
        final WicketTester wt = springWicketTester.wt();
        ItemCasePanel icp = new FormItemCasePanel("icp", $m.ofValue(cb));
        wt.startComponentInPage(icp);
        FormTester formTester = wt.newFormTester("icp:form");
        assertNotNull(formTester);
    }
}