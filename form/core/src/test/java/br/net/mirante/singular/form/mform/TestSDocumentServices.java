package br.net.mirante.singular.form.mform;

import static org.fest.assertions.api.Assertions.anyOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.MathContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import br.net.mirante.singular.form.mform.core.attachment.IAttachmentPersistenceHandler;
import br.net.mirante.singular.form.mform.core.attachment.IAttachmentRef;
import br.net.mirante.singular.form.mform.core.attachment.MIAttachment;
import br.net.mirante.singular.form.mform.core.attachment.MTipoAttachment;

public class TestSDocumentServices {
    
    private MTipoComposto<?> groupingType;
    private MIAttachment fileFieldInstance;
    private SDocument document;
    private IAttachmentPersistenceHandler tempHandler, persistentHandler;

    @Before public void setup(){
        MDicionario dicionario = MDicionario.create();
        createTypes(dicionario.criarNovoPacote("teste"));
        createInstances();
        setupServices();
        
    }

    private void createTypes(PacoteBuilder pb) {
        groupingType = pb.createTipoComposto("Grouping");
        groupingType.addCampo("anexo", MTipoAttachment.class);
        groupingType.addCampoInteger("justIgnoreThis");
    }
    
    private void createInstances() {
        MIComposto instance = (MIComposto) groupingType.novaInstancia();
        fileFieldInstance = (MIAttachment) instance.getAllChildren().iterator().next();
    }
    
    private void setupServices() {
        document = fileFieldInstance.getDocument();
        
        tempHandler = mock(IAttachmentPersistenceHandler.class);
        persistentHandler = mock(IAttachmentPersistenceHandler.class);
        document.setAttachmentPersistenceHandler(serviceRef(tempHandler));
        document.bindLocalService("filePersistence", serviceRef(persistentHandler));
    }
    
    @Test public void deveMigrarOsAnexosParaAPersistencia(){
        fileFieldInstance.setFileId("abacate");
        
        byte[] content = new byte[]{0};
        
        when(tempHandler.getAttachment("abacate"))
            .thenReturn(attachmentRef("abacate", content));
        
        document.persistFiles();
        verify(persistentHandler).addAttachment(content);
    }
    
    @Test public void deveApagarOTemporarioAposInserirNoPersistente(){
        fileFieldInstance.setFileId("abacate");
        
        byte[] content = new byte[]{0};
        
        when(tempHandler.getAttachment("abacate"))
            .thenReturn(attachmentRef("abacate", content));
        
        document.persistFiles();
        verify(tempHandler).deleteAttachment("abacate");
    }
    
    @Test public void deveApagarTodosOsTemporarios(){
        fileFieldInstance.setFileId("abacate");
        fileFieldInstance.addTemporaryFileId("temp1");
        fileFieldInstance.addTemporaryFileId("temp2");
        
        byte[] content = new byte[]{0};
        
        when(tempHandler.getAttachment("abacate"))
            .thenReturn(attachmentRef("abacate", content));
        
        document.persistFiles();
        verify(tempHandler).deleteAttachment("abacate");
        verify(tempHandler).deleteAttachment("temp1");
        verify(tempHandler).deleteAttachment("temp2");
    }
    
    @Test public void deveApagarOPersistenteSeEsteSeAlterou(){
        fileFieldInstance.setFileId("abacate");
        fileFieldInstance.setOriginalFileId("avocado");
        
        byte[] content = new byte[]{0};
        
        when(tempHandler.getAttachment("abacate"))
            .thenReturn(attachmentRef("abacate", content));
        
        document.persistFiles();
        verify(persistentHandler).deleteAttachment("avocado");
    }
    
    @Test public void naoApagaNadaSeNenhumArquivoFoiAlterado(){
        fileFieldInstance.setFileId("abacate");
        fileFieldInstance.setOriginalFileId("abacate");
        
        byte[] content = new byte[]{0};
        
        when(tempHandler.getAttachment("abacate"))
            .thenReturn(attachmentRef("abacate", content));
        
        document.persistFiles();
        verify(persistentHandler, never()).deleteAttachment(Matchers.any());
        verify(tempHandler, never()).deleteAttachment(Matchers.any());
    }
    
    @SuppressWarnings("serial")
    private ServiceRef<IAttachmentPersistenceHandler> serviceRef(IAttachmentPersistenceHandler handler) {
        return new ServiceRef<IAttachmentPersistenceHandler>() {
            public IAttachmentPersistenceHandler get() {
                return handler;
            }
        };
    }

    private IAttachmentRef attachmentRef(String hash, byte[] content) {
        return new IAttachmentRef() {
            
            public String getId() {
                return hash;
            }
            
            public Integer getSize() {
                return content.length;
            }
            
            public String getHashSHA1() {
                return hash;
            }
            
            public InputStream getContent() {
                return new ByteArrayInputStream(content);
            }
        };
    }
}
