package br.net.mirante.singular.form.wicket.mapper.attachment;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.protocol.http.servlet.MultipartServletWebRequest;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.google.common.collect.Lists.*;

import br.net.mirante.singular.form.mform.MDicionario;
import br.net.mirante.singular.form.mform.core.attachment.MIAttachment;
import br.net.mirante.singular.form.wicket.hepers.TestPackage;
import br.net.mirante.singular.form.wicket.test.base.TestApp;
import br.net.mirante.singular.form.wicket.test.base.TestPage;

public class UploadBehaviourTest extends WebBehaviourBaseTest {
    private static MDicionario dicionario;
    private static TestPackage tpackage;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private UploadBehavior b;

    private MultipartServletWebRequest multipart;


    @BeforeClass
    public static void createDicionario() {
        dicionario = MDicionario.create();
        tpackage = dicionario.carregarPacote(TestPackage.class);
    }

    @Before
    public void setup() throws Exception {
        setupDriver(setupInstance());
    }

    private void setupDriver(MIAttachment instance) throws FileUploadException {
        new WicketTester(new TestApp());
        b = new UploadBehavior(instance);
        b.setWebWrapper(createWebWrapper());
        b.bind(new TestPage(null));
    }

    private MIAttachment setupInstance() {
        return tpackage.attachmentFileField.novaInstancia();
    }

    private WebWrapper createWebWrapper() throws FileUploadException {
        WebWrapper w = new WebWrapper();
        w.setRequest(mockRequest());
        multipart = mock(MultipartServletWebRequest.class);
        when(request.newMultipartWebRequest(any(Bytes.class), anyString())).thenReturn(multipart);
        w.setResponse(mockResponse());
        return w;
    }

    @Test public void rejectsNonMultipartRequests() {
        when(containerRequest.getContentType()).thenReturn("text/html");
        thrown.expect(AbortWithHttpErrorCodeException.class);
        thrown.expectMessage("Request is not Multipart as Expected");
        b.onResourceRequested();
    }

    @Test public void wicketDemandsToCallParseToWork() throws Exception {
        b.onResourceRequested();
        verify(multipart).parseFileParts();
    }

    @Test public void informsTheServiceAboutTheFileReceived() throws Exception {
        FileItem file = file("my.file.ext", new byte[] { 0 });

        when(multipart.getFile("FILE-UPLOAD")).thenReturn(newArrayList(file));

        b.onResourceRequested();

        ByteArrayOutputStream baos = (ByteArrayOutputStream) response.getOutputStream();
        JSONObject result = new JSONObject(baos.toString());
        JSONObject answer = new JSONObject();
        JSONArray expected = new JSONArray();
        JSONObject jsonFile = new JSONObject();
        jsonFile.put("name", "my.file.ext");
        jsonFile.put("fileId", "5ba93c9db0cff93f52b521d7420e43f6eda2784f");
        jsonFile.put("hashSHA1", "5ba93c9db0cff93f52b521d7420e43f6eda2784f");
        jsonFile.put("size", 1);
        expected.put(jsonFile);
        answer.put("files", expected);
        assertThat(result).isEqualsToByComparingFields(answer);
    }
    
    @Test public void afterEachUploadStoresAllTemporaryIds() throws Exception {
        MIAttachment instance = (MIAttachment) setupInstance();
        assertThat(instance.getTemporaryFileIds()).isEmpty();
        setupDriver(instance);
        
        FileItem f1 = file("my.file.ext", new byte[] { 0 });
        when(multipart.getFile("FILE-UPLOAD")).thenReturn(newArrayList(f1));

        b.onResourceRequested();
        
        FileItem f2 = file("other.file.ext", new byte[] { 1 });
        when(multipart.getFile("FILE-UPLOAD")).thenReturn(newArrayList(f2));

        b.onResourceRequested();
        
        assertThat(instance.getTemporaryFileIds())
            .containsOnly("5ba93c9db0cff93f52b521d7420e43f6eda2784f",
                            "bf8b4530d8d246dd74ac53a13471bba17941dff7");
    }

    private FileItem file(String fileName, byte[] content) throws IOException {
        FileItem file = mock(FileItem.class);
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream(content));
        when(file.getName()).thenReturn(fileName);
        return file;
    }

}
