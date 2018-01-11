package com.pmo.dashboard.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.pmo.dashboard.dao.EmployeeLogMapper;
import com.pom.dashboard.service.EmployeeLogService;

@Service
public class EmployeeLogServiceImpl implements EmployeeLogService{
	
	@Resource
	private EmployeeLogMapper employeeLogMapper;

	@SuppressWarnings("unchecked")
	@Override
	public boolean save(Object entity) {
		if(employeeLogMapper.save(entity)>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean update(Object entity) {
		if(employeeLogMapper.update(entity)>0){
		    return true;	
		}
		return false;
	}

	@Override
	public boolean deleteById(Serializable... ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
