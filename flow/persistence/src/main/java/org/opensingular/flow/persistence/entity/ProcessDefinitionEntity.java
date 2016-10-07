/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.flow.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import org.opensingular.lib.support.persistence.util.Constants;
import org.opensingular.lib.support.persistence.util.HybridIdentityOrSequenceGenerator;

/**
 * The persistent class for the TB_DEFINICAO_PROCESSO database table.
 */
@Entity
@GenericGenerator(name = AbstractProcessDefinitionEntity.PK_GENERATOR_NAME, strategy = HybridIdentityOrSequenceGenerator.CLASS_NAME)
@Table(name = "TB_DEFINICAO_PROCESSO", schema = Constants.SCHEMA)
public class ProcessDefinitionEntity extends AbstractProcessDefinitionEntity<ProcessGroupEntity,CategoryEntity, TaskDefinitionEntity, RoleDefinitionEntity, ProcessVersionEntity> {

    private static final long serialVersionUID = 1L;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = Constants.SCHEMA, name = "TB_VERSAO_PROCESSO", 
        joinColumns = @JoinColumn(name = "CO_DEFINICAO_PROCESSO", referencedColumnName = "CO_DEFINICAO_PROCESSO"),
        inverseJoinColumns = @JoinColumn(name = "CO_VERSAO_PROCESSO", referencedColumnName = "CO_VERSAO_PROCESSO"))
    private List<ProcessInstanceEntity> processInstances;

    public List<ProcessInstanceEntity> getProcessInstances() {
        return processInstances;
    }

    public void setProcessInstances(List<ProcessInstanceEntity> processInstances) {
        this.processInstances = processInstances;
    }
}
