package org.opensingular.singular.form.showcase.component.form.core.search;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Processo implements Serializable {

    private Long numeroProcesso;
    private String descricao;
    private List<Processo> processosFilhos = new LinkedList<>();

    Processo(Long numeroProcesso, String descricao) {
        this.numeroProcesso = numeroProcesso;
        this.descricao = descricao;
    }

    public Long getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(Long numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Processo> getProcessosFilhos() {
        return processosFilhos;
    }

    public void setProcessosFilhos(List<Processo> processosFilhos) {
        this.processosFilhos = processosFilhos;
    }

    public void addFilho(Processo processo) {
        processosFilhos.add(processo);
    }
}
