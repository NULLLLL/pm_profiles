package com.profiles.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.profiles.base.BaseTest;
import com.profiles.pscf.service.PscfService;

@TransactionConfiguration(defaultRollback = false)
public class PscfServiceTest extends BaseTest {

	@Autowired
	private PscfService pscfService;

	@Test
	public void saveExcel() {
		String filePath = "PSCF.xls";
		pscfService.saveExcel(filePath);
	}

	@Test
	public void saveData() {
		pscfService.saveData(null);
	}

}
