package org.opensingular.sample.studio.definition;

import org.opensingular.sample.studio.repository.ModalidadeEmpregoRepository;
import org.opensingular.studio.core.definition.StudioDefinition;

public class ModalidadeDeEmpregoStudioDefinition implements StudioDefinition {
    @Override
    public Class<ModalidadeEmpregoRepository> getRepositoryClass() {
        return ModalidadeEmpregoRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioDefinition.StudioDataTable studioDataTable) {
        studioDataTable.add("Modalidade de Emprego", "nome");
    }

    @Override
    public String getTitle() {
        return "Cadastro de Modalidades de Emprego";
    }
}
