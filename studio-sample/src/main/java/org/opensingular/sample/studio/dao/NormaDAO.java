package org.opensingular.sample.studio.dao;

import org.opensingular.lib.support.persistence.BaseDAO;
import org.opensingular.sample.studio.entity.NormaEntity;

import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional(Transactional.TxType.MANDATORY)
public class NormaDAO extends BaseDAO<NormaEntity, Integer> {

    public NormaDAO() {
        super(NormaEntity.class);
    }

}