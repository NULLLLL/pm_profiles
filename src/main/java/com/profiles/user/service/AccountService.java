package com.profiles.user.service;

import java.util.List;

import com.profiles.user.entity.User;

public interface AccountService {

	List<User> getAllUser();

	User getUser(Long id);

	User findUserByLoginName(String loginName);

	User checkUserLoginName(String loginName);

}
