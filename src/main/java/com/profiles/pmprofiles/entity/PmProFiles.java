package com.profiles.pmprofiles.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.profiles.base.IdEntity;

@Entity
@Table(name = "profiles")
public class PmProFiles extends IdEntity {

	private String p_number;

	private String name;

	private double lower_size;

	private double upper_size;

	private double weight_per;

	private double uncertaint;

	private String symbol;

	private String document;

	public String getP_number() {
		return p_number;
	}

	public void setP_number(String p_number) {
		this.p_number = p_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLower_size() {
		return lower_size;
	}

	public void setLower_size(double lower_size) {
		this.lower_size = lower_size;
	}

	public double getUpper_size() {
		return upper_size;
	}

	public void setUpper_size(double upper_size) {
		this.upper_size = upper_size;
	}

	public double getWeight_per() {
		return weight_per;
	}

	public void setWeight_per(double weight_per) {
		this.weight_per = weight_per;
	}

	public double getUncertaint() {
		return uncertaint;
	}

	public void setUncertaint(double uncertaint) {
		this.uncertaint = uncertaint;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

}
