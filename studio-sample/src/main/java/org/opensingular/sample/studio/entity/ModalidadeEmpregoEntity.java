package org.opensingular.sample.studio.entity;

import org.opensingular.lib.support.persistence.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TD_MODALIDADE_EMPREGO")
public class ModalidadeEmpregoEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "CO_SEQ_MODALIDADE_EMPREGO")
    public Integer cod;

    @Column(name = "NO_MODALIDADE_EMPREGO")
    public String nome;

    @Column(name = "ST_REGISTRO_ATIVO")
    public Boolean ativo;

    @Override
    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}