package com.pmo.dashboard.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.pmo.dashboard.dao.EmployeeLogMapper;
import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.EmployeeLogPageCondition;
import com.pom.dashboard.service.EmployeeLogService;

@Service
public class EmployeeLogServiceImpl implements EmployeeLogService{
	
	@Resource
	private EmployeeLogMapper employeeLogMapper;

	@Override
	public boolean save(Object entity) {
		if(employeeLogMapper.save(entity)>0){
			return true;
		}
		return false;
	}

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
	public Object getById(String logId) {
		return employeeLogMapper.getById(logId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeLog> getLogByEmployeeID(String id) {
		return employeeLogMapper.getLogByEmployeeID(id);
	}

	@Override
	public int countEmployeeLogList(EmployeeLogPageCondition employeeLog) {
		int countAll = employeeLogMapper.countEmployeeLogList(employeeLog);
		int pageRecordsNum = employeeLog.getPageRecordsNum();
		int pageNumber = 0;

		if (countAll % pageRecordsNum == 0) {
			pageNumber = countAll / pageRecordsNum;
		} else {
			pageNumber = countAll / pageRecordsNum + 1;
		}
		return pageNumber;
	}

	@Override
	public List<EmployeeLog> queryEmployeeLogList(EmployeeLogPageCondition employeeLog) {
		return employeeLogMapper.queryEmployeeLogList(employeeLog);
	}


}
