package com.pmo.dashboard.entity;

public class EmployeeLog {
	
	
	
    private String logId;

    private String employeeId;

    private String csSubdeptIdNew;

    private String csSubdeptIdOriginal;

    private String roleNew;

    private String roleOriginal;

    private String statusNew;

    private String statusOriginal;

    private String updateDate;

    private String operationPerson;

    private String changeInformation;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public String getCsSubdeptIdNew() {
        return csSubdeptIdNew;
    }

    public void setCsSubdeptIdNew(String csSubdeptIdNew) {
        this.csSubdeptIdNew = csSubdeptIdNew == null ? null : csSubdeptIdNew.trim();
    }

    public String getCsSubdeptIdOriginal() {
        return csSubdeptIdOriginal;
    }

    public void setCsSubdeptIdOriginal(String csSubdeptIdOriginal) {
        this.csSubdeptIdOriginal = csSubdeptIdOriginal == null ? null : csSubdeptIdOriginal.trim();
    }

    public String getRoleNew() {
        return roleNew;
    }

    public void setRoleNew(String roleNew) {
        this.roleNew = roleNew == null ? null : roleNew.trim();
    }

    public String getRoleOriginal() {
        return roleOriginal;
    }

    public void setRoleOriginal(String roleOriginal) {
        this.roleOriginal = roleOriginal == null ? null : roleOriginal.trim();
    }

    public String getStatusNew() {
        return statusNew;
    }

    public void setStatusNew(String statusNew) {
        this.statusNew = statusNew == null ? null : statusNew.trim();
    }

    public String getStatusOriginal() {
        return statusOriginal;
    }

    public void setStatusOriginal(String statusOriginal) {
        this.statusOriginal = statusOriginal == null ? null : statusOriginal.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson == null ? null : operationPerson.trim();
    }

    public String getChangeInformation() {
        return changeInformation;
    }

    public void setChangeInformation(String changeInformation) {
        this.changeInformation = changeInformation == null ? null : changeInformation.trim();
    }
}