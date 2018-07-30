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
package org.opensingular.singular.form.showcase.component.form.core.search.form;

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

    public String getNome() {
        return nome;
    }

    public List<Processo> getSubProcessos() {
        return subProcessos;
    }

    public void addSubProcesso(Processo processo) {
        subProcessos.add(processo);
    }
}
