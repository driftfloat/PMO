package com.pmo.dashboard.api.respmodel;

/**
 * 响应model
 * @author Devin
 * @since 2018-1-26
 * @version 1.0
 *
 */
public class EmployeeSynRespmodel {
	
	
	private String erNum;//Ehr
	private String hrNum;//lob
	private String cUserName;//staffname
	private String eUserName;//LN
	private String skill;//skill
	private String orgName;//事业部名称
	private String buName;//交付部名称
	 
	 
	public String getErNum() {
		return erNum;
	}
	public void setErNum(String erNum) {
		this.erNum = erNum;
	}
	public String getHrNum() {
		return hrNum;
	}
	public void setHrNum(String hrNum) {
		this.hrNum = hrNum;
	}
	public String getcUserName() {
		return cUserName;
	}
	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}
	public String geteUserName() {
		return eUserName;
	}
	public void seteUserName(String eUserName) {
		this.eUserName = eUserName;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getBuName() {
		return buName;
	}
	public void setBuName(String buName) {
		this.buName = buName;
	}
	 

}
