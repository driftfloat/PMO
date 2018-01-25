package com.pmo.dashboard.entity;

public class EmployeeLogPageCondition extends PageCondition{
	
	
	private String projectStatus;//项目状态(0:有更改)
    private String contractStatus;//合同状态(0:有更改)
    private String levelStatus;//级别状态(0:有更改)
    private String newdept;
    private String newstatus;
    private String employeeId;//员工ID
    private Integer pageRecordsNum;
    private String pageState;
    
    private String chType;//更改类型
    
    
	
	
	public String getNewdept() {
		return newdept;
	}
	public void setNewdept(String newdept) {
		this.newdept = newdept;
	}
	public String getNewstatus() {
		return newstatus;
	}
	public void setNewstatus(String newstatus) {
		this.newstatus = newstatus;
	}
	public String getChType() {
		return chType;
	}
	public void setChType(String chType) {
		this.chType = chType;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public String getLevelStatus() {
		return levelStatus;
	}
	public void setLevelStatus(String levelStatus) {
		this.levelStatus = levelStatus;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getPageRecordsNum() {
		return pageRecordsNum;
	}
	public void setPageRecordsNum(Integer pageRecordsNum) {
		this.pageRecordsNum = pageRecordsNum;
	}
	public String getPageState() {
		return pageState;
	}
	public void setPageState(String pageState) {
		this.pageState = pageState;
	}
    
     
    
}
