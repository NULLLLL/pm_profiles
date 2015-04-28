package com.profiles.pmprofiles.service;

import java.util.List;
import java.util.Map;

import com.profiles.pmprofiles.entity.PmProFiles;

public interface PmProFilesService {

	void save(PmProFiles pmProFiles);

	void save(String p_number, String name, double lower_size, double upper_size, double weight_per, double uncertaint, String symbol, String document);

	void saveExcel(String filePath);

	List<Map<String, String>> get_distinct_p_numberAndDocument();

	List<Map<String, String>> get_distinct_p_numberAndName();

	List<Map<String, Object>> getByP_Number(String p_number);

	List<Map<String, Object>> getListForTable(String params);
}
