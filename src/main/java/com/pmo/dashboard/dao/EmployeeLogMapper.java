package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.EmployeeLogPageCondition;


public interface EmployeeLogMapper extends BaseLogMapper<Object, Long>{
	
	
	List<EmployeeLog> getLogByEmployeeID(String id);
	
	int countEmployeeLogList(EmployeeLogPageCondition employeeLog);
	
	List<EmployeeLog> queryEmployeeLogList(EmployeeLogPageCondition employeeLog);
	
}