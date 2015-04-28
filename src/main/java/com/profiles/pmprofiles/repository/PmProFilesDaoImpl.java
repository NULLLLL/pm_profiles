package com.profiles.pmprofiles.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.sf.json.JSONObject;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import util.StringHelper;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getListForTable(String params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from profiles as p ");
		sql.append(where(params));
		Query query = em.createNativeQuery(sql.toString());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> resultList = query.getResultList();
		return resultList;

	}

	@SuppressWarnings("unchecked")
	private String where(String params) {
		if (StringHelper.isEmpty(params))
			return "";
		JSONObject jsonObject = JSONObject.fromObject(params);
		Iterator<String> keys = jsonObject.keys();
		StringBuilder sql = new StringBuilder(" where 1=1 ");
		String key = null;
		String data = null;
		while (keys.hasNext()) {
			key = (String) keys.next();
			data = jsonObject.getString(key);
			if (StringHelper.isNotEmpty(data))
				sql.append(" and p.").append(key).append(" ='").append(data).append("' ");
		}
		sql.append(" order by p.id asc ");
		return sql.toString();
	}
}
