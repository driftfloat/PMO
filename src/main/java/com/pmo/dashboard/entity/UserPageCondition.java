package com.pmo.dashboard.entity;

public class UserPageCondition extends PageCondition{
	
	private String eHr;
	private String userName;
	private String userType;
	private String bu;
	private String du;
	
	public String geteHr() {
		return eHr;
	}
	public void seteHr(String eHr) {
		this.eHr = eHr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public UserPageCondition(String eHr, String userName, String userType, String bu, String du) {
		super();
		this.eHr = eHr;
		this.userName = userName;
		this.userType = userType;
		this.bu = bu;
		this.du = du;
	}
	public UserPageCondition(String currentPage, String pageCount) {
		super(currentPage, pageCount);
	}
	public UserPageCondition() {
		super();
	}
	
}
