package com.profiles.user.web;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.profiles.user.service.AccountService;

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
		return JSONArray.fromObject(accountService.getUserList(params));
	}

}
