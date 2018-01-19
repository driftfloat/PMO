package com.pmo.dashboard.entity;

public class EmployeeLogPageCondition extends PageCondition{
	
	
	private String employeeID;
    private int pageRecordsNum;
    
    
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public int getPageRecordsNum() {
		return pageRecordsNum;
	}
	public void setPageRecordsNum(int pageRecordsNum) {
		this.pageRecordsNum = pageRecordsNum;
	}  
    
}
