package br.net.mirante.singular.flow.core.dto;

import java.io.Serializable;
import java.util.Date;

public interface IInstanceDTO extends Serializable {
    Long getCod();

    void setCod(Long cod);

    String getDescricao();

    void setDescricao(String descricao);

    Long getDelta();

    String getDeltaString();

    void setDelta(Long delta);

    Date getDataInicial();

    String getDataInicialString();

    void setDataInicial(Date dataInicial);

    Long getDeltaAtividade();

    String getDeltaAtividadeString();

    void setDeltaAtividade(Long deltaAtividade);

    Date getDataAtividade();

    String getDataAtividadeString();

    void setDataAtividade(Date dataAtividade);

    String getUsuarioAlocado();

    void setUsuarioAlocado(String usuarioAlocado);
}