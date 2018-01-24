package com.pmo.dashboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pmo.dashboard.entity.EmployeeGraphParam;
import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.EmployeeLogPageCondition;

public interface EmployeeLogMapper extends BaseLogMapper<Object, Long>{
	
	
	List<EmployeeLog> getLogByEmployeeID(String id);
	
	int countEmployeeLogList(EmployeeLogPageCondition employeeLogPageCondition);
	
	List<EmployeeLog> queryEmployeeLogList(EmployeeLogPageCondition employeeLogPageCondition);
	
	// Felix, 180118, Begin.
    List<EmployeeLog> queryEmpLogByDUNew(EmployeeGraphParam employeeGraphParam);
    
    List<EmployeeLog> queryEmpLogByDUOrg(@Param("startDate")String startDate, @Param("endDate")String endDate, @Param("du")String du);
    // Felix,180118, End.
	
}