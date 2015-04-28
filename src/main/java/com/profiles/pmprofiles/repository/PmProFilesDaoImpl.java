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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> get_distinct_p_numberAndName() {
		StringBuilder sql = new StringBuilder();
		sql.append("select  distinct(p.p_number),p.name from profiles as p order by p.p_number");
		Query query = em.createNativeQuery(sql.toString());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, String>> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getByP_Number(String p_number) {
		StringBuilder sql = new StringBuilder();
		sql.append("select p.weight_per, p.uncertaint, p.symbol from profiles as p where p.p_number ='").append(p_number).append("' order by id asc");
		Query query = em.createNativeQuery(sql.toString());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> resultList = query.getResultList();
		return resultList;

	}

}
