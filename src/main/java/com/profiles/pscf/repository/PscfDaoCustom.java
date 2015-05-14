package com.profiles.pscf.repository;

import java.util.List;
import java.util.Map;

public interface PscfDaoCustom {

	List<Map<String, Object>> findByJingDuAndWeiDu(double jingdu, double weidu);

}
