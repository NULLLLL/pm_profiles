package com.profiles.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.profiles.base.BaseTest;
import com.profiles.jwrange.service.JWRangeService;

@TransactionConfiguration(defaultRollback = false)
public class JWRangeServiceTest extends BaseTest {

	@Autowired
	private JWRangeService jWRangeService;

	@Ignore
	@Test
	public void saveExcelTest() {
		String filePath = "PSCF.xls";
		jWRangeService.saveExcel(filePath);
	}

	//	@Ignore
	@Test
	public void selectDataInRange() {
		jWRangeService.selectDataInRange();
	}

	@Test
	public void export() {
		jWRangeService.export("PSCF1.xls");
	}

	@Test
	public void initRange() {
		jWRangeService.setValueZero();

	}

}
