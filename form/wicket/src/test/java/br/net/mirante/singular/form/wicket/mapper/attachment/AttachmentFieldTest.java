package br.net.mirante.singular.form.wicket.mapper.attachment;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.net.mirante.singular.form.mform.MDicionario;
import br.net.mirante.singular.form.mform.MISimples;
import br.net.mirante.singular.form.wicket.hepers.TestPackage;
import br.net.mirante.singular.form.wicket.test.base.TestApp;
import br.net.mirante.singular.form.wicket.test.base.TestPage;

@SuppressWarnings("rawtypes")
public class AttachmentFieldTest {

    private static MDicionario dicionario;
    private static TestPackage pacote;
    private WicketTester driver;
    private TestPage page;
    private FormTester form;

    //TODO: Document the classes for testing and for upload
    
    @BeforeClass
    public static void createDicionario() {
	dicionario = MDicionario.create();
	pacote = dicionario.carregarPacote(TestPackage.class);
    }
    
    @Before public void setupPage(){
	driver = new WicketTester(new TestApp());
	page = new TestPage(null);
	page.setDicionario(dicionario);
	page.setNewInstanceOfType(TestPackage.TIPO_ATTACHMENT);
	page.build();
	driver.startPage(page);
    }
    
    @Before public void setupFormAssessor(){
	form = driver.newFormTester("test-form", false);
    }

    @Test public void generatesFieldsResposibleForCompositeParts() {
	driver.assertEnabled(formField(form, "file_name_fileField"));
	driver.assertEnabled(formField(form,  "file_hash_fileField"));
	driver.assertEnabled(formField(form, "file_size_fileField"));
	driver.assertEnabled(formField(form, "file_id_fileField"));
    }

    private String formField(FormTester form, String leafName) {
	return "test-form:"+findId(form.getForm(), leafName).get();
    }
    
    @SuppressWarnings("unchecked")
    @Test public void onSubmissionItPopulatesTheFieldsOfTheAttachmentComposite(){
	form.setValue(findId(form.getForm(), "file_name_fileField").get(), "abacate.png");
	form.setValue(findId(form.getForm(), "file_hash_fileField").get(), "1234567890asdfghj");
	form.setValue(findId(form.getForm(), "file_size_fileField").get(), "1234");
	form.setValue(findId(form.getForm(), "file_id_fileField").get(), "1020304050");
	form.submit("save-btn");
	
	String attachmentName = pacote.attachmentFileField.getNomeSimples();
	List<MISimples> values = (List) page.getCurrentInstance().getValor(attachmentName);
	assertThat(findValueInList(values, "name")).isEqualTo("abacate.png");
	assertThat(findValueInList(values, "hashSHA1")).isEqualTo("1234567890asdfghj");
	assertThat(findValueInList(values, "size")).isEqualTo(1234);
	assertThat(findValueInList(values, "fileId")).isEqualTo("1020304050");
    }
    
    @Test public void componentMustHaveAUploadBehaviourWhichReflectsTheUploadUrl(){
	Component attachmentComponent = page.get(formField(form, "_attachment_fileField"));
	List<UploadBehavior> behaviours = attachmentComponent.getBehaviors(UploadBehavior.class);
	assertThat(behaviours).hasSize(1);
    }
    
    private Object findValueInList(List<MISimples> list, String propName){
	for(MISimples m : list){
	    if(m.getNome().equals(propName)) return m.getValor();
	}
	return null;
    }

    public Optional<String> findId(MarkupContainer container, String leafName) {
	Iterator<Component> it = container.iterator();
	while(it.hasNext()){
	    Optional<String> found = findInComponent(leafName, it.next());
	    if(found.isPresent()){ return found; }
	}
	return Optional.empty();
    }

    private Optional<String> findInComponent(String leafName, Component c) {
	if (c.getId().equals(leafName)) {
	    return Optional.of(leafName);
	} else if (c instanceof MarkupContainer) {
	    Optional<String> found = findId((MarkupContainer) c, leafName);
	    if (found.isPresent()) {
		return Optional.of(c.getId() + ":" + found.get());
	    }
	}
	return Optional.empty();
    }

}