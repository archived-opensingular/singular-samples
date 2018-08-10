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

package org.opensingular.sample.studio.form;


import org.opensingular.form.*;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewListByMasterDetail;

import javax.annotation.Nonnull;

@SInfoType(name = "Ensaio", spackage = ResiduoPackage.class)
public class Ensaio extends STypeComposite<SIComposite> {

    public STypeString cidade;
    public STypeString codigo;
    public STypeList<Amostra, SIComposite> amostras;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        codigo = addField("codigo", STypeString.class);
        cidade = addField("cidade", STypeString.class);
        amostras = addFieldListOf("amostras", Amostra.class);
        codigo.asAtr().required().label("Código").asAtrBootstrap().colPreference(6);
        cidade.asAtr().required().label("Cidade").asAtrBootstrap().colPreference(6);
        Amostra amostra = amostras.getElementsType();
        amostras.asAtr().label("Amostra").asAtrBootstrap().colPreference(12);
        amostras.withView(new SViewListByMasterDetail(), view ->
            view.col(amostra.codigo, "Código")
                .col(amostra.dose, "Dose")
                .col(amostra.dat, "DAT")
                .col(amostra.loq, "LOQ")
        );
		// relational mapping
        this.asSQL()
                .table("TB_ENSAIO_ESTUDO_RESIDUOS")
                .tablePK("CO_SEQ_ENSAIO_ESTUDO")
                .addTableFK("CO_ESTUDO_RESIDUOS", EstudoResiduo.class);
        codigo.asSQL()
				.column("DS_CODIGO_ENSAIO");
        cidade.asSQL()
				.column("DS_CIDADE");
    }
}