package org.opensingular.sample.studio.definition;

import org.opensingular.sample.studio.repository.EstudoResiduoRepository;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.opensingular.studio.core.definition.StudioTableDefinition;

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
}
