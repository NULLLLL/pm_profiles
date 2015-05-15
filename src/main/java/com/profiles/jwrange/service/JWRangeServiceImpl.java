package com.profiles.jwrange.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.profiles.pscf.entity.Pscf;
import com.profiles.pscf.service.PscfService;

@Component
@Transactional
public class JWRangeServiceImpl implements JWRangeService {

	private static final Logger logger = LoggerFactory.getLogger(JWRangeServiceImpl.class);

	@Autowired
	JWRangeDao jWRangeDao;

	@Autowired
	PscfService pscfService;

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
			delAll();//先清空数据库
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
					if (cellValue.equals("-"))
						cellValue = "";
					list.add(cellValue);
				}
				saveData(list);
			}
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
		}

	}

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

	public List<JWRange> findAll() {
		return (List<JWRange>) jWRangeDao.findAll();
	}

	public void delAll() {
		jWRangeDao.deleteAll();
	}

	@Override
	public void selectDataInRange() {
		setValueZero();
		List<JWRange> list = findAll();
		Class<?> pscfClazz = Pscf.class;
		Class<?> jwRangeClazz = JWRange.class;
		Field[] pscfFields = pscfClazz.getDeclaredFields();
		Double weidu = 0.00;
		Double jingdu = 0.00;
		String pscfFieldName = null;
		String value = null;
		List<Map<String, Object>> data = null;
		try {
			for (JWRange jwRange : list) {
				weidu = jwRange.getWeidu();
				jingdu = jwRange.getJingdu();
				data = pscfService.findByJingDuAndWeiDu(jingdu, weidu);
				if (CollectionUtils.isEmpty(data))
					continue;
				for (Map<String, Object> map : data) {
					for (Field pscfField : pscfFields) {
						pscfFieldName = pscfField.getName();
						if (pscfFieldName.equals("hour") || pscfFieldName.equals("weidu") || pscfFieldName.equals("jingdu"))
							continue;
						value = String.valueOf(map.get(pscfFieldName));
						if (StringHelper.isEmpty(value))
							continue;
						pscfFieldName = pscfFieldName.replaceFirst(pscfFieldName.substring(0, 1), pscfFieldName.substring(0, 1).toUpperCase());
						if (value.startsWith("m")) {
							Method getMethod = jwRangeClazz.getDeclaredMethod("get" + pscfFieldName + "_m");
							Integer invoke = Integer.parseInt(getMethod.invoke(jwRange).toString());
							Method setMethod = jwRangeClazz.getDeclaredMethod("set" + pscfFieldName + "_m", Integer.class);
							setMethod.invoke(jwRange, invoke + 1);
						} else if (value.startsWith("q")) {
							Method getMethod = jwRangeClazz.getDeclaredMethod("get" + pscfFieldName + "_q");
							Integer invoke = Integer.parseInt(getMethod.invoke(jwRange).toString());
							Method setMethod = jwRangeClazz.getDeclaredMethod("set" + pscfFieldName + "_q", Integer.class);
							setMethod.invoke(jwRange, invoke + 1);
						} else
							continue;
						jWRangeDao.save(jwRange);
					}
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
		}
	}

	@Override
	public void export(String pathname) {
		HSSFWorkbook workbook = null;
		HSSFRow row = null;
		int rowNum = 0;
		try {
			List<Map<String, Object>> data = jWRangeDao.findAllData();
			if (CollectionUtils.isEmpty(data))
				return;
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("格子2");
			int cellNum = 0;
			Class<?> clazz = JWRange.class;
			Field[] fields = clazz.getDeclaredFields();
			int flag = 0;
			for (Map<String, Object> map : data) {
				row = sheet.createRow(rowNum);
				if (flag == 32) {
					rowNum++;
					flag = 0;
					row = sheet.createRow(rowNum);
				}
				for (Field field : fields) {
					String name = field.getName();
					if (cellNum < 2)
						row.createCell(cellNum).setCellValue(Double.parseDouble(String.valueOf(map.get(name))));
					else
						row.createCell(cellNum).setCellValue(Integer.parseInt(String.valueOf(map.get(name))));
					cellNum++;
				}
				cellNum = 0;
				rowNum++;
				flag++;
			}
			FileOutputStream fout = new FileOutputStream(pathname);
			workbook.write(fout);
			fout.close();
			File file = new File(pathname);
			InputStream inputStream = new FileInputStream(file);
			byte[] byteArray = FileUtil.toByteArray(inputStream);
			FileUtil.writeFile(pathname, byteArray);
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
		}

	}

	public void setValueZero() {
		List<JWRange> list = findAll();
		Class<?> clazz = JWRange.class;
		Field[] fields = clazz.getDeclaredFields();
		for (JWRange jwRange : list) {
			int i = 0;
			for (Field field : fields) {
				if (i < 2) {
					i++;
					continue;
				}
				try {
					field.setAccessible(true);
					field.set(jwRange, 0);
					i++;
				} catch (Exception e) {
					logger.error(LogUtil.stackTraceToString(e));
				}
			}
			jWRangeDao.save(jwRange);
		}
	}

}
