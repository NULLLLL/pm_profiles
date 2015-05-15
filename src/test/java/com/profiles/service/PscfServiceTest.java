package com.profiles.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.profiles.base.BaseTest;
import com.profiles.pscf.service.PscfService;

//@TransactionConfiguration(defaultRollback = false)
public class PscfServiceTest extends BaseTest {

	@Autowired
	private PscfService pscfService;

	@Ignore
	@Test
	public void saveExcel() {
		String filePath = "PSCF.xls";
		pscfService.saveExcel(filePath);
	}

}
