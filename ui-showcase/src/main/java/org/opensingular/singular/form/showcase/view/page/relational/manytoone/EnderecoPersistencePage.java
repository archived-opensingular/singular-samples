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

package org.opensingular.singular.form.showcase.view.page.relational.manytoone;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.opensingular.form.SIComposite;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeEndereco;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypeUF;
import org.opensingular.singular.form.showcase.studio.persistence.repository.EnderecoFormRepository;
import org.opensingular.singular.form.showcase.view.page.relational.RelationalPersistencePage;
import org.wicketstuff.annotation.mount.MountPath;

import javax.inject.Inject;

@MountPath("relationalpersistence/manytoone")
public class EnderecoPersistencePage extends RelationalPersistencePage {

    @Inject
    private EnderecoFormRepository enderecoFormRepository;

    public EnderecoPersistencePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder) {
        builder.appendPropertyColumn("CEP", ins -> ins.getValue("cep"));
        builder.appendPropertyColumn("UF", ins -> ins.getValue("uf.nome"));
        builder.appendPropertyColumn("Endereço", ins -> ins.getValue("endereco"));
        builder.appendPropertyColumn("Bairro", ins -> ins.getValue("bairro"));
    }

    @Override
    protected FormRespository getFormRespository() {
        return enderecoFormRepository;
    }

    @Override
    protected String getERImageStringURI() {
        return "MerEnderecoUf.png";
    }

    @Override
    protected String getDDLStringURI() {
        return "DdlEnderecoUf.sql";
    }

    @Override
    protected Class<?>[] getSources() {
        return new Class[]{STypeEndereco.class, STypeUF.class};
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return Model.of("Many to one");
    }
}
