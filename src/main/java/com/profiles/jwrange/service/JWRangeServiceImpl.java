package com.profiles.jwrange.service;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import util.FileUtil;
import util.LogUtil;
import util.StringHelper;

import com.profiles.jwrange.entity.JWRange;
import com.profiles.jwrange.repository.JWRangeDao;

@Component
@Transactional
public class JWRangeServiceImpl implements JWRangeService {

	private static final Logger logger = LoggerFactory.getLogger(JWRangeServiceImpl.class);

	@Autowired
	private JWRangeDao jWRangeDao;

	@Override
	public void saveExcel(String filePath) {
		if (StringHelper.isEmpty(filePath))
			return;
		File file = new File(filePath);
		Workbook workbook = null;
		List<Object> list = null;
		try {
			workbook = WorkbookFactory.create(new FileInputStream(file));
			Sheet sheet = workbook.getSheet("格子");
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			if (lastRowNum == firstRowNum)
				return;
			Row firstRow = sheet.getRow(0);//表头
			if (firstRow == null)
				return;
			short lastCellNum = firstRow.getLastCellNum();
			for (int i = firstRowNum + 2; i <= lastRowNum; i++) {
				list = new ArrayList<Object>();
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				for (int k = 0; k < lastCellNum; k++) {
					if (k >= 2)
						break;
					Cell cell = row.getCell(k);
					String cellValue = FileUtil.getCellFormatValue(cell);
					list.add(cellValue);
				}
				saveData(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LogUtil.stackTraceToString(e));
		}

	}

	@Override
	public void saveData(List<Object> data) {
		JWRange range = new JWRange();
		try {
			Class<?> clazz = JWRange.class;
			Field[] fields = clazz.getDeclaredFields();
			Constructor<?> con = null;
			Field field = null;
			Class<?> typeClass = null;
			String typeName = null;
			for (int i = 0; i < fields.length; i++) {
				if (i >= 2)
					break;
				Object value = data.get(i);
				field = fields[i];
				typeClass = field.getType();
				typeName = typeClass.getName();
				if (typeName.contains("Integer")) {
					con = typeClass.getConstructor(int.class);
					value = Integer.parseInt(String.valueOf(value));
				} else if (typeName.contains("Double")) {
					con = typeClass.getConstructor(double.class);
					value = Double.parseDouble(String.valueOf(value));
				} else {
					con = typeClass.getConstructor(typeClass);
					value = String.valueOf(value);
				}
				field.setAccessible(true);
				field.set(range, con.newInstance(value));
			}
			jWRangeDao.save(range);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LogUtil.stackTraceToString(e));
		}
	}

}
