package com.profiles.user.repository;

import java.util.List;
import java.util.Map;

import com.profiles.user.entity.User;

public interface UserDaoCustom {

	public List<User> getUserList(String params);

	List<Map<String, Object>> getAllUser();

}
