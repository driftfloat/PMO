package com.pmo.dashboard.api.reqmodel;



/**
 * 请求model
 * @author Devin
 * @since 2018-1-26
 * @version 1.0
 *
 */
public class EmployeeSynReqmodel {
	
	
	private String userName;//认证用户
	private String password;//认证密码
	private String systemId;//系统标识（SpiderNet）
	private String lastUpdateTime;//上次更新日期
	private String operationCode;//操作类型1.QE:员工数据同步
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
	

}
