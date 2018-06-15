package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeUpgradeRecord;

public interface EmployeeUpgradeService {
	
	
	
	List<EmployeeUpgradeRecord> queryById(EmployeeUpgradeRecord record);
	
	int save(EmployeeUpgradeRecord record);

}
