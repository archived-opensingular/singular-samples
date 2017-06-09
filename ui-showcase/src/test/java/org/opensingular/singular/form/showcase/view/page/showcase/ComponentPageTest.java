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