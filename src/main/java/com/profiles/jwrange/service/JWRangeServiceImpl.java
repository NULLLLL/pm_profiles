package com.profiles.jwrange.service;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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

	public List<JWRange> findAll() {
		return (List<JWRange>) jWRangeDao.findAll();
	}

	@Override
	public void selectDataInRange() {
		List<JWRange> list = findAll();
		Class<?> clazz = Pscf.class;
		Field[] fields = clazz.getDeclaredFields();
		Double weidu = 0.00;
		Double jingdu = 0.00;
		Integer q_m = 0;
		Integer tc_m = 0;
		Integer oc_m = 0;
		Integer ec_m = 0;
		Integer na_m = 0;
		Integer nh4_m = 0;
		Integer cl_m = 0;
		Integer no3_m = 0;
		Integer so4_m = 0;
		Integer al_m = 0;
		Integer si_m = 0;
		Integer ca_m = 0;
		Integer v_m = 0;
		Integer fe_m = 0;
		Integer ni_m = 0;
		Integer zn_m = 0;
		Integer pb_m = 0;
		Integer cd_m = 0;
		Integer s_m = 0;
		Integer q_q = 0;
		Integer tc_q = 0;
		Integer oc_q = 0;
		Integer ec_q = 0;
		Integer na_q = 0;
		Integer nh4_q = 0;
		Integer cl_q = 0;
		Integer no3_q = 0;
		Integer so4_q = 0;
		Integer al_q = 0;
		Integer si_q = 0;
		Integer ca_q = 0;
		Integer v_q = 0;
		Integer fe_q = 0;
		Integer ni_q = 0;
		Integer zn_q = 0;
		Integer pb_q = 0;
		Integer cd_q = 0;
		Integer s_q = 0;
		String fieldName = null;
		String value = null;
		List<Map<String, Object>> data = null;
		try {
			for (JWRange jwRange : list) {
				weidu = jwRange.getWeidu();
				jingdu = jwRange.getJingdu();
				q_m = jwRange.getQ_m();
				tc_m = jwRange.getTc_m();
				oc_m = jwRange.getOc_m();
				ec_m = jwRange.getEc_m();
				na_m = jwRange.getNa_m();
				nh4_m = jwRange.getQ_m();
				cl_m = jwRange.getQ_m();
				no3_m = jwRange.getQ_m();
				so4_m = jwRange.getSo4_m();
				al_m = jwRange.getAl_m();
				si_m = jwRange.getSi_m();
				ca_m = jwRange.getCa_m();
				v_m = jwRange.getV_m();
				fe_m = jwRange.getFe_m();
				ni_m = jwRange.getNi_m();
				zn_m = jwRange.getZn_m();
				pb_m = jwRange.getPb_m();
				cd_m = jwRange.getCd_m();
				s_m = jwRange.getS_m();
				q_q = jwRange.getQ_q();
				tc_q = jwRange.getTc_q();
				oc_q = jwRange.getOc_q();
				ec_q = jwRange.getEc_q();
				na_q = jwRange.getNa_q();
				nh4_q = jwRange.getNh4_q();
				cl_q = jwRange.getCl_q();
				no3_q = jwRange.getNo3_q();
				so4_q = jwRange.getSo4_q();
				al_q = jwRange.getAl_q();
				si_q = jwRange.getSi_q();
				ca_q = jwRange.getCa_q();
				v_q = jwRange.getV_q();
				fe_q = jwRange.getFe_q();
				ni_q = jwRange.getNi_q();
				zn_q = jwRange.getZn_q();
				pb_q = jwRange.getPb_q();
				cd_q = jwRange.getCd_q();
				s_q = jwRange.getS_q();

				data = pscfService.findByJingDuAndWeiDu(jingdu, weidu);
				if (CollectionUtils.isEmpty(data))
					continue;
				for (Map<String, Object> map : data) {
					for (Field field : fields) {
						fieldName = field.getName();
						value = String.valueOf(map.get(fieldName));
						if (fieldName.equals("q")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setQ_m(q_m + 1);
									q_m = q_m + 1;
								} else {
									jwRange.setQ_q(q_q + 1);
									q_q = q_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("tc")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setTc_m(tc_m + 1);
									tc_m = tc_m + 1;
								} else {
									jwRange.setTc_q(tc_q + 1);
									tc_q = tc_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("oc")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setOc_m(oc_m + 1);
									oc_m = oc_m + 1;
								} else {
									jwRange.setOc_q(oc_q + 1);
									oc_q = oc_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("ec")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setEc_m(ec_m + 1);
									ec_m = ec_m + 1;
								} else {
									jwRange.setEc_q(ec_q + 1);
									ec_q = ec_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("na")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setNa_m(na_m + 1);
									na_m = na_m + 1;
								} else {
									jwRange.setNa_q(na_q + 1);
									na_q = na_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("nh4")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setNh4_m(nh4_m + 1);
									nh4_m = nh4_m + 1;
								} else {
									jwRange.setNh4_q(nh4_q + 1);
									nh4_q = nh4_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("cl")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setCl_m(cl_m + 1);
									cl_m = cl_m + 1;
								} else {
									jwRange.setCl_q(cl_q + 1);
									cl_q = cl_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("no3")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setNo3_m(no3_m + 1);
									no3_m = no3_m + 1;
								} else {
									jwRange.setNo3_q(no3_q + 1);
									no3_q = no3_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("so4")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setSo4_m(so4_m + 1);
									so4_m = so4_m + 1;
								} else {
									jwRange.setSo4_q(so4_q + 1);
									so4_q = so4_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("al")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setAl_m(al_m + 1);
									al_m = al_m + 1;
								} else {
									jwRange.setAl_q(al_q + 1);
									al_q = al_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("si")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setSi_m(si_m + 1);
									si_m = si_m + 1;
								} else {
									jwRange.setSi_q(si_q + 1);
									si_q = si_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("ca")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setCa_m(ca_m + 1);
									ca_m = ca_m + 1;
								} else {
									jwRange.setCa_q(ca_q + 1);
									ca_q = ca_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("v")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setV_m(v_m + 1);
									v_m = v_m + 1;
								} else {
									jwRange.setV_q(v_q + 1);
									v_q = v_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("fe")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setFe_m(fe_m + 1);
									fe_m = fe_m + 1;
								} else {
									jwRange.setFe_q(fe_q + 1);
									fe_q = fe_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("ni")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setNi_m(ni_m + 1);
									ni_m = ni_m + 1;
								} else {
									jwRange.setNi_q(ni_q + 1);
									ni_q = ni_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("zn")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setZn_m(zn_m + 1);
									zn_m = zn_m + 1;
								} else {
									jwRange.setZn_q(zn_q + 1);
									zn_q = zn_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("pb")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setPb_m(pb_m + 1);
									pb_m = pb_m + 1;
								} else {
									jwRange.setPb_q(pb_q + 1);
									pb_q = pb_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("cd")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setCd_m(cd_m + 1);
									cd_m = cd_m + 1;
								} else {
									jwRange.setCd_q(cd_q + 1);
									cd_q = cd_q + 1;
								}
								continue;
							}
						} else if (fieldName.equals("s")) {
							if (StringHelper.isEmpty(value))
								continue;
							else {
								if (value.startsWith("m")) {
									jwRange.setS_m(s_m + 1);
									s_m = s_m + 1;
								} else {
									jwRange.setS_q(s_q + 1);
									s_q = s_q + 1;
								}
								continue;
							}
						}
					}
					jWRangeDao.save(jwRange);
				}
			}
		} catch (Exception e) {
			logger.error(LogUtil.stackTraceToString(e));
		}
	}
}
