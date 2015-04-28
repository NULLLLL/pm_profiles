package com.profiles.service.account;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profiles.entity.User;
import com.profiles.repository.UserDao;
import com.profiles.service.account.ShiroDbRealm.ShiroUser;

/**
 * shiro manager 获取当前登陆者的信息
 * 
 */
@Component
public class ShiroManager {

	@Autowired
	private UserDao userDao;

	/**
	 * 根据登陆名查询系统用户
	 * 
	 * @param loginName
	 * @return
	 */
	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	/**
	 * 获取当前登陆者
	 * 
	 * @return
	 */
	public ShiroUser getCurrentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取当前登陆者的id.
	 */
	public Long getCurrentUserId() {
		return getCurrentUser().id;
	}

	/**
	 * 获取当前登陆者的name.
	 */
	public String getCurrentUserName() {
		return getCurrentUser().name;
	}

}
