package com.profiles.jwrange.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

public class JWRangeDaoImpl implements JWRangeDaoCustom {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAllData() {
		StringBuilder sql = new StringBuilder("select * from j_w_range ");
		Query query = em.createNativeQuery(sql.toString());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> resultList = query.getResultList();
		return resultList;
	}

}
