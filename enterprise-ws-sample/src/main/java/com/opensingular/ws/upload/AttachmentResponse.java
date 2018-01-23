
package com.opensingular.ws.upload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de attachmentResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="attachmentResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attachmentResponse", propOrder = {
    "fileId",
    "fileSize",
    "hashSHA1",
    "name"
})
public class AttachmentResponse {

    protected String fileId;
    protected long fileSize;
    protected String hashSHA1;
    protected String name;

    /**
     * Obtém o valor da propriedade fileId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Define o valor da propriedade fileId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileId(String value) {
        this.fileId = value;
    }

    /**
     * Obtém o valor da propriedade fileSize.
     * 
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Define o valor da propriedade fileSize.
     * 
     */
    public void setFileSize(long value) {
        this.fileSize = value;
    }

    /**
     * Obtém o valor da propriedade hashSHA1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashSHA1() {
        return hashSHA1;
    }

    /**
     * Define o valor da propriedade hashSHA1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashSHA1(String value) {
        this.hashSHA1 = value;
    }

    /**
     * Obtém o valor da propriedade name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define o valor da propriedade name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
