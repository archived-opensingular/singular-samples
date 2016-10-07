/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.opensingular.bam.dao;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import org.opensingular.flow.core.TaskType;
import org.opensingular.flow.persistence.entity.ProcessDefinitionEntity;

@Repository
@SuppressWarnings("unchecked")
public class PesquisaDAO extends BaseDAO{

    private static final int MAX_MAP_SIZE = 7;

    public List<Map<String, String>> retrieveMeanTimeByProcess(Period period, String processCode, Set<String> processCodeWithAccess) {
        Query hqlQuery = getSession().createQuery("select pd.key as SIGLA, pd.name as NOME, "
            + "trim(str(avg((" +
                " dateDiffInDays(pi.endDate,pi.beginDate)" +
                ")))) as MEAN "
            + "from ProcessInstanceEntity pi join pi.processVersion pv join pv.processDefinition pd "
            + "where pi.endDate is not null "
            + (period != null ? "and pi.beginDate >= :startPeriod and pi.endDate <= :endPeriod " : "")
            + (processCode != null ? " and pd.key = :processCode " : "")
            + "and pd.key in(:processCodeWithAccess) "
            + "group by pd.key, pd.name order by MEAN desc");
        hqlQuery.setParameterList("processCodeWithAccess", processCodeWithAccess).setMaxResults(15).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (period != null) {
            hqlQuery.setParameter("startPeriod", periodFromNow(period));
            hqlQuery.setParameter("endPeriod", new Date());
        }
        if (processCode != null) {
            hqlQuery.setParameter("processCode", processCode);
        }
        
        return (List<Map<String, String>>) hqlQuery.list();
    }

    private Date periodFromNow(Period period) {
        Temporal temporal = period.addTo(LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.from(temporal);

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public List<Map<String, String>> retrieveMeanTimeByTask(Period period, String processCode) {
        int count = retrieveMeanTimeByTaskCount(period, processCode);
        if (count > MAX_MAP_SIZE) {
            List<Map<String, String>> mainTasks = retrieveMeanTimeByTasks(period, processCode, MAX_MAP_SIZE - 1);
            mainTasks.add(retrieveMeanTimeByOthers(period, processCode, count - MAX_MAP_SIZE + 1));
            return mainTasks;
        } else {
            return retrieveMeanTimeByTasks(period, processCode, MAX_MAP_SIZE);
        }
    }

    private Integer retrieveMeanTimeByTaskCount(Period period, String processCode) {
        Query hqlQuery = getSession().createQuery("select count(distinct ti.task.name) as quantidade "
            + "from TaskInstanceEntity ti join ti.processInstance pi join pi.processVersion pv join pv.processDefinition pd "
            + "where pi.endDate is not null and pi.endDate >= :startPeriod and pd.key = :processCode "
            + "and ti.task.type <> :taskEnd ");
        hqlQuery.setParameter("startPeriod", periodFromNow(period)).setParameter("processCode", processCode).setParameter("taskEnd", TaskType.End);
        
        return ((Number) hqlQuery.uniqueResult()).intValue();
    }

    private List<Map<String, String>> retrieveMeanTimeByTasks(Period period, String processCode, int max) {
        Query hqlQuery = getSession().createQuery("select ti.task.name as NOME, pd.cod as COD, pd.name as NOME_DEFINICAO, "
            + " avg((" +
                "  dateDiffInDays(ti.endDate, ti.beginDate)" +
                ")) as MEAN "
            + "from TaskInstanceEntity ti join ti.processInstance pi join pi.processVersion pv join pv.processDefinition pd "
            + "where pi.endDate is not null and pi.endDate >= :startPeriod and pd.key = :processCode "
            + "and ti.task.type <> :taskEnd "
            + "group by ti.task.name, pd.cod, pd.name order by MEAN desc");
        hqlQuery.setMaxResults(max)
            .setParameter("startPeriod", periodFromNow(period))
            .setParameter("processCode", processCode)
            .setParameter("taskEnd", TaskType.End)
            .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        
        return (List<Map<String, String>>) hqlQuery.list();
    }

    @SuppressWarnings("rawtypes")
    private Map<String, String> retrieveMeanTimeByOthers(Period period, String processCode, int max) {
        Query hqlQuery = getSession().createQuery("select pd.name as NOME_DEFINICAO, avg((" +
                " dateDiffInDays(ti.endDate, ti.beginDate)" +
                ")) as MEAN "
            + "from TaskInstanceEntity ti join ti.processInstance pi join pi.processVersion pv join pv.processDefinition pd "
            + "where pi.endDate is not null and pi.endDate >= :startPeriod and pd.key = :processCode "
            + "and ti.task.type <> :taskEnd "
            + "group by pd.cod, pd.name,ti.task.name order by MEAN");
        hqlQuery.setMaxResults(max)
            .setParameter("startPeriod", periodFromNow(period))
            .setParameter("processCode", processCode)
            .setParameter("taskEnd", TaskType.End)
            .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        
        Map map = new HashMap(4);
        map.put("NOME", "Outras");
        map.put("COD", 0);
        map.put("MEAN", 0.0);
        for (Map<String, Object> result : (List<Map<String, Object>>) hqlQuery.list()) {
            map.put("NOME_DEFINICAO", result.get("NOME_DEFINICAO"));
            map.put("MEAN", ((Double)map.get("MEAN")) + Math.round((Double)result.get("MEAN")));
        }
        return map;
    }

    public List<Map<String, String>> retrieveStatsByActiveTask(String processCode) {
        Query hqlQuery = getSession().createQuery("select ti.task.name as NOME, count(distinct pi.cod) as QUANTIDADE, "
            + "avg((" +
                "  dateDiffInDays(current_date(),ti.beginDate)" +

                ") * (24 * 60 * 60)) / (24 * 60 * 60) as TEMPO "
            + "from TaskInstanceEntity ti join ti.processInstance pi join pi.processVersion pv join pv.processDefinition pd "
            + "where pi.endDate is null and ti.endDate is null and pd.key = :processCode "
            + "group by ti.task.name order by QUANTIDADE desc");
        hqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .setParameter("processCode", processCode);
        
        return hqlQuery.list();
    }

    public String retrieveProcessDefinitionName(String processCode) {
        Criteria criteria = getSession().createCriteria(ProcessDefinitionEntity.class);
        criteria.add(Restrictions.eq("key", processCode));
        criteria.setProjection(Projections.property("name"));
        return (String) criteria.uniqueResult();
    }

    public Integer retrieveProcessDefinitionId(String processCode) {
        Criteria criteria = getSession().createCriteria(ProcessDefinitionEntity.class);
        criteria.add(Restrictions.eq("key", processCode));
        criteria.setProjection(Projections.id());
        return (Integer) criteria.uniqueResult();
    }
}
