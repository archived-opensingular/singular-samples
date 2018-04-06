package org.opensingular.singular.form.showcase.component.form.core.search;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Processo implements Serializable {

    private Long id;
    private String nome;
    private List<Processo> subProcessos = new LinkedList<>();

    public Processo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Processo> getSubProcessos() {
        return subProcessos;
    }

    public void setSubProcessos(List<Processo> subProcesso) {
        this.subProcessos = subProcesso;
    }

    public void addSubProcesso(Processo processo) {
        subProcessos.add(processo);
    }
}
