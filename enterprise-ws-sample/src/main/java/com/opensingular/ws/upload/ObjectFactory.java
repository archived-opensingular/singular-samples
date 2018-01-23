
package com.opensingular.ws.upload;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.opensingular.ws.upload package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FileUpload_QNAME = new QName("opensingular.com", "fileUpload");
    private final static QName _FileUploadResponse_QNAME = new QName("opensingular.com", "fileUploadResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.opensingular.ws.upload
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FileUploadResponse }
     * 
     */
    public FileUploadResponse createFileUploadResponse() {
        return new FileUploadResponse();
    }

    /**
     * Create an instance of {@link FileUpload }
     * 
     */
    public FileUpload createFileUpload() {
        return new FileUpload();
    }

    /**
     * Create an instance of {@link AttachmentResponse }
     * 
     */
    public AttachmentResponse createAttachmentResponse() {
        return new AttachmentResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileUpload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "opensingular.com", name = "fileUpload")
    public JAXBElement<FileUpload> createFileUpload(FileUpload value) {
        return new JAXBElement<FileUpload>(_FileUpload_QNAME, FileUpload.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileUploadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "opensingular.com", name = "fileUploadResponse")
    public JAXBElement<FileUploadResponse> createFileUploadResponse(FileUploadResponse value) {
        return new JAXBElement<FileUploadResponse>(_FileUploadResponse_QNAME, FileUploadResponse.class, null, value);
    }

}
