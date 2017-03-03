package org.opensingular.form.wicket.mapper.table;

import org.apache.wicket.markup.html.form.Button;
import org.junit.Before;
import org.junit.Test;
import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByTable;
import org.opensingular.form.wicket.helpers.AssertionsWComponent;
import org.opensingular.form.wicket.helpers.SingularDummyFormPageTester;

public class TableListWithCompositeTypeTest {

    private SingularDummyFormPageTester tester;

    private static STypeList<STypeComposite<SIComposite>, SIComposite> mockList;
    private static STypeComposite<?> mockTypeComposite;
    private static STypeString simpleString;

    private static void buildBaseType(STypeComposite<?> mockType) {

        mockList = mockType.addFieldListOfComposite("mockList", "mockTypeComposite");
        mockTypeComposite = mockList.getElementsType();

        mockList.withView(SViewListByTable::new);
        mockList.asAtr()
                .label("Mock Type Composite");

        simpleString = mockTypeComposite.addFieldString("mockTypeComposite", true);

    }

    @Before
    public void setUp(){
        tester = new SingularDummyFormPageTester();
        tester.getDummyPage().setTypeBuilder(TableListWithCompositeTypeTest::buildBaseType);
        tester.startDummyPage();
    }

    @Test
    public void testAddItem() {
        final Button addButton = findAddButton();

        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(0);

        tester.executeAjaxEvent(addButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(1);

        tester.executeAjaxEvent(addButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(2);

        tester.executeAjaxEvent(addButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(3);

        tester.getAssertionsForm()
                .getSubCompomentWithType(mockList)
                .getSubCompomentWithType(mockTypeComposite)
                .getSubCompomentWithType(simpleString).assertSInstance();
    }

    @Test
    public void testRemoveItem() {
        final Button addButton = findAddButton();

        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(0);

        tester.executeAjaxEvent(addButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(1);

        tester.getAssertionsForm()
                .getSubCompomentWithType(mockList)
                .getSubCompomentWithType(mockTypeComposite)
                .getSubCompomentWithType(simpleString).assertSInstance();

        final Button removeButton = (Button) tester.getAssertionsForm().findSubComponent(b -> b.getClass().getName().contains("RemoverButton")).getTarget();

        tester.executeAjaxEvent(removeButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(0);
    }

    @Test
    public void testAddItemAndFillOptions() {
        final Button addButton = findAddButton();

        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(0);

        tester.executeAjaxEvent(addButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(1);

        final String value = "123456";

        AssertionsWComponent stringAssertion = tester.getAssertionsForm()
                .getSubCompomentWithType(mockList)
                .getSubCompomentWithType(mockTypeComposite)
                .getSubCompomentWithType(simpleString);

        stringAssertion.assertSInstance().getTarget().setValue(value);

        tester.newFormTester().submit();

        stringAssertion.assertSInstance().isValueEquals(value);
    }

    @Test
    public void testAddItemFillOptionsAndThenAddOtherItem() {
        final Button addButton = findAddButton();

        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(0);

        tester.executeAjaxEvent(addButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(1);

        final String value = "123456";

        AssertionsWComponent stringAssertion = tester.getAssertionsForm()
                .getSubCompomentWithType(mockList)
                .getSubCompomentWithType(mockTypeComposite)
                .getSubCompomentWithType(simpleString);

        stringAssertion.assertSInstance().getTarget().setValue(value);

        tester.executeAjaxEvent(addButton, "click");
        tester.getAssertionsForm().getSubCompomentWithType(mockList).assertSInstance().isList(2);

        stringAssertion.assertSInstance().isValueEquals(value);
    }

    public Button findAddButton(){
        return (Button) tester.getAssertionsForm().findSubComponent(b -> b.getClass().getName().contains("AddButton")).getTarget();
    }
}
