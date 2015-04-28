package com.profiles.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
			System.out.println("success");
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void get_distinct_p_numberAndName() {
		List<Map<String, String>> list = pmProFilesService.get_distinct_p_numberAndName();
		StringBuilder stringBuilder = new StringBuilder();
		for (Map<String, String> map : list)
			stringBuilder.append(map.get("p_number")).append("\n").append(map.get("name")).append("\n");
		byte[] bytes = stringBuilder.toString().getBytes();
		try {
			FileUtil.writeFile("word2.txt", bytes);
			System.out.println("success");
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			e.printStackTrace();
		}
	}

	public static final String[] names = { "WEIGHT_PER", "UNCERTAINT", "SYMBOL" };

	@SuppressWarnings("resource")
	@Test
	public void daochuExcel() {
		List<Map<String, String>> p_numberAndName = pmProFilesService.get_distinct_p_numberAndName();
		if (CollectionUtils.isEmpty(p_numberAndName))
			return;
		List<Map<String, Object>> byP_Number = null;
		String p_number = null;
		String name = null;
		String symbol = null;
		double weight_per = 0;
		double uncertaint = 0;
		HSSFWorkbook workbook = null;
		HSSFRow row = null;
		HSSFCell createCell = null;
		int rowNum = 0;
		try {
			workbook = new HSSFWorkbook();
			HSSFCellStyle style = workbook.createCellStyle();
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框  
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框  
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFSheet sheet = workbook.createSheet();
			for (Map<String, String> map : p_numberAndName) {
				p_number = map.get("p_number");
				name = map.get("name");
				row = sheet.createRow(rowNum);
				row.createCell(0).setCellValue(new HSSFRichTextString(p_number));
				row.createCell(1).setCellValue(new HSSFRichTextString(name));
				rowNum += 1;
				row = sheet.createRow(rowNum);
				for (int i = 0; i < names.length; i++) {
					createCell = row.createCell(i);
					createCell.setCellStyle(style);
					createCell.setCellValue(new HSSFRichTextString(names[i]));
				}
				rowNum += 1;
				byP_Number = pmProFilesService.getByP_Number(p_number);
				if (CollectionUtils.isEmpty(byP_Number))
					return;
				for (Map<String, Object> map2 : byP_Number) {
					weight_per = Double.parseDouble(String.valueOf(map2.get("weight_per")));
					uncertaint = Double.parseDouble(String.valueOf(map2.get("uncertaint")));
					symbol = String.valueOf(map2.get("symbol"));
					row = sheet.createRow(rowNum);
					row.createCell(0).setCellValue(weight_per);
					row.createCell(1).setCellValue(uncertaint);
					row.createCell(2).setCellValue(new HSSFRichTextString(symbol));
					rowNum += 1;
				}
			}
			FileOutputStream fout = new FileOutputStream("profile.xls");
			workbook.write(fout);
			fout.close();
			File file = new File("profile.xls");
			InputStream inputStream = new FileInputStream(file);
			byte[] byteArray = FileUtil.toByteArray(inputStream);
			FileUtil.writeFile("profile.xls", byteArray);
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
			e.printStackTrace();
		}

	}
}
