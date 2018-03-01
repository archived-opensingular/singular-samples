package org.opensingular.singular.form.showcase.view.page.relational;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.opensingular.form.SIComposite;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.persistence.FormRespository;
import org.opensingular.form.studio.SingularStudioSimpleCRUDPanel;
import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.lib.wicket.util.datatable.BSDataTableBuilder;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.lib.wicket.util.tab.BSTabPanel;
import org.opensingular.singular.form.showcase.view.page.ItemCodePanel;
import org.opensingular.singular.form.showcase.view.template.ShowcaseTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class RelationalPersistencePage extends ShowcaseTemplate implements Loggable {
    public RelationalPersistencePage(PageParameters parameters) {
        super(parameters);
        buildPage();
    }

    private void buildPage() {
        addCrudPanel();
        addSources();
        addMERImage();
        addDDLPanel();
    }

    private void addSources() {
        BSTabPanel tabs = new BSTabPanel("sources");
        for(Class<?> source : getSources()){
            try {
                InputStream script = Thread.currentThread().getContextClassLoader().getResourceAsStream(source.getName().replace('.', '/') + ".java");
                ItemCodePanel itemCodePanel = new ItemCodePanel(BSTabPanel.TAB_PANEL_ID, Model.of(IOUtils.toString(script, StandardCharsets.UTF_8)), "java");
                tabs.addTab(source.getSimpleName(), itemCodePanel);
            } catch (Exception e) {
                getLogger().error(e.getMessage(), e);
            }
        }
        add(tabs);
    }

    private void addMERImage() {
        String erImageStringURI = getERImageStringURI();
        if (erImageStringURI != null) {
            add(new Image("mer", new PackageResourceReference(getClass(), erImageStringURI)));
        } else {
            add(new WebMarkupContainer("mer"));
        }
    }

    private void addDDLPanel() {
        String ddlStringURI = getDDLStringURI();
        if(ddlStringURI != null) {
            try {
                InputStream script = getClass().getResourceAsStream(ddlStringURI);
                if(script != null) {
                    add(new ItemCodePanel("ddl", Model.of(IOUtils.toString(script, StandardCharsets.UTF_8)), "sql"));
                    return;
                }
            } catch (Exception e) {
               getLogger().error(e.getMessage(), e);
            }
        }
        add(new WebMarkupContainer("ddl"));
    }

    private void addCrudPanel() {
        add(new SingularStudioSimpleCRUDPanel<STypeComposite<SIComposite>, SIComposite>("crud", this::getFormRespository) {
            @Override
            protected void buildListTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder) {
                configureTable(builder);
            }
        }.setCrudTitle("Formulario").setCrudIcon(DefaultIcons.DOCS));
    }

    @Override
    protected IModel<String> getContentTitle() {
        return Model.of("Persistência Relacional");
    }

    protected abstract void configureTable(BSDataTableBuilder<SIComposite, String, IColumn<SIComposite, String>> builder);

    protected abstract FormRespository getFormRespository();

    protected abstract String getERImageStringURI();

    protected abstract String getDDLStringURI();

    protected abstract Class<?>[] getSources();
}