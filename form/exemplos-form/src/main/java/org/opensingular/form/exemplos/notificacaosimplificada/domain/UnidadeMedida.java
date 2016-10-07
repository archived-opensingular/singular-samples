package org.opensingular.form.exemplos.notificacaosimplificada.domain;

// Generated 16/03/2010 08:00:26

import org.opensingular.lib.support.persistence.enums.SimNao;
import org.opensingular.form.exemplos.notificacaosimplificada.domain.generic.VocabularioControlado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * UnidadeMedida generated by Vinicius Uriel
 */
@XmlRootElement(name = "unidade-medida", namespace = "http://www.anvisa.gov.br/reg-med/schema/domains")
@XmlType(name = "unidade-medida", namespace = "http://www.anvisa.gov.br/reg-med/schema/domains")
@Entity
@Table(name = "TB_UNIDADE_MEDIDA_MEDICAMENTO", schema = "DBMEDICAMENTO"
        , uniqueConstraints = @UniqueConstraint(columnNames = "SG_UNIDADE_MEDIDA_MEDICAMENTO"))
@PrimaryKeyJoinColumn(name = "CO_UNIDADE_MEDIDA_MEDICAMENTO", referencedColumnName = "CO_SEQ_VOCABULARIO_CONTROLADO")
@NamedQueries({
        @NamedQuery(name = "UnidadeMedida.findAll", query = "Select unidadeMedida From UnidadeMedida as unidadeMedida where unidadeMedida.ativa = 'S'  Order by unidadeMedida.descricao  ")})
public class UnidadeMedida extends VocabularioControlado {

    private static final long serialVersionUID = 8565541983395826765L;

    @Column(name = "SG_UNIDADE_MEDIDA_MEDICAMENTO", unique = true, nullable = false, length = 15)
    private String sigla;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CO_TIPO_UNIDADE_MEDIDA")
    private TipoMedida tipo;

    public UnidadeMedida() {
    }

    public UnidadeMedida(Long id, String descricao, String sigla, TipoMedida tipo, SimNao ativa) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
        this.tipo = tipo;
        this.ativa = ativa;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoMedida getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoMedida tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hashCode = Integer.MIN_VALUE;
        if (this.getId() != null) {
            hashCode += this.getId();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof UnidadeMedida) {
                UnidadeMedida um = (UnidadeMedida) obj;
                if (um.getId() != null) {
                    return um.getId().equals(this.getId());
                }
            }
        }
        return false;
    }
}
