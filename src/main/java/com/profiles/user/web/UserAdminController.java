/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.profiles.user.web;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.profiles.user.service.AccountService;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin")
public class UserAdminController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/user")
	public String list() {
		return "account/adminUserList";
	}

	@RequestMapping(value = "/userTable")
	@ResponseBody
	public JSONArray getUserTable(@RequestParam(value = "_", required = false) String _, @RequestParam(value = "params") String params) {
		return null;
	}

	@RequestMapping(value = "/export")
	@ResponseBody
	public void exportExcel(HttpServletResponse response) {
		/*List<User> allUser = accountService.getAllUser();
		String[] colNames = new String[] { "用户名", "姓名", "注册时间", "邮箱" };
		String[] fieldNames = new String[] { "loginName", "name", "registerDate", "email" };
		HSSFWorkbook hssfWorkbook = BuildExcelUtil.build("user", allUser, colNames, fieldNames);
		String fileName = "user.xls";
		ExportResponse.export(response, hssfWorkbook, fileName);*/
	}

}
