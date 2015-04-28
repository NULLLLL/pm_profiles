package com.profiles.service.account;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profiles.service.account.ShiroDbRealm.ShiroUser;
import com.profiles.user.repository.UserDao;

/**
 * shiro manager
 * 获取当前登陆者的信息
 *
 */
@Component
public class ShiroManager {

	@Autowired
	private UserDao userDao;

	/**
	 * 获取当前登陆者
	 * @return
	 */
	public ShiroUser getCurrentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

}
