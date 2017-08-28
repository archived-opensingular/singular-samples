package org.opensingular.sample.studio.entity;

import org.opensingular.lib.support.persistence.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "TD_NORMA")
public class NormaEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "CO_SEQ_NORMA")
    private Integer cod;

    @Column(name = "NO_NORMA")
    private String nome;

    @Convert(converter = SimNaoConverter.class)
    @Column(name = "ST_REGISTRO_ATIVO")
    private Boolean ativo;

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