package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeGraphParam;
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
	int countEmployeeLogList(EmployeeLogPageCondition employeeLogPageCondition);
	List<EmployeeLog> queryEmployeeLogList(EmployeeLogPageCondition employeeLogPageCondition);
	// Felix, 180118, Begin.
	List<EmployeeLog> queryEmpLogByDUNew(EmployeeGraphParam graphParam);
		
	List<EmployeeLog> queryEmpLogByDUOrg(String startDate, String endDate, String du);
	// Felix, 180118, End.
	

}
