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

package org.opensingular.singular.form.showcase.view.page.relational.simple;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.opensingular.form.SIComposite;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.singular.form.showcase.studio.persistence.form.STypePessoa;
import org.opensingular.singular.form.showcase.studio.persistence.repository.PessoaFormRepository;
import org.opensingular.singular.form.showcase.view.page.relational.RelationalPersistencePage;
import org.wicketstuff.annotation.mount.MountPath;

import javax.inject.Inject;

@MountPath("relationalpersistence/simple")
public class PessoaPersistencePage extends RelationalPersistencePage {

    @Inject
    private PessoaFormRepository pessoaFormRepository;

    public PessoaPersistencePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder) {
        builder.appendPropertyColumn("Nome", ins -> ins.getValue("nome"));
        builder.appendPropertyColumn("CPF", ins -> ins.getValue("cpf"));
        builder.appendPropertyColumn("Email", ins -> ins.getValue("email"));
    }

    @Override
    protected FormRespository getFormRespository() {
        return pessoaFormRepository;
    }

    @Override
    protected String getERImageStringURI() {
        return "MerPessoa.png";
    }

    @Override
    protected String getDDLStringURI() {
        return "DdlPessoa.sql";
    }

    @Override
    protected Class<?>[] getSources() {
        return new Class[]{STypePessoa.class};
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return Model.of("Simple");
    }

}
