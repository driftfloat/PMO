package com.pmo.dashboard.entity;

import java.util.List;

public class EmployeeGraphParam {

	private String startDate;
	
	private String endDate;
	
	private String bu;
	
	private String du;
	
	private List<Integer> duList;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getDu() {
		return du;
	}

	public void setDu(String du) {
		this.du = du;
	}

	public List<Integer> getDuList() {
		return duList;
	}

	public void setDuList(List<Integer> duList) {
		this.duList = duList;
	}
	
	
}
