package com.profiles.pscf.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.profiles.base.IdEntity;

@Entity
@Table(name = "pscf")
public class Pscf extends IdEntity {

	private Integer hour;
	private Double jingdu;
	private Double weidu;
	private String q;
	private String tc;
	private String oc;
	private String ec;
	private String na;
	private String nh4;
	private String cl;
	private String no3;
	private String so4;
	private String al;
	private String si;
	private String ca;
	private String v;
	private String fe;
	private String ni;
	private String zn;
	private String pb;
	private String cd;
	private String s;

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Double getJingdu() {
		return jingdu;
	}

	public void setJingdu(Double jingdu) {
		this.jingdu = jingdu;
	}

	public Double getWeidu() {
		return weidu;
	}

	public void setWeidu(Double weidu) {
		this.weidu = weidu;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public String getOc() {
		return oc;
	}

	public void setOc(String oc) {
		this.oc = oc;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public String getNa() {
		return na;
	}

	public void setNa(String na) {
		this.na = na;
	}

	public String getNh4() {
		return nh4;
	}

	public void setNh4(String nh4) {
		this.nh4 = nh4;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getNo3() {
		return no3;
	}

	public void setNo3(String no3) {
		this.no3 = no3;
	}

	public String getSo4() {
		return so4;
	}

	public void setSo4(String so4) {
		this.so4 = so4;
	}

	public String getAl() {
		return al;
	}

	public void setAl(String al) {
		this.al = al;
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getFe() {
		return fe;
	}

	public void setFe(String fe) {
		this.fe = fe;
	}

	public String getNi() {
		return ni;
	}

	public void setNi(String ni) {
		this.ni = ni;
	}

	public String getZn() {
		return zn;
	}

	public void setZn(String zn) {
		this.zn = zn;
	}

	public String getPb() {
		return pb;
	}

	public void setPb(String pb) {
		this.pb = pb;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

}
