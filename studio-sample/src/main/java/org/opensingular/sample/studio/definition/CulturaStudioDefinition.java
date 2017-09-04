package org.opensingular.sample.studio.definition;

import org.opensingular.form.studio.StudioCRUDPermissionStrategy;
import org.opensingular.sample.studio.repository.CulturaRepository;
import org.opensingular.studio.app.definition.StudioDefinition;

public class CulturaStudioDefinition implements StudioDefinition {

    @Override
    public Class<CulturaRepository> getRepositoryClass() {
        return CulturaRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioDataTable studioDataTable) {
        studioDataTable.add("Cultura", "nome");
    }

    @Override
    public String getTitle() {
        return "Cadastro de Culturas";
    }

    @Override
    public StudioCRUDPermissionStrategy getPermissionStrategy() {
        return StudioCRUDPermissionStrategy.VIEW_ONLY;
    }
}
