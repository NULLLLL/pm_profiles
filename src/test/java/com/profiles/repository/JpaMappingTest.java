/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.profiles.repository;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.LogUtil;

import com.profiles.base.BaseTest;

public class JpaMappingTest extends BaseTest {

	private static Logger logger = LoggerFactory.getLogger(JpaMappingTest.class);

	@PersistenceContext
	private EntityManager em;

	@Ignore
	@SuppressWarnings("rawtypes")
	@Test
	public void allClassMapping() throws Exception {
		Metamodel model = em.getEntityManagerFactory().getMetamodel();

		assertTrue("No entity mapping found", model.getEntities().size() > 0);

		for (EntityType entityType : model.getEntities()) {
			String entityName = entityType.getName();
			logger.info(" start: " + entityName);
			try {
				em.createQuery("select o from " + entityName + " o").getResultList();
			} catch (Exception e) {
				logger.error(e.getMessage() + " entityName is :" + entityName);
				logger.error(LogUtil.stackTraceToString(e));
				throw e;
			}
			logger.info(" finish: " + entityName);
		}
	}
}
