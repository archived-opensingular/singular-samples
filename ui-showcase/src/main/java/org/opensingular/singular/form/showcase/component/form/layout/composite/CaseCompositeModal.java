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

package org.opensingular.singular.form.showcase.component.form.layout.composite;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.country.brazil.STypeAddress;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.form.view.SViewByBlock;
import org.opensingular.form.view.SViewCompositeModal;
import org.opensingular.form.view.list.SViewListByMasterDetail;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.layout.CaseLayoutPackage;

import javax.annotation.Nonnull;

/**
 * Composite Modal
 */
@CaseItem(componentName = "Composite Modal", subCaseName = "Default", group = Group.LAYOUT, resources = @Resource(CaseLayoutPackage.class))
@SInfoType(spackage = CaseLayoutPackage.class, name = "DefaultCompositeModal")
public class CaseCompositeModal extends STypeComposite<SIComposite> {

    public STypeAddress endereco;

    public STypeList<STypeAddress, SIComposite> enderecos;


    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        endereco = this.addField("endereco", STypeAddress.class);
        endereco.asAtr()
                .required()
                .label("Endereço Residencial");

        enderecos = this.addFieldListOf("enderecos", STypeAddress.class);

        enderecos.getElementsType().asAtr()
                .required()
                .label("Endereço Comercial");

        enderecos
                .withView(SViewListByMasterDetail::new);

        //@destacar:bloco
        SViewCompositeModal view = new SViewCompositeModal();
        endereco.withView(view);
        //@destacar:fim
    }
}
