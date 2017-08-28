package org.opensingular.sample.studio.dao;

import org.opensingular.lib.support.persistence.BaseDAO;
import org.opensingular.sample.studio.entity.TipoDoseEntity;

import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional(Transactional.TxType.MANDATORY)
public class TipoDoseDAO extends BaseDAO<TipoDoseEntity, Integer> {

    public TipoDoseDAO() {
        super(TipoDoseEntity.class);
    }

}