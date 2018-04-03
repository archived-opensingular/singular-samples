package org.opensingular.singular.form.showcase.component.form.core.search;

import org.opensingular.form.wicket.mapper.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Processo implements TreeNode<Processo> {

    private Long numeroProcesso;
    private String descricao;
    private List<Processo> processosFilhos = new LinkedList<>();
    private Processo processoPai;

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

    @Override
    public Long getId() {
        return numeroProcesso;
    }

    @Override
    public String getDisplayLabel() {
        return descricao;
    }

    @Override
    public Processo getParent() {
        return processoPai;
    }

    @Override
    public void setParent(Processo parent) {
        this.processoPai = parent;
    }

    @Override
    public List<Processo> getChildrens() {
        return processosFilhos;
    }

}
