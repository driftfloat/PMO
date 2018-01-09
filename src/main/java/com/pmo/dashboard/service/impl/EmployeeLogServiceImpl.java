package com.pmo.dashboard.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.pmo.dashboard.dao.EmployeeLogMapper;
import com.pmo.dashboard.entity.EmployeeLog;
import com.pom.dashboard.service.EmployeeLogService;

@Service
public class EmployeeLogServiceImpl implements EmployeeLogService{
	
	@Resource
	private EmployeeLogMapper employeeLogMapper;

	@Override
	public boolean add(EmployeeLog log) {
		if(employeeLogMapper.add(log)>0){
			return true;
		}
		return false;
	}

}
