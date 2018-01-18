package com.pmo.dashboard.entity;

public class EmployeeLog {
	
	
    private String logId;//日志ID
    private String employeeId;//员工ID
    private String csSubdeptIdNew;//新交付部
    private String csSubdeptIdOriginal;//原交付部
    private String roleNew;//新角色
    private String roleOriginal;//原角色
    private String statusNew;//新状态
    private String statusOriginal;//原状态
    private String updateDate;//更新时间
    private String operationPerson;//用户ID(userid)
    private String changeInformation;//修改具体字段拼接信息
    private String logType;//日志类型(0:add,1:update)
    
    //表外字段
    private String operationPersonName;
    private String csSubdeptIdNewName;
    private String csSubdeptIdOriginalName;
    

    public String getCsSubdeptIdNewName() {
		return csSubdeptIdNewName!=null?csSubdeptIdNewName:"";
	}

	public void setCsSubdeptIdNewName(String csSubdeptIdNewName) {
		this.csSubdeptIdNewName = csSubdeptIdNewName;
	}

	public String getCsSubdeptIdOriginalName() {
		return csSubdeptIdOriginalName!=null?csSubdeptIdOriginalName:"";
	}

	public void setCsSubdeptIdOriginalName(String csSubdeptIdOriginalName) {
		this.csSubdeptIdOriginalName = csSubdeptIdOriginalName;
	}

	public String getOperationPersonName() {
		return operationPersonName;
	}

	public void setOperationPersonName(String operationPersonName) {
		this.operationPersonName = operationPersonName;
	}

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
        return statusNew!=null?statusNew:"";
    }

    public void setStatusNew(String statusNew) {
        this.statusNew = statusNew == null ? null : statusNew.trim();
    }

    public String getStatusOriginal() {
        return statusOriginal!=null?statusOriginal:"";
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
        return changeInformation!=null?changeInformation:"";
    }

    public void setChangeInformation(String changeInformation) {
        this.changeInformation = changeInformation == null ? null : changeInformation;
    }
    
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }
}