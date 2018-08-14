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

package org.opensingular.singular.form.showcase.studio.persistence.form;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.view.list.SViewListByTable;

import javax.annotation.Nonnull;

@SInfoType(name = "carrinhoDeCompras", spackage = SPackageStudioPersistenceForm.class)
public class STypeCarrinhoCompra extends STypeComposite<SIComposite> {

    public STypeList<STypeProduto, SIComposite> produtos;

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        produtos = addFieldListOf("produtos", STypeProduto.class);
        produtos.withView(new SViewListByTable(), view -> view.setRenderCompositeFieldsAsColumns(false));
        produtos.asAtrBootstrap().colPreference(12).asAtr().label("Produtos");
    }

}
