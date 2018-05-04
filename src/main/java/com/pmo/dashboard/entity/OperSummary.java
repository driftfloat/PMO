package com.pmo.dashboard.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OperSummary {
	private String id;
	private String departmentName;
	private String type;
	private String remark;
	private BigDecimal yearTotal = BigDecimal.ZERO;
//	private String year;
//	private String month;
	private Map<String,BigDecimal> months;
	private BigDecimal ifaw;
	private BigDecimal infOt;
	private BigDecimal infPt;
	private BigDecimal infAd;
	private BigDecimal infTravel;
	private BigDecimal infEquipment;
	private BigDecimal infSub;
	private BigDecimal invalid;
	private BigDecimal effectiveSt;
	private BigDecimal invalidSt;
	
	public BigDecimal getIfaw() {
		return ifaw;
	}
	public void setIfaw(BigDecimal ifaw) {
		this.ifaw = ifaw;
	}
	public BigDecimal getInfOt() {
		return infOt;
	}
	public void setInfOt(BigDecimal infOt) {
		this.infOt = infOt;
	}
	public BigDecimal getInfPt() {
		return infPt;
	}
	public void setInfPt(BigDecimal infPt) {
		this.infPt = infPt;
	}
	public BigDecimal getInfAd() {
		return infAd;
	}
	public void setInfAd(BigDecimal infAd) {
		this.infAd = infAd;
	}
	public BigDecimal getInfTravel() {
		return infTravel;
	}
	public void setInfTravel(BigDecimal infTravel) {
		this.infTravel = infTravel;
	}
	public BigDecimal getInfEquipment() {
		return infEquipment;
	}
	public void setInfEquipment(BigDecimal infEquipment) {
		this.infEquipment = infEquipment;
	}
	public BigDecimal getInfSub() {
		return infSub;
	}
	public void setInfSub(BigDecimal infSub) {
		this.infSub = infSub;
	}
	public BigDecimal getInvalid() {
		return invalid;
	}
	public void setInvalid(BigDecimal invalid) {
		this.invalid = invalid;
	}
	public BigDecimal getEffectiveSt() {
		return effectiveSt;
	}
	public void setEffectiveSt(BigDecimal effectiveSt) {
		this.effectiveSt = effectiveSt;
	}
	public BigDecimal getInvalidSt() {
		return invalidSt;
	}
	public void setInvalidSt(BigDecimal invalidSt) {
		this.invalidSt = invalidSt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Map<String, BigDecimal> getMonths() {
		return months;
	}
	public void setMonths(Map<String, BigDecimal> months) {
		this.months = months;
	}
	public BigDecimal getYearTotal() {
		return yearTotal;
	}
	public void setYearTotal(BigDecimal yearTotal) {
		this.yearTotal = yearTotal;
	}
}
