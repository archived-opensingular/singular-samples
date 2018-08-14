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

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.util.SingularPredicates;
import org.opensingular.form.view.list.SViewListByMasterDetail;
import org.opensingular.sample.studio.entity.SimNaoConverter;

import javax.annotation.Nonnull;

@SInfoType(name = "Amostra", spackage = ResiduoPackage.class)
public class Amostra extends STypeComposite<SIComposite> {

    public STypeString codigo;
    public STypeInteger dose;
    public STypeInteger dat;
    public STypeInteger loq;
    public STypeInteger quantidadeResiduoEncontrado;
    public STypeBoolean possuiMetabolito;
    public STypeList<Metabolito, SIComposite> metabolitos;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        codigo = addField("codigo", STypeString.class);
        dose = addField("dose", STypeInteger.class);
        dat = addField("dat", STypeInteger.class);
        loq = addField("loq", STypeInteger.class);
        quantidadeResiduoEncontrado = addField("quantidadeResiduoEncontrado", STypeInteger.class);
        possuiMetabolito = addField("possuiMetabolito", STypeBoolean.class);
        metabolitos = addFieldListOf("metabolitos", Metabolito.class);

        codigo.asAtr().required().label("Código").asAtrBootstrap().colPreference(4);
        dose.asAtr().required().label("Dose").asAtrBootstrap().colPreference(4);
        dat.asAtr().required().label("DAT").asAtrBootstrap().newRow().colPreference(4);
        loq.asAtr().required().label("LOQ").asAtrBootstrap().colPreference(4);
        quantidadeResiduoEncontrado.asAtr().required().label("Residuo Encontrado").asAtrBootstrap().colPreference(4);
        possuiMetabolito.asAtr().label("Possui metabolito").asAtrBootstrap().colPreference(12);
        Metabolito metabolito = metabolitos.getElementsType();
        metabolitos.asAtr()
                .dependsOn(possuiMetabolito)
                .exists(SingularPredicates.typeValueIsTrue(possuiMetabolito))
                .label("Metabolito");
        metabolitos.withView(new SViewListByMasterDetail(), view -> view
                .col(metabolito.loq, "LOQ")
                .col(metabolito.quantidadeResiduoEncontrado, "Residuo Encontrado")
        );
		// relational mapping
        this.asSQL()
                .table("TB_AMOSTRA_ENSAIO")
                .tablePK("CO_SEQ_AMOSTRA_ENSAIO")
                .addTableFK("CO_ENSAIO_ESTUDO", EstudoResiduo.class);
        codigo.asSQL()
				.column("DS_CODIGO_AMOSTRA");
        dose.asSQL()
				.column("NU_DOSE");
        dat.asSQL()
        			.column("QT_DAT");
        loq.asSQL()
                .column("QT_LOQ");
        quantidadeResiduoEncontrado.asSQL()
        			.column("QT_RESIDUO_ENCONTRADO");
        possuiMetabolito.asSQL()
				.column("ST_POSSUI_METABOLITO")
				.columnConverter(SimNaoConverter::new);
    }
}
