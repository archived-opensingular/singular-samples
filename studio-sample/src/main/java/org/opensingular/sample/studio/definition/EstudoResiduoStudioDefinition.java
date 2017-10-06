package org.opensingular.sample.studio.definition;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.opensingular.form.SInstance;
import org.opensingular.sample.studio.repository.EstudoResiduoRepository;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.definition.StudioTableDefinition;
import org.opensingular.studio.core.panel.CrudEditContent;
import org.opensingular.studio.core.panel.CrudShellContent;
import org.opensingular.studio.core.panel.CrudShellManager;

public class EstudoResiduoStudioDefinition implements StudioDefinition {

    @Override
    public Class<EstudoResiduoRepository> getRepositoryClass() {
        return EstudoResiduoRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioTableDefinition studioDataTable) {
        studioDataTable.add("Cultura", "cultura.display");
        studioDataTable.add("Modalidade de Emprego", "modalidadeDeEmprego.display");
        studioDataTable.add("Norma", "norma.display");
        studioDataTable.add("Tipo de Dose", "tipoDose.display");
        studioDataTable.add("Intervalo de Segurança", "intervaloSeguranca");
    }

    @Override
    public String getTitle() {
        return "Cadastro de Estudos de Residuos";
    }


    @Override
    public CrudEditContent makeEditContent(CrudShellManager crudShellManager, CrudShellContent previousContent, IModel<SInstance> instance) {
        CrudEditContent crudEditContent = new CrudEditContent(crudShellManager, previousContent, instance);
        crudEditContent.setSaveButtonFactory(new CrudEditContent.SaveButtonFactory(crudShellManager) {
            @Override
            public Button make(String id, IModel<SInstance> instanceModel) {
                return new CrudEditContent.StudioSaveButton(id, instanceModel, crudShellManager) {
                    @Override
                    protected void onValidationSuccess(AjaxRequestTarget target, Form<?> form, IModel<? extends SInstance> instanceModel) {
                        crudShellManager.addConfirm("Tem certeza que deseja salvar?", target, newTarget -> {
                            super.onValidationSuccess(newTarget, form, instanceModel);
                        });
                    }
                };
            }
        });
        return crudEditContent;
    }

}
