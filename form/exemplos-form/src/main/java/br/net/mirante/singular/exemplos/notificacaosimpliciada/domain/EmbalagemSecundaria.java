package br.net.mirante.singular.exemplos.notificacaosimpliciada.domain;
// Generated 16/03/2010 08:00:26 by Mirante 3.2.2.GA


import br.net.mirante.singular.exemplos.notificacaosimpliciada.domain.enums.SimNao;
import br.net.mirante.singular.exemplos.notificacaosimpliciada.domain.generic.VocabularioControlado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * EmbalagemSecundaria generated by Vinicius Uriel
 */

@XmlRootElement(name = "embalagem-secundaria", namespace = "http://www.anvisa.gov.br/reg-med/schema/domains")
@XmlType(name = "embalagem-secundaria", namespace = "http://www.anvisa.gov.br/reg-med/schema/domains")
@Entity
@Table(name = "TB_EMBALAGEM_SECUNDARIA", schema = "DBMEDICAMENTO")
@PrimaryKeyJoinColumn(name = "CO_EMBALAGEM_SECUNDARIA", referencedColumnName = "CO_SEQ_VOCABULARIO_CONTROLADO")
@NamedQueries({
        @NamedQuery(name = "EmbalagemSecundaria.findAll", query = "Select embalagemSecundaria From EmbalagemSecundaria as embalagemSecundaria where embalagemSecundaria.ativa = 'S'  Order by embalagemSecundaria.descricao  ")})
public class EmbalagemSecundaria extends VocabularioControlado {

    private static final long serialVersionUID = 7152980386718195660L;

    @Column(name = "SG_EMBALAGEM_SECUNDARIA", unique = true, nullable = false, length = 15)
    private String sigla;

    @Column(name = "DS_CONCEITO", length = 400)
    private String conceito;

    public EmbalagemSecundaria() {
    }

    public EmbalagemSecundaria(Long id, String descricao, String sigla, SimNao ativa) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
        this.ativa = ativa;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getConceito() {
        return this.conceito;
    }

    public void setConceito(String conceito) {
        this.conceito = conceito;
    }


}
