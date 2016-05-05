package br.net.mirante.singular.form.wicket.mapper.selection;

import br.net.mirante.singular.form.mform.SInstance;
import br.net.mirante.singular.form.mform.STypeComposite;
import br.net.mirante.singular.form.mform.basic.view.SViewSearchModal;
import br.net.mirante.singular.form.mform.core.STypeString;
import br.net.mirante.singular.form.mform.provider.FilteredPagedProvider;
import br.net.mirante.singular.form.mform.provider.ProviderContext;
import br.net.mirante.singular.form.mform.provider.filter.FilterConfigBuilder;
import br.net.mirante.singular.form.wicket.helpers.SingularFormBaseTest;
import br.net.mirante.singular.form.wicket.mapper.search.SearchModalPanel;
import br.net.mirante.singular.util.wicket.ajax.ActionAjaxLink;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.FormComponent;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SearchModalMapperTest extends SingularFormBaseTest {

    private STypeString mandatoryField;
    private STypeString dependentField;

    @Override
    protected void buildBaseType(STypeComposite<?> baseType) {

        mandatoryField = baseType.addFieldString("mandatoryField", true);

        mandatoryField.withView(new SViewSearchModal());
        mandatoryField.asAtrProvider().filteredPagedProvider(new FilteredPagedProvider<String>() {
            @Override
            public List<String> load(ProviderContext<SInstance> context) {
                return Arrays.asList("1", "2");
            }

            @Override
            public void configureFilter(FilterConfigBuilder builder) {
                builder.configureType(filter -> filter.addFieldString("search"));
                builder.addColumn("String");
                builder.lazy(false);
            }
        });
        dependentField = baseType.addFieldString("dependentField");
        dependentField.asAtr().dependsOn(mandatoryField);
        dependentField.asAtr().visible(ins -> StringUtils.isNotEmpty(ins.findNearestValue(mandatoryField, String.class).orElse(null)));

    }

    @Test
    public void testIfChooseValueInModelUpdatesDependentComponent() {

        FormComponent dependentFieldComp = findFormComponentsByType(dependentField)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o componente dependente"));

        FormComponent mandatoryFieldComp = findFormComponentsByType(mandatoryField)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o componente mandatorio"));

        tester.assertInvisible(dependentFieldComp.getPageRelativePath());

        Button link = findOnForm(Button.class, form.getForm(), al -> al.getId().equals(SearchModalPanel.MODAL_TRIGGER_ID))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o link para abertura da modal"));

        tester.executeAjaxEvent(link, "click");

        List<AjaxLink> links = findOnForm(ActionAjaxLink.class, form.getForm(),
                al -> al.getId().equals("link"))
                .collect(Collectors.toList());

        tester.executeAjaxEvent(links.get(0), "click");

        tester.assertModelValue(mandatoryFieldComp.getPageRelativePath(), "1");
        tester.assertVisible(dependentFieldComp.getPageRelativePath());

    }
}