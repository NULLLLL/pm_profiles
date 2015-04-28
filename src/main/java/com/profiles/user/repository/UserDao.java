package com.profiles.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.profiles.user.entity.User;

public interface UserDao extends CrudRepository<User, Long>, UserDaoCustom {
	User findByLoginName(String loginName);
}
