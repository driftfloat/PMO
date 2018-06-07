package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeSkillMapper;
import com.pmo.dashboard.entity.EmployeeSkill;
import com.pom.dashboard.service.EmployeeSkillService;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {
	@Resource
	private EmployeeSkillMapper employeeSkillMapper;
	
	@Override
	public boolean delete(String id) {
		if(employeeSkillMapper.deleteByPrimaryKey(id)>0){
            return true;
        }
		return false;
	}

	@Override
	public List<EmployeeSkill> detail(String eHr) {
		return employeeSkillMapper.detail(eHr);
	}

	@Override
	public boolean insert(EmployeeSkill record) {
		if(employeeSkillMapper.insertSelective(record)>0){
            return true;
        }
		return false;
	}

	@Override
	public EmployeeSkill select(String id) { 
		return employeeSkillMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(EmployeeSkill record) {
		if(employeeSkillMapper.updateByPrimaryKeySelective(record)>0){
            return true;
        }
		return false;
	}

	@Override
	public List<EmployeeSkill> query(EmployeeSkill condition) {
		return employeeSkillMapper.query(condition);
	}

	@Override
	public List<String> skills() {
		return employeeSkillMapper.skills();
	}

}
