package com.profiles.jwrange.service;

import java.util.List;

public interface JWRangeService {

	void saveExcel(String filePath);

	void saveData(List<Object> data);

	void selectDataInRange();

	void export(String pathname);

	void setValueZero();

}
