package com.profiles.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.profiles.base.BaseTest;
import com.profiles.jwrange.service.JWRangeService;

@TransactionConfiguration(defaultRollback = false)
public class JWRangeServiceTest extends BaseTest {

	@Autowired
	private JWRangeService jWRangeService;

	@Test
	public void saveExcelTest() {
		String filePath = "PSCF.xls";
		jWRangeService.saveExcel(filePath);
	}

}
