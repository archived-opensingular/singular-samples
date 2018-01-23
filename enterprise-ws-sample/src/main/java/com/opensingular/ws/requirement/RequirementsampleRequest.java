
package com.opensingular.ws.requirement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{com.opensingular}Requirementsample" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "requirementsample"
})
@XmlRootElement(name = "RequirementsampleRequest")
public class RequirementsampleRequest {

    protected String id;
    @XmlElement(name = "Requirementsample", namespace = "com.opensingular")
    protected Requirementsample requirementsample;

    /**
     * Obtém o valor da propriedade id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade requirementsample.
     * 
     * @return
     *     possible object is
     *     {@link Requirementsample }
     *     
     */
    public Requirementsample getRequirementsample() {
        return requirementsample;
    }

    /**
     * Define o valor da propriedade requirementsample.
     * 
     * @param value
     *     allowed object is
     *     {@link Requirementsample }
     *     
     */
    public void setRequirementsample(Requirementsample value) {
        this.requirementsample = value;
    }

}
