package org.opensingular.singular.form.showcase.view.page.relational;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.form.studio.SingularStudioSimpleCRUDPanel;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.lib.wicket.util.resource.Icon;
import org.opensingular.singular.form.showcase.view.page.ItemCodePanel;
import org.opensingular.singular.form.showcase.view.template.ShowcaseTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class RelationalPersistencePage extends ShowcaseTemplate {

    public RelationalPersistencePage(PageParameters parameters) {
        super(parameters);
        buildPage();
    }

    private void buildPage() {
        addCrudPanel();
        addTypeCode();
        addMERImage();
        addDDLPanel();
    }

    private void addTypeCode() {
        try {
            InputStream script = Thread.currentThread().getContextClassLoader().getResourceAsStream(getTypeClass().getName().replace(".", "/") + ".java");
            add(new ItemCodePanel("type-code", Model.of(IOUtils.toString(script, StandardCharsets.UTF_8)), "java"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMERImage() {
        add(new Image("mer", new PackageResourceReference(getClass(), getERImageStringURI())));
    }

    private void addDDLPanel() {
        try {
            InputStream script = getClass().getResourceAsStream(getDDLStringURI());
            add(new ItemCodePanel("ddl", Model.of(IOUtils.toString(script, StandardCharsets.UTF_8)), "sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCrudPanel() {
        add(new SingularStudioSimpleCRUDPanel<STypeComposite<SIComposite>, SIComposite>("crud", this::getFormRespository) {
            @Override
            protected void buildListTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder) {
                builder.appendPropertyColumn("Nome", ins -> ins.getValue("nome"));
                builder.appendPropertyColumn("CPF", ins -> ins.getValue("cpf"));
                builder.appendPropertyColumn("EMail", ins -> ins.getValue("email"));
            }
        }.setCrudTitle("Cadastro de Pessoa").setCrudIcon(Icon.of("fa fa-group")));
    }


    @Override
    protected IModel<String> getContentTitle() {
        return Model.of("Persistência Relacional");
    }

    protected abstract FormRespository getFormRespository();

    protected abstract String getERImageStringURI();

    protected abstract String getDDLStringURI();

    protected abstract Class<?> getTypeClass();


}