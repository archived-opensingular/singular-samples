package org.opensingular.sample.studio.definition;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.opensingular.form.SInstance;
import org.opensingular.lib.wicket.util.datatable.column.BSActionPanel;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.sample.studio.repository.TipoDoseRepository;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.definition.StudioTableDefinition;
import org.opensingular.studio.core.panel.CrudListContent;

public class TipoDoseStudioDefinition implements StudioDefinition {
    @Override
    public Class<TipoDoseRepository> getRepositoryClass() {
        return TipoDoseRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioTableDefinition studioDataTable) {
        studioDataTable.add("Tipo de Dose", "nome");
        studioDataTable.add(new CrudListContent.ListAction() {
            @Override
            public void configure(BSActionPanel.ActionConfig<SInstance> config) {
                config.iconeModel(Model.of(DefaultIcons.HAND_UP));
            }

            @Override
            public void onAction(AjaxRequestTarget target, IModel<SInstance> model) {
                target.appendJavaScript("alert('Ola Mundo!!!');");
            }
        });
    }

    @Override
    public String getTitle() {
        return "Cadastro de Tipos de Doses";
    }

}