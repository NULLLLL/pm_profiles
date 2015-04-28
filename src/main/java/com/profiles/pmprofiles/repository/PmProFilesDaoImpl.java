package com.profiles.pmprofiles.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

public class PmProFilesDaoImpl implements PmProFilesDaoCustom {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> get_distinct_p_numberAndDocument() {
		StringBuilder sql = new StringBuilder();
		sql.append("select  distinct(p.p_number),p.document from profiles as p order by p.p_number");
		Query query = em.createNativeQuery(sql.toString());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, String>> resultList = query.getResultList();

		return resultList;
	}

}
