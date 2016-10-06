/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.opensingular.bam.dto;

import java.util.Date;

import org.opensingular.singular.flow.core.dto.IInstanceDTO;
import org.opensingular.singular.commons.util.FormatUtil;


public class InstanceDTO implements IInstanceDTO {

    private Integer cod;
    private String descricao;
    private Long delta;
    private Date dataInicial;
    private Long deltaAtividade;
    private Date dataAtividade;
    private String usuarioAlocado;

    public InstanceDTO() {
    }
    
    public InstanceDTO(Integer cod, String descricao, Long delta, Date dataInicial,
            Long deltaAtividade, Date dataAtividade, String usuarioAlocado) {
        this.cod = cod;
        this.descricao = descricao;
        this.delta = delta;
        this.dataInicial = dataInicial;
        this.deltaAtividade = deltaAtividade;
        this.dataAtividade = dataAtividade;
        this.usuarioAlocado = usuarioAlocado;
    }

    @Override
    public Integer getCod() {
        return cod;
    }

    @Override
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public Long getDelta() {
        return delta;
    }

    @Override
    public String getDeltaString() {
        StringBuilder tempo = new StringBuilder("");
        if (this.delta != null) {
            FormatUtil.appendSeconds(tempo, this.delta);
        }
        return tempo.toString();
    }

    @Override
    public void setDelta(Long delta) {
        this.delta = delta;
    }

    @Override
    public Date getDataInicial() {
        return dataInicial;
    }

    @Override
    public String getDataInicialString() {
        return FormatUtil.dateToDefaultTimestampString(dataInicial);
    }

    @Override
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    @Override
    public Long getDeltaAtividade() {
        return deltaAtividade;
    }

    @Override
    public String getDeltaAtividadeString() {
        StringBuilder tempo = new StringBuilder("");
        if (this.deltaAtividade != null) {
            FormatUtil.appendSeconds(tempo, this.deltaAtividade);
        }
        return tempo.toString();
    }

    @Override
    public void setDeltaAtividade(Long deltaAtividade) {
        this.deltaAtividade = deltaAtividade;
    }

    @Override
    public Date getDataAtividade() {
        return dataAtividade;
    }

    @Override
    public String getDataAtividadeString() {
        return FormatUtil.dateToDefaultTimestampString(dataAtividade);
    }

    @Override
    public void setDataAtividade(Date dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    @Override
    public String getUsuarioAlocado() {
        return usuarioAlocado;
    }

    @Override
    public void setUsuarioAlocado(String usuarioAlocado) {
        this.usuarioAlocado = usuarioAlocado;
    }
}