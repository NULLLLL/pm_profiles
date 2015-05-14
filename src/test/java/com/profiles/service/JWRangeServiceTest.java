package com.profiles.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.profiles.base.BaseTest;
import com.profiles.jwrange.service.JWRangeService;

//@TransactionConfiguration(defaultRollback = false)
public class JWRangeServiceTest extends BaseTest {

	@Autowired
	private JWRangeService jWRangeService;

	@Ignore
	@Test
	public void saveExcelTest() {
		String filePath = "PSCF.xls";
		jWRangeService.saveExcel(filePath);
	}

	@Test
	public void selectDataInRange() {
		jWRangeService.selectDataInRange();
	}

}
