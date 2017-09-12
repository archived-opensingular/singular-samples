package org.opensingular.sample.studio.definition;

import org.opensingular.sample.studio.repository.NormaRepository;
import org.opensingular.studio.core.definition.StudioDefinition;

public class NormaStudioDefinition implements StudioDefinition {
    @Override
    public Class<NormaRepository> getRepositoryClass() {
        return NormaRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioDefinition.StudioDataTable studioDataTable) {
        studioDataTable.add("Norma", "nome");
    }


    @Override
    public String getTitle() {
        return "Cadastro de Normas";
    }
}
