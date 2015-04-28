/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.profiles.pmprofiles.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.LogUtil;

import com.profiles.pmprofiles.service.PmProFilesService;

@Controller
@RequestMapping(value = "")
public class PmProfilesController {

	private static final Logger logger = LoggerFactory.getLogger(PmProfilesController.class);

	@Autowired
	private PmProFilesService pmProFilesService;

	@RequestMapping(value = "/list")
	public String list() {
		return "profiles/profiles";
	}

	@RequestMapping(value = "/listTable")
	@ResponseBody
	public JSONArray getListTable(@RequestParam(value = "_", required = false) String _, @RequestParam(value = "params") String params) {
		List<Map<String, Object>> list = null;
		try {
			list = pmProFilesService.getListForTable(params);
			JSONArray jsonArray = JSONArray.fromObject(list);
			return jsonArray;
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			return null;
		}

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
