package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.EmployeeLogPageCondition;

/**
 * 员工日志
 * @author Devin
 *
 */
@SuppressWarnings("rawtypes")
public interface EmployeeLogService extends BaseLogService{
	
	
	List<EmployeeLog> getLogByEmployeeID(String id);
	int countEmployeeLogList(EmployeeLogPageCondition employeeLog);
	List<EmployeeLog> queryEmployeeLogList(EmployeeLogPageCondition employeeLog);
	

}
