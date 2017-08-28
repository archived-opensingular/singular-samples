package org.opensingular.sample.studio.dao;

import org.opensingular.lib.support.persistence.BaseDAO;
import org.opensingular.sample.studio.entity.ModalidadeEmpregoEntity;

import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional(Transactional.TxType.MANDATORY)
public class ModalidadeEmpregoDAO extends BaseDAO<ModalidadeEmpregoEntity, Integer> {

    public ModalidadeEmpregoDAO() {
        super(ModalidadeEmpregoEntity.class);
    }

}