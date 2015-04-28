package com.profiles.base;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "action")
public class Action extends IdEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
