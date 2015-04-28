package com.profiles.service;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import util.FileUtil;
import util.LogUtil;
import util.StringHelper;

import com.profiles.base.BaseTest;
import com.profiles.pmprofiles.service.PmProFilesService;

//@TransactionConfiguration(defaultRollback = false)
public class PmProFilesServiceTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(PmProFilesServiceTest.class);

	@Autowired
	private PmProFilesService pmProFilesService;

	@Ignore
	@Test
	public void saveExcel() throws Exception {
		pmProFilesService.saveExcel("VIEW_PM_PROFILES.xls");
	}

	@Ignore
	@Test
	public void get_distinct_p_number() {
		List<Map<String, String>> list = pmProFilesService.get_distinct_p_numberAndDocument();
		StringBuilder stringBuilder = new StringBuilder();
		for (Map<String, String> map : list)
			stringBuilder.append(map.get("p_number")).append("\n").append(StringHelper.isEmpty(map.get("document")) ? "空缺" : map.get("document")).append("\n");
		byte[] bytes = stringBuilder.toString().getBytes();
		try {
			FileUtil.writeFile("word.txt", bytes);
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			e.printStackTrace();
		}
	}
}
