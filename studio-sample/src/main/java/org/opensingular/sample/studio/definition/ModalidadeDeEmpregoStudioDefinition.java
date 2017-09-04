package org.opensingular.sample.studio.definition;

import org.opensingular.sample.studio.repository.ModalidadeEmpregoRepository;
import org.opensingular.studio.app.definition.StudioDefinition;

public class ModalidadeDeEmpregoStudioDefinition implements StudioDefinition {
    @Override
    public Class<ModalidadeEmpregoRepository> getRepositoryClass() {
        return ModalidadeEmpregoRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioDataTable studioDataTable) {
        studioDataTable.add("Modalidade de Emprego", "nome");
    }

    @Override
    public String getTitle() {
        return "Cadastro de Modalidades de Emprego";
    }
}
