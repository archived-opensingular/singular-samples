package org.opensingular.sample.studio.dao;

import org.opensingular.lib.support.persistence.BaseDAO;
import org.opensingular.sample.studio.entity.CulturaEntity;

import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional(Transactional.TxType.MANDATORY)
public class CulturaDAO extends BaseDAO<CulturaEntity, Integer> {

    public CulturaDAO() {
        super(CulturaEntity.class);
    }

}