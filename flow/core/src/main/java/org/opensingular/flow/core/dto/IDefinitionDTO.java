/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.flow.core.dto;

import java.io.Serializable;

public interface IDefinitionDTO extends Serializable {
    Integer getCod();

    void setCod(Integer cod);

    String getNome();

    void setNome(String nome);

    String getSigla();

    void setSigla(String sigla);

    String getCategoria();

    void setCategoria(String categoria);

    Long getQuantidade();

    void setQuantidade(Long quantidade);

    Long getTempoMedio();

    void setTempoMedio(Long tempoMedio);

    String getTempoMedioString();

    Long getThroughput();

    void setThroughput(Long throughput);

    Long getVersion();

    void setVersion(Long version);
}
