package org.opensingular.sample.studio.definition;

import org.opensingular.sample.studio.repository.TipoDoseRepository;
import org.opensingular.studio.core.definition.StudioDefinition;

public class TipoDoseStudioDefinition implements StudioDefinition {
    @Override
    public Class<TipoDoseRepository> getRepositoryClass() {
        return TipoDoseRepository.class;
    }

    @Override
    public void configureStudioDataTable(StudioDefinition.StudioDataTable studioDataTable) {
        studioDataTable.add("Tipo de Dose", "nome");
    }


    @Override
    public String getTitle() {
        return "Cadastro de Tipos de Doses";
    }
}
