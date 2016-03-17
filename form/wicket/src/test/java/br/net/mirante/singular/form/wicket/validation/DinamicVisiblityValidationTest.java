package br.net.mirante.singular.form.wicket.validation;

import org.apache.wicket.markup.html.form.FormComponent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.net.mirante.singular.form.mform.STypeComposite;
import br.net.mirante.singular.form.mform.core.STypeString;
import br.net.mirante.singular.form.wicket.test.base.AbstractSingularFormTest;

public class DinamicVisiblityValidationTest extends AbstractSingularFormTest {

    String testValue = "fvrw1e4r5t4e.r6";
    STypeString fieldOne;
    STypeString fieldTwo;

    @Override
    protected void populateMockType(STypeComposite<?> mockType) {
        fieldOne = mockType.addCampoString("fieldOne");
        fieldOne.asAtrCore().obrigatorio(true);
        fieldTwo = mockType.addCampoString("fieldTwo");

        fieldTwo.asAtrBasic().dependsOn(fieldOne);
        fieldTwo.asAtrBasic()
                .visivel(instance -> instance.findNearestValue(fieldOne, String.class).orElse("").equals(testValue));
        fieldTwo.asAtrCore().obrigatorio(true);
    }

    @Test
    public void testIfContaisErrorOnlyForFieldOne() {
        formTester.submit(mockPage.getSingularValidationButton());
        Assert.assertFalse(findFormComponentsByType(fieldOne).findFirst().get().getFeedbackMessages().isEmpty());
        Assert.assertTrue(findFormComponentsByType(fieldTwo).findFirst().get().getFeedbackMessages().isEmpty());
    }

    @Test
    public void testIfNotContaisErrorForFieldTwoAfterChangeFieldOneValueWhithWrongValue() {
        formTester.setValue(findFieldOneFormComponent(), "abas" + testValue + "2132");
        wicketTester.executeAjaxEvent(findFieldOneFormComponent(), "change");
        formTester.submit(mockPage.getSingularValidationButton());
        Assert.assertTrue(findFormComponentsByType(fieldOne).findFirst().get().getFeedbackMessages().isEmpty());
        Assert.assertTrue(findFormComponentsByType(fieldTwo).findFirst().get().getFeedbackMessages().isEmpty());
    }

    @Test
    public void testIfContaisErrorForFieldTwoAfterChangeFieldOneValue() {
        formTester.setValue(findFieldOneFormComponent(), testValue);
        wicketTester.executeAjaxEvent(findFieldOneFormComponent(), "change");
        formTester.submit(mockPage.getSingularValidationButton());
        Assert.assertTrue(findFormComponentsByType(fieldOne).findFirst().get().getFeedbackMessages().isEmpty());
        Assert.assertFalse(findFormComponentsByType(fieldTwo).findFirst().get().getFeedbackMessages().isEmpty());
    }

    public FormComponent findFieldOneFormComponent() {
        return findFormComponentsByType(fieldOne).findFirst().get();
    }
}
