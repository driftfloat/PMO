package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeUpgradeRecordMapper;
import com.pmo.dashboard.entity.EmployeeUpgradeRecord;
import com.pom.dashboard.service.EmployeeUpgradeService;



@Service
public class EmployeeUpgradeServiceImpl implements EmployeeUpgradeService{
	
	
	
	@Resource
	private EmployeeUpgradeRecordMapper employeeUpgradeRecordMapper;

	@Override
	public List<EmployeeUpgradeRecord> queryById(EmployeeUpgradeRecord record) {
		return employeeUpgradeRecordMapper.queryById(record);
	}

	@Override
	public int save(EmployeeUpgradeRecord record) {
		return employeeUpgradeRecordMapper.insert(record);
	}

}
