/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.singular.form.showcase.component.studio.simplepersistence;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeString;

@SInfoType(name = "STypeDemanda", spackage = DemandaPackage.class)
public class STypeDemanda extends STypeComposite<SIComposite> {

    public STypeString titulo;
    public PessoaRef demandante;
    public PessoaRef atendente;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        titulo = addField("titulo", STypeString.class);
        demandante = addField("demandante", PessoaRef.class);
        atendente = addField("atendente", PessoaRef.class);

        titulo.asAtr().label("Título").asAtrBootstrap().colPreference(12);
        titulo.asAtr().required();
        demandante.asAtr().required().label("Demandante").asAtrBootstrap().colPreference(6);
        atendente.asAtr().required().label("Atendente").asAtrBootstrap().colPreference(6);
        // relational mapping
        // @destacar
        this.asSQL()
                // @destacar
                .table("T_DEMANDA")
                // @destacar
                .tablePK("ID")
                // @destacar
                .addTableFK("ID_DEMANDANTE", STypePessoa.class)
                // @destacar
                .addTableFK("ID_ATENDENTE", STypePessoa.class);
        // @destacar
        titulo.asSQL()
                // @destacar
                .column("TITULO");
        // @destacar
        demandante.key.asSQL()
                // @destacar
                .column("ID_DEMANDANTE");
        // @destacar
        atendente.key.asSQL()
                // @destacar
                .column("ID_ATENDENTE");
    }
}
