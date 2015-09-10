package br.net.mirante.singular.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import br.net.mirante.singular.flow.core.TaskType;

@Repository
public class DefinitionDAO {

    private enum Columns {
        cod("CODIGO"),
        name("NOME"),
        category("CATEGORIA"),
        quantity("QUANTIDADE"),
        time("TEMPO"),
        throu("THROU"),
        version("1");

        private String code;

        Columns(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    @Inject
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object[] retrieveById(Long id) {
        String sql = "SELECT DEF.CO_DEFINICAO_PROCESSO AS CODIGO, DEF.NO_PROCESSO AS NOME, DEF.SG_PROCESSO AS SIGLA"
                + " FROM TB_DEFINICAO_PROCESSO DEF"
                + " WHERE DEF.CO_DEFINICAO_PROCESSO = :id";
        Query query = getSession().createSQLQuery(sql)
                .addScalar("CODIGO", LongType.INSTANCE)
                .addScalar("NOME", StringType.INSTANCE)
                .addScalar("SIGLA", StringType.INSTANCE)
                .setParameter("id", id);

        return (Object[]) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> retrieveAll(int first, int size, String orderByProperty, boolean asc) {
        StringBuilder orderByStatement = new StringBuilder("");
        if (orderByProperty != null) {
            orderByStatement.append("ORDER BY ").append(Columns.valueOf(orderByProperty)).append(" ");
            orderByStatement.append(asc ? "ASC" : "DESC");
        }

        String sql = "SELECT DEF.CO_DEFINICAO_PROCESSO AS CODIGO, DEF.NO_PROCESSO AS NOME, DEF.SG_PROCESSO AS SIGLA,"
                + "          CAT.NO_CATEGORIA AS CATEGORIA, COUNT(DISTINCT DEM.CO_INSTANCIA_PROCESSO) AS QUANTIDADE,"
                + "          AVG(DATEDIFF(SECOND, DEM.DT_INICIO, DEM.DT_FIM)) AS TEMPO,"
                + "          (SELECT AVG(SUBDEM.THRO) FROM ("
                + "             SELECT CO_DEFINICAO_PROCESSO AS COD, MONTH(DT_FIM) AS MES,"
                + "                    COUNT(CO_INSTANCIA_PROCESSO) AS THRO"
                + "             FROM TB_INSTANCIA_PROCESSO TBIP"
                + "               INNER JOIN TB_PROCESSO TBP ON TBP.CO_PROCESSO = TBIP.CO_PROCESSO"
                + "             WHERE DT_FIM IS NOT NULL AND TBP.CO_DEFINICAO_PROCESSO = DEF.CO_DEFINICAO_PROCESSO"
                + "             GROUP BY CO_DEFINICAO_PROCESSO, MONTH(DT_FIM)"
                + "          ) SUBDEM GROUP BY SUBDEM.COD) AS THROU"
                + "   FROM TB_DEFINICAO_PROCESSO DEF"
                + "     INNER JOIN TB_CATEGORIA CAT ON CAT.CO_CATEGORIA = DEF.CO_CATEGORIA"
                + "     INNER JOIN TB_PROCESSO PRO ON PRO.CO_DEFINICAO_PROCESSO = DEF.CO_DEFINICAO_PROCESSO"
                + "     LEFT JOIN TB_INSTANCIA_PROCESSO DEM ON PRO.CO_PROCESSO = DEM.CO_PROCESSO"
                + "     LEFT JOIN TB_TAREFA SIT ON PRO.CO_PROCESSO = SIT.CO_PROCESSO"
                + "   WHERE"
                + "     DEM.cod_situacao IS NULL OR SIT.CO_TIPO_TAREFA != " + TaskType.End.ordinal()
                + "   GROUP BY DEF.CO_DEFINICAO_PROCESSO, DEF.NO_PROCESSO, DEF.SG_PROCESSO, CAT.NO_CATEGORIA "
                + orderByStatement.toString();
        Query query = getSession().createSQLQuery(sql)
                .addScalar("CODIGO", LongType.INSTANCE)
                .addScalar("NOME", StringType.INSTANCE)
                .addScalar("SIGLA", StringType.INSTANCE)
                .addScalar("CATEGORIA", StringType.INSTANCE)
                .addScalar("QUANTIDADE", LongType.INSTANCE)
                .addScalar("TEMPO", LongType.INSTANCE)
                .addScalar("THROU", LongType.INSTANCE);

        query.setFirstResult(first);
        query.setMaxResults(size);

        return (List<Object[]>) query.list();
    }

    public int countAll() {
        return ((Number) getSession()
                .createSQLQuery("SELECT COUNT(*) FROM TB_DEFINICAO_PROCESSO").uniqueResult()).intValue();
    }
}