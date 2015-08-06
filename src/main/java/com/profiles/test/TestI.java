package com.profiles.test;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.profiles.base.IdEntity;

@Entity
@Table(name = "test_i")
public class TestI extends IdEntity {

	private String name;

	private int name1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getName1() {
		return name1;
	}

	public void setName1(int name1) {
		this.name1 = name1;
	}

}
