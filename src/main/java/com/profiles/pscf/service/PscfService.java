package com.profiles.pscf.service;

import java.util.List;
import java.util.Map;

public interface PscfService {

	void saveExcel(String filePath);

	List<Map<String, Object>> findByJingDuAndWeiDu(double jingdu, double weidu);

}
