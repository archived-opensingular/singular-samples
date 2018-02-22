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

package org.opensingular.sample.studio.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeInteger;

import javax.annotation.Nonnull;

@SInfoType(name = "Metabolito", spackage = ResiduoPackage.class)
public class Metabolito extends STypeComposite<SIComposite> {

    public STypeInteger loq;
    public STypeInteger quantidadeResiduoEncontrado;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        loq = addField("loq", STypeInteger.class);
        quantidadeResiduoEncontrado = addField("quantidadeResiduoEncontrado", STypeInteger.class);
        loq.asAtr().required().label("LOQ").asAtrBootstrap().colPreference(6);
        quantidadeResiduoEncontrado.asAtr().required().label("Residuo Encontrado").asAtrBootstrap().colPreference(6);
		// relational mapping
        this.asSQL()
                .table("TB_METABOLITO_AMOSTRA")
                .tablePK("CO_SEQ_METABOLITO_AMOSTRA")
                .addTableFK("CO_AMOSTRA_ENSAIO", Amostra.class);
        loq.asSQL()
                .column("QT_LOQ");
        quantidadeResiduoEncontrado.asSQL()
        			.column("QT_RESIDUO_ENCONTRADO");
    }
}
