
package com.opensingular.ws.requirement;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="dadosPessoais">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence minOccurs="0">
 *                   &lt;element name="nomeCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nomeMae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="nomePai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="telefone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="documentos">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence minOccurs="0">
 *                             &lt;element name="documentoList">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="documento" maxOccurs="10">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence minOccurs="0">
 *                                                 &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="naoTenhoFotoCachorro" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="fotoDoCachorro">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence minOccurs="0">
 *                             &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="documentacaoComprobatoria">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence minOccurs="0">
 *                             &lt;element name="documentoList">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="documento" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence minOccurs="0">
 *                                                 &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="brasileiro" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="listEnderecos">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence minOccurs="0">
 *                             &lt;element name="EnderecoCompletoList">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="EnderecoCompleto" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence minOccurs="0">
 *                                                 &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="logradouro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="estado">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence minOccurs="0">
 *                                                           &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                           &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "dadosPessoais"
})
@XmlRootElement(name = "Requirementsample")
public class Requirementsample {

    protected Requirementsample.DadosPessoais dadosPessoais;

    /**
     * Obtém o valor da propriedade dadosPessoais.
     * 
     * @return
     *     possible object is
     *     {@link Requirementsample.DadosPessoais }
     *     
     */
    public Requirementsample.DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    /**
     * Define o valor da propriedade dadosPessoais.
     * 
     * @param value
     *     allowed object is
     *     {@link Requirementsample.DadosPessoais }
     *     
     */
    public void setDadosPessoais(Requirementsample.DadosPessoais value) {
        this.dadosPessoais = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence minOccurs="0">
     *         &lt;element name="nomeCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nomeMae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="nomePai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="telefone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="documentos">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence minOccurs="0">
     *                   &lt;element name="documentoList">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="documento" maxOccurs="10">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence minOccurs="0">
     *                                       &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="naoTenhoFotoCachorro" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="fotoDoCachorro">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence minOccurs="0">
     *                   &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="documentacaoComprobatoria">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence minOccurs="0">
     *                   &lt;element name="documentoList">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="documento" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence minOccurs="0">
     *                                       &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="brasileiro" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="listEnderecos">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence minOccurs="0">
     *                   &lt;element name="EnderecoCompletoList">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="EnderecoCompleto" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence minOccurs="0">
     *                                       &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="logradouro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="estado">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence minOccurs="0">
     *                                                 &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                                 &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "nomeCompleto",
        "nomeMae",
        "nomePai",
        "telefone",
        "documentos",
        "naoTenhoFotoCachorro",
        "fotoDoCachorro",
        "documentacaoComprobatoria",
        "brasileiro",
        "listEnderecos"
    })
    public static class DadosPessoais {

        protected String nomeCompleto;
        protected String nomeMae;
        protected String nomePai;
        protected String telefone;
        protected Requirementsample.DadosPessoais.Documentos documentos;
        protected Boolean naoTenhoFotoCachorro;
        protected Requirementsample.DadosPessoais.FotoDoCachorro fotoDoCachorro;
        protected Requirementsample.DadosPessoais.DocumentacaoComprobatoria documentacaoComprobatoria;
        protected Boolean brasileiro;
        protected Requirementsample.DadosPessoais.ListEnderecos listEnderecos;

        /**
         * Obtém o valor da propriedade nomeCompleto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNomeCompleto() {
            return nomeCompleto;
        }

        /**
         * Define o valor da propriedade nomeCompleto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNomeCompleto(String value) {
            this.nomeCompleto = value;
        }

        /**
         * Obtém o valor da propriedade nomeMae.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNomeMae() {
            return nomeMae;
        }

        /**
         * Define o valor da propriedade nomeMae.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNomeMae(String value) {
            this.nomeMae = value;
        }

        /**
         * Obtém o valor da propriedade nomePai.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNomePai() {
            return nomePai;
        }

        /**
         * Define o valor da propriedade nomePai.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNomePai(String value) {
            this.nomePai = value;
        }

        /**
         * Obtém o valor da propriedade telefone.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTelefone() {
            return telefone;
        }

        /**
         * Define o valor da propriedade telefone.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTelefone(String value) {
            this.telefone = value;
        }

        /**
         * Obtém o valor da propriedade documentos.
         * 
         * @return
         *     possible object is
         *     {@link Requirementsample.DadosPessoais.Documentos }
         *     
         */
        public Requirementsample.DadosPessoais.Documentos getDocumentos() {
            return documentos;
        }

        /**
         * Define o valor da propriedade documentos.
         * 
         * @param value
         *     allowed object is
         *     {@link Requirementsample.DadosPessoais.Documentos }
         *     
         */
        public void setDocumentos(Requirementsample.DadosPessoais.Documentos value) {
            this.documentos = value;
        }

        /**
         * Obtém o valor da propriedade naoTenhoFotoCachorro.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isNaoTenhoFotoCachorro() {
            return naoTenhoFotoCachorro;
        }

        /**
         * Define o valor da propriedade naoTenhoFotoCachorro.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setNaoTenhoFotoCachorro(Boolean value) {
            this.naoTenhoFotoCachorro = value;
        }

        /**
         * Obtém o valor da propriedade fotoDoCachorro.
         * 
         * @return
         *     possible object is
         *     {@link Requirementsample.DadosPessoais.FotoDoCachorro }
         *     
         */
        public Requirementsample.DadosPessoais.FotoDoCachorro getFotoDoCachorro() {
            return fotoDoCachorro;
        }

        /**
         * Define o valor da propriedade fotoDoCachorro.
         * 
         * @param value
         *     allowed object is
         *     {@link Requirementsample.DadosPessoais.FotoDoCachorro }
         *     
         */
        public void setFotoDoCachorro(Requirementsample.DadosPessoais.FotoDoCachorro value) {
            this.fotoDoCachorro = value;
        }

        /**
         * Obtém o valor da propriedade documentacaoComprobatoria.
         * 
         * @return
         *     possible object is
         *     {@link Requirementsample.DadosPessoais.DocumentacaoComprobatoria }
         *     
         */
        public Requirementsample.DadosPessoais.DocumentacaoComprobatoria getDocumentacaoComprobatoria() {
            return documentacaoComprobatoria;
        }

        /**
         * Define o valor da propriedade documentacaoComprobatoria.
         * 
         * @param value
         *     allowed object is
         *     {@link Requirementsample.DadosPessoais.DocumentacaoComprobatoria }
         *     
         */
        public void setDocumentacaoComprobatoria(Requirementsample.DadosPessoais.DocumentacaoComprobatoria value) {
            this.documentacaoComprobatoria = value;
        }

        /**
         * Obtém o valor da propriedade brasileiro.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isBrasileiro() {
            return brasileiro;
        }

        /**
         * Define o valor da propriedade brasileiro.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setBrasileiro(Boolean value) {
            this.brasileiro = value;
        }

        /**
         * Obtém o valor da propriedade listEnderecos.
         * 
         * @return
         *     possible object is
         *     {@link Requirementsample.DadosPessoais.ListEnderecos }
         *     
         */
        public Requirementsample.DadosPessoais.ListEnderecos getListEnderecos() {
            return listEnderecos;
        }

        /**
         * Define o valor da propriedade listEnderecos.
         * 
         * @param value
         *     allowed object is
         *     {@link Requirementsample.DadosPessoais.ListEnderecos }
         *     
         */
        public void setListEnderecos(Requirementsample.DadosPessoais.ListEnderecos value) {
            this.listEnderecos = value;
        }


        /**
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence minOccurs="0">
         *         &lt;element name="documentoList">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="documento" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence minOccurs="0">
         *                             &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "documentoList"
        })
        public static class DocumentacaoComprobatoria {

            protected Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList documentoList;

            /**
             * Obtém o valor da propriedade documentoList.
             * 
             * @return
             *     possible object is
             *     {@link Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList }
             *     
             */
            public Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList getDocumentoList() {
                return documentoList;
            }

            /**
             * Define o valor da propriedade documentoList.
             * 
             * @param value
             *     allowed object is
             *     {@link Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList }
             *     
             */
            public void setDocumentoList(Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList value) {
                this.documentoList = value;
            }


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
             *         &lt;element name="documento" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence minOccurs="0">
             *                   &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
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
                "documento"
            })
            public static class DocumentoList {

                @XmlElement(required = true)
                protected List<Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList.Documento> documento;

                /**
                 * Gets the value of the documento property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the documento property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getDocumento().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList.Documento }
                 * 
                 * 
                 */
                public List<Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList.Documento> getDocumento() {
                    if (documento == null) {
                        documento = new ArrayList<Requirementsample.DadosPessoais.DocumentacaoComprobatoria.DocumentoList.Documento>();
                    }
                    return this.documento;
                }


                /**
                 * <p>Classe Java de anonymous complex type.
                 * 
                 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence minOccurs="0">
                 *         &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
                    "fileId",
                    "name",
                    "hashSHA1",
                    "fileSize"
                })
                public static class Documento {

                    protected String fileId;
                    protected String name;
                    protected String hashSHA1;
                    protected BigInteger fileSize;

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
                     * Obtém o valor da propriedade fileSize.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigInteger }
                     *     
                     */
                    public BigInteger getFileSize() {
                        return fileSize;
                    }

                    /**
                     * Define o valor da propriedade fileSize.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigInteger }
                     *     
                     */
                    public void setFileSize(BigInteger value) {
                        this.fileSize = value;
                    }

                }

            }

        }


        /**
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence minOccurs="0">
         *         &lt;element name="documentoList">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="documento" maxOccurs="10">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence minOccurs="0">
         *                             &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "documentoList"
        })
        public static class Documentos {

            protected Requirementsample.DadosPessoais.Documentos.DocumentoList documentoList;

            /**
             * Obtém o valor da propriedade documentoList.
             * 
             * @return
             *     possible object is
             *     {@link Requirementsample.DadosPessoais.Documentos.DocumentoList }
             *     
             */
            public Requirementsample.DadosPessoais.Documentos.DocumentoList getDocumentoList() {
                return documentoList;
            }

            /**
             * Define o valor da propriedade documentoList.
             * 
             * @param value
             *     allowed object is
             *     {@link Requirementsample.DadosPessoais.Documentos.DocumentoList }
             *     
             */
            public void setDocumentoList(Requirementsample.DadosPessoais.Documentos.DocumentoList value) {
                this.documentoList = value;
            }


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
             *         &lt;element name="documento" maxOccurs="10">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence minOccurs="0">
             *                   &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
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
                "documento"
            })
            public static class DocumentoList {

                @XmlElement(required = true)
                protected List<Requirementsample.DadosPessoais.Documentos.DocumentoList.Documento> documento;

                /**
                 * Gets the value of the documento property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the documento property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getDocumento().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Requirementsample.DadosPessoais.Documentos.DocumentoList.Documento }
                 * 
                 * 
                 */
                public List<Requirementsample.DadosPessoais.Documentos.DocumentoList.Documento> getDocumento() {
                    if (documento == null) {
                        documento = new ArrayList<Requirementsample.DadosPessoais.Documentos.DocumentoList.Documento>();
                    }
                    return this.documento;
                }


                /**
                 * <p>Classe Java de anonymous complex type.
                 * 
                 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence minOccurs="0">
                 *         &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
                    "fileId",
                    "name",
                    "hashSHA1",
                    "fileSize"
                })
                public static class Documento {

                    protected String fileId;
                    protected String name;
                    protected String hashSHA1;
                    protected BigInteger fileSize;

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
                     * Obtém o valor da propriedade fileSize.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigInteger }
                     *     
                     */
                    public BigInteger getFileSize() {
                        return fileSize;
                    }

                    /**
                     * Define o valor da propriedade fileSize.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigInteger }
                     *     
                     */
                    public void setFileSize(BigInteger value) {
                        this.fileSize = value;
                    }

                }

            }

        }


        /**
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence minOccurs="0">
         *         &lt;element name="fileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="hashSHA1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
            "fileId",
            "name",
            "hashSHA1",
            "fileSize"
        })
        public static class FotoDoCachorro {

            protected String fileId;
            protected String name;
            protected String hashSHA1;
            protected BigInteger fileSize;

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
             * Obtém o valor da propriedade fileSize.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getFileSize() {
                return fileSize;
            }

            /**
             * Define o valor da propriedade fileSize.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setFileSize(BigInteger value) {
                this.fileSize = value;
            }

        }


        /**
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence minOccurs="0">
         *         &lt;element name="EnderecoCompletoList">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="EnderecoCompleto" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence minOccurs="0">
         *                             &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="logradouro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="estado">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence minOccurs="0">
         *                                       &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                                       &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "enderecoCompletoList"
        })
        public static class ListEnderecos {

            @XmlElement(name = "EnderecoCompletoList")
            protected Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList enderecoCompletoList;

            /**
             * Obtém o valor da propriedade enderecoCompletoList.
             * 
             * @return
             *     possible object is
             *     {@link Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList }
             *     
             */
            public Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList getEnderecoCompletoList() {
                return enderecoCompletoList;
            }

            /**
             * Define o valor da propriedade enderecoCompletoList.
             * 
             * @param value
             *     allowed object is
             *     {@link Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList }
             *     
             */
            public void setEnderecoCompletoList(Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList value) {
                this.enderecoCompletoList = value;
            }


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
             *         &lt;element name="EnderecoCompleto" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence minOccurs="0">
             *                   &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="logradouro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="estado">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence minOccurs="0">
             *                             &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                             &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
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
                "enderecoCompleto"
            })
            public static class EnderecoCompletoList {

                @XmlElement(name = "EnderecoCompleto", required = true)
                protected List<Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto> enderecoCompleto;

                /**
                 * Gets the value of the enderecoCompleto property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the enderecoCompleto property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getEnderecoCompleto().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto }
                 * 
                 * 
                 */
                public List<Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto> getEnderecoCompleto() {
                    if (enderecoCompleto == null) {
                        enderecoCompleto = new ArrayList<Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto>();
                    }
                    return this.enderecoCompleto;
                }


                /**
                 * <p>Classe Java de anonymous complex type.
                 * 
                 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence minOccurs="0">
                 *         &lt;element name="cep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="logradouro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="bairro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="estado">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence minOccurs="0">
                 *                   &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                   &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                    "cep",
                    "logradouro",
                    "numero",
                    "complemento",
                    "bairro",
                    "cidade",
                    "estado",
                    "pais"
                })
                public static class EnderecoCompleto {

                    protected String cep;
                    protected String logradouro;
                    protected String numero;
                    protected String complemento;
                    protected String bairro;
                    protected String cidade;
                    protected Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto.Estado estado;
                    protected String pais;

                    /**
                     * Obtém o valor da propriedade cep.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCep() {
                        return cep;
                    }

                    /**
                     * Define o valor da propriedade cep.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCep(String value) {
                        this.cep = value;
                    }

                    /**
                     * Obtém o valor da propriedade logradouro.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLogradouro() {
                        return logradouro;
                    }

                    /**
                     * Define o valor da propriedade logradouro.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLogradouro(String value) {
                        this.logradouro = value;
                    }

                    /**
                     * Obtém o valor da propriedade numero.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNumero() {
                        return numero;
                    }

                    /**
                     * Define o valor da propriedade numero.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNumero(String value) {
                        this.numero = value;
                    }

                    /**
                     * Obtém o valor da propriedade complemento.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getComplemento() {
                        return complemento;
                    }

                    /**
                     * Define o valor da propriedade complemento.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setComplemento(String value) {
                        this.complemento = value;
                    }

                    /**
                     * Obtém o valor da propriedade bairro.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getBairro() {
                        return bairro;
                    }

                    /**
                     * Define o valor da propriedade bairro.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setBairro(String value) {
                        this.bairro = value;
                    }

                    /**
                     * Obtém o valor da propriedade cidade.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCidade() {
                        return cidade;
                    }

                    /**
                     * Define o valor da propriedade cidade.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCidade(String value) {
                        this.cidade = value;
                    }

                    /**
                     * Obtém o valor da propriedade estado.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto.Estado }
                     *     
                     */
                    public Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto.Estado getEstado() {
                        return estado;
                    }

                    /**
                     * Define o valor da propriedade estado.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto.Estado }
                     *     
                     */
                    public void setEstado(Requirementsample.DadosPessoais.ListEnderecos.EnderecoCompletoList.EnderecoCompleto.Estado value) {
                        this.estado = value;
                    }

                    /**
                     * Obtém o valor da propriedade pais.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPais() {
                        return pais;
                    }

                    /**
                     * Define o valor da propriedade pais.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPais(String value) {
                        this.pais = value;
                    }


                    /**
                     * <p>Classe Java de anonymous complex type.
                     * 
                     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence minOccurs="0">
                     *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                     *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                        "sigla",
                        "nome"
                    })
                    public static class Estado {

                        protected String sigla;
                        protected String nome;

                        /**
                         * Obtém o valor da propriedade sigla.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getSigla() {
                            return sigla;
                        }

                        /**
                         * Define o valor da propriedade sigla.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setSigla(String value) {
                            this.sigla = value;
                        }

                        /**
                         * Obtém o valor da propriedade nome.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getNome() {
                            return nome;
                        }

                        /**
                         * Define o valor da propriedade nome.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setNome(String value) {
                            this.nome = value;
                        }

                    }

                }

            }

        }

    }

}
