/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.profiles.user.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.profiles.service.account.ShiroManager;
import com.profiles.user.entity.User;
import com.profiles.user.repository.UserDao;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountServiceImpl implements AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private ShiroManager shiroManager;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	@Override
	@Transactional(readOnly = true)
	public User checkUserLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

}
