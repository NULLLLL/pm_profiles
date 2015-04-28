package com.profiles.base;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
@TransactionConfiguration(defaultRollback=true)
@ContextConfiguration(locations = {"/applicationContext-shiro.xml" , "/applicationContext.xml" })
public class BaseTest extends SpringTransactionalTestCase {

	@Test
	public void initContext(){
		Assert.assertNotNull(applicationContext);
		System.err.println("init applicationContext info is : " + applicationContext.getDisplayName());
		
	}
	
}
