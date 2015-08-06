package com.profiles.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.profiles.base.BaseTest;
import com.profiles.test.TestI;
import com.profiles.test.TestIDao;

@TransactionConfiguration(defaultRollback = false)
public class ITest extends BaseTest {

	@Autowired
	private TestIDao testIDao;

	@Test
	public void save() {
		TestI testI = null;
		for (int i = 0; i < 1000000; i++) {
			testI = new TestI();
			testI.setName("abc" + i);
			testI.setName1(i);
			testIDao.save(testI);
		}
	}

}
