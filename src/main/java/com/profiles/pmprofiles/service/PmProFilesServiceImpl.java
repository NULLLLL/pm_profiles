package com.profiles.pmprofiles.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.FileUtil;
import util.StringHelper;

import com.profiles.pmprofiles.entity.PmProFiles;
import com.profiles.pmprofiles.repository.PmProFilesDao;

@Component
@Transactional(readOnly = true)
public class PmProFilesServiceImpl implements PmProFilesService {

	@Autowired
	private PmProFilesDao pmProFilesDao;

	@Override
	@Transactional(readOnly = false)
	public void save(PmProFiles pmProFiles) {
		if (pmProFiles == null)
			return;
		pmProFilesDao.save(pmProFiles);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(String p_number, String name, double lower_size, double upper_size, double weight_per, double uncertaint, String symbol, String document) {
		if (StringHelper.isEmpty(name))
			return;
		PmProFiles pmProFiles = new PmProFiles();
		pmProFiles.setDocument(document);
		pmProFiles.setLower_size(lower_size);
		pmProFiles.setName(name);
		pmProFiles.setP_number(p_number);
		pmProFiles.setSymbol(symbol);
		pmProFiles.setUncertaint(uncertaint);
		pmProFiles.setUpper_size(upper_size);
		pmProFiles.setWeight_per(weight_per);
		pmProFilesDao.save(pmProFiles);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveExcel(String filePath) {
		if (StringHelper.isEmpty(filePath))
			return;
		File file = new File(filePath);
		Workbook workbook = null;
		List<String> list = null;
		try {
			workbook = WorkbookFactory.create(new FileInputStream(file));
			Sheet sheet = workbook.getSheetAt(0);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			if (lastRowNum == firstRowNum)
				return;
			Row firstRow = sheet.getRow(0);
			if (firstRow == null)
				return;
			short lastCellNum = firstRow.getLastCellNum();
			for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
				list = new ArrayList<String>();
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				for (int k = 0; k < lastCellNum; k++) {
					Cell cell = row.getCell(k);
					String cellValue = FileUtil.getCellFormatValue(cell);
					list.add(cellValue);
				}
				save(list.get(0), list.get(1), Double.parseDouble(list.get(2)), Double.parseDouble(list.get(3)), Double.parseDouble(list.get(4)),
						Double.parseDouble(list.get(5)), list.get(6), list.get(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Map<String, String>> get_distinct_p_numberAndDocument() {
		return pmProFilesDao.get_distinct_p_numberAndDocument();
	}

	@Override
	public List<Map<String, String>> get_distinct_p_numberAndName() {
		return pmProFilesDao.get_distinct_p_numberAndName();
	}

	@Override
	public List<Map<String, Object>> getByP_Number(String p_number) {
		if (StringHelper.isEmpty(p_number))
			return null;
		return pmProFilesDao.getByP_Number(p_number);
	}
}