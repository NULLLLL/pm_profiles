package com.profiles.pscf.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

public class PscfDaoImpl implements PscfDaoCustom {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByJingDuAndWeiDu(double jingdu, double weidu) {
		StringBuilder sql = new StringBuilder("select * from pscf where ");
		sql.append(" jingdu>").append((double) (jingdu - (double) 1)); // 10<a<=15
		sql.append(" and jingdu<=").append(jingdu);
		sql.append(" and weidu>").append((double) (weidu - (double) 1));
		sql.append(" and weidu<=").append(weidu);
		Query query = em.createNativeQuery(sql.toString());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> resultList = query.getResultList();
		return resultList;
	}

}
