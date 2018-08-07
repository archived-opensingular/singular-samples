/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.sample.studio.entity;

import org.opensingular.lib.support.persistence.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "TD_TIPO_DOSE")
public class TipoDoseEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "CO_SEQ_TIPO_DOSE")
    private Integer cod;

    @Column(name = "NO_TIPO_DOSE")
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