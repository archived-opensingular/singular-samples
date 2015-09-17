package br.net.mirante.singular.service;

import java.time.Period;
import java.util.List;
import java.util.Map;

import br.net.mirante.singular.flow.core.dto.IStatusDTO;

public interface PesquisaService {

    List<Map<String, String>> retrieveMeanTimeByProcess(Period period);

    List<Map<String, String>> retrieveNewInstancesQuantityLastYear(String processCode);

    List<Map<String, String>> retrieveEndStatusQuantityByPeriod(Period period, String processCode);

    List<Map<String, String>> retrieveMeanTimeByTask(Period period, String processCode);

    List<Map<String,String>> retrieveCountByTask(String processDefinitionCode);

    IStatusDTO retrieveActiveInstanceStatus(String processCode);

    List<Map<String, String>> retrieveMeanTimeActiveInstances(String processCode);

    List<Map<String, String>> retrieveMeanTimeFinishedInstances(String processCode);

    List<Map<String, String>> retrieveCounterActiveInstances(String processCode);

    String retrieveProcessDefinitionName(String processCode);

    String retrieveProcessDefinitionId(String processDefinitionCode);
}
