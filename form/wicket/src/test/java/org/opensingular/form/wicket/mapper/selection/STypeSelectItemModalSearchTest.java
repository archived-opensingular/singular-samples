package org.opensingular.form.wicket.mapper.selection;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.Button;
import org.junit.Test;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.converter.ValueToSICompositeConverter;
import org.opensingular.form.helpers.AssertionsSInstance;
import org.opensingular.form.provider.Config;
import org.opensingular.form.provider.FilteredProvider;
import org.opensingular.form.provider.ProviderContext;
import org.opensingular.form.view.SViewSearchModal;
import org.opensingular.form.wicket.helpers.SingularDummyFormPageTester;
import org.opensingular.form.wicket.mapper.search.SearchModalPanel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class STypeSelectItemModalSearchTest {

    private static STypeComposite<SIComposite> notebook;

    private static final String MARCA = "marca";
    private static final String MEMORIA = "memoria";
    private static final String DISCO = "disco";
    private static final String SISTEMA_OPERACIONAL = "sistemaOperacional";

    private static void buildBaseType(STypeComposite<?> baseType) {
        notebook = baseType.addFieldComposite("notebook");

        notebook.addFieldString(MARCA);
        notebook.addFieldString(MEMORIA);
        notebook.addFieldString(DISCO);
        notebook.addFieldString(SISTEMA_OPERACIONAL);

        notebook.withView(new SViewSearchModal());
        notebook.asAtrProvider().filteredProvider(new FilteredProvider<Notebook>() {
            @Override
            public void configureProvider(Config cfg) {
                cfg.getFilter().addFieldString(MARCA);
                cfg.getFilter().addFieldString(SISTEMA_OPERACIONAL);
                cfg.result().addColumn(MARCA, "Marca")
                        .addColumn(MEMORIA, "Memoria")
                        .addColumn(DISCO, "Disco")
                        .addColumn(SISTEMA_OPERACIONAL, "Sistema Operacional");
            }

            @Override
            public List<Notebook> load(ProviderContext<SInstance> context) {
                return Arrays.asList(new Notebook("Apple", "4GB", "1T", "OSX"), new Notebook("Samsug", "8GB", "1TB", "ArchLinux"));
            }
        });

        notebook.asAtrProvider().converter((ValueToSICompositeConverter<Notebook>) (ins, note) -> {
            ins.setValue(MARCA, note.marca);
            ins.setValue(MEMORIA, note.memoria);
            ins.setValue(DISCO, note.disco);
            ins.setValue(SISTEMA_OPERACIONAL, note.sistemaOperacional);
        });
    }

    private static class Notebook implements Serializable {

        String marca;
        String memoria;
        String disco;
        String sistemaOperacional;

        Notebook(String marca, String memoria, String disco, String sistemaOperacional) {
            this.marca = marca;
            this.memoria = memoria;
            this.disco = disco;
            this.sistemaOperacional = sistemaOperacional;
        }

        public String getMarca() {
            return marca;
        }

        public String getMemoria() {
            return memoria;
        }

        public String getDisco() {
            return disco;
        }

        public String getSistemaOperacional() {
            return sistemaOperacional;
        }
    }

    @Test
    public void testSelection() {
        SingularDummyFormPageTester tester = new SingularDummyFormPageTester();
        tester.getDummyPage().setTypeBuilder(STypeSelectItemModalSearchTest::buildBaseType);
        tester.startDummyPage();

        Button link = (Button) tester.getAssertionsForm().findSubComponent(al -> al.getId().equals(SearchModalPanel.MODAL_TRIGGER_ID)).getTarget();
        tester.executeAjaxEvent(link, "click");

        AjaxLink ajaxLink = (AjaxLink) tester.getAssertionsForm().findSubComponent(al -> al.getId().equals("link")).getTarget();
        tester.executeAjaxEvent(ajaxLink, "click");

        AssertionsSInstance noteBookAssertion = tester.getAssertionsForm().getSubCompomentWithType(notebook).assertSInstance();
        noteBookAssertion.field(MARCA).isValueEquals("Apple");
        noteBookAssertion.field(MEMORIA).isValueEquals("4GB");
        noteBookAssertion.field(DISCO).isValueEquals("1T");
        noteBookAssertion.field(SISTEMA_OPERACIONAL).isValueEquals("OSX");
    }
}
