package com.profiles.pscf.service;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.profiles.pscf.entity.Pscf;
import com.profiles.pscf.repository.PscfDao;

@Component
@Transactional
public class PscfServiceImpl implements PscfService {

	private static final Logger logger = LoggerFactory.getLogger(PscfServiceImpl.class);

	@Autowired
	private PscfDao pscfDao;

	@Override
	public void saveExcel(String filePath) {
		if (StringHelper.isEmpty(filePath))
			return;
		File file = new File(filePath);
		Workbook workbook = null;
		List<Object> list = null;
		try {
			workbook = WorkbookFactory.create(new FileInputStream(file));
			Sheet sheet = workbook.getSheet("总");
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			if (lastRowNum == firstRowNum)
				return;
			Row firstRow = sheet.getRow(0);//表头
			if (firstRow == null)
				return;
			short lastCellNum = firstRow.getLastCellNum();
			for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
				list = new ArrayList<Object>();
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				for (int k = 0; k < lastCellNum; k++) {
					if (k <= 5 || k > 27)
						continue;
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
		Pscf pscf = new Pscf();
		try {
			Class<?> clazz = Pscf.class;
			Field[] fields = clazz.getDeclaredFields();
			Constructor<?> con = null;
			Field field = null;
			Class<?> typeClass = null;
			String typeName = null;
			for (int i = 0; i < fields.length; i++) {
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
				field.set(pscf, con.newInstance(value));
			}
			pscfDao.save(pscf);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LogUtil.stackTraceToString(e));
		}
	}

	@Override
	public List<Map<String, Object>> findByJingDuAndWeiDu(double jingdu, double weidu) {
		return pscfDao.findByJingDuAndWeiDu(jingdu, weidu);
	}

}
