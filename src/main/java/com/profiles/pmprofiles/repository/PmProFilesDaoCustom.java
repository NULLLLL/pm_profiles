package com.profiles.pmprofiles.repository;

import java.util.List;
import java.util.Map;

public interface PmProFilesDaoCustom {

	List<Map<String, String>> get_distinct_p_numberAndDocument();

	List<Map<String, String>> get_distinct_p_numberAndName();

	List<Map<String, Object>> getByP_Number(String p_number);

	List<Map<String, Object>> getListForTable(String params);

}
