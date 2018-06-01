package com.pmo.dashboard.dao;

import com.pmo.dashboard.entity.EmployeeSkill;

public interface EmployeeSkillMapper {
    int deleteByPrimaryKey(String id);

    int insert(EmployeeSkill record);

    int insertSelective(EmployeeSkill record);

    EmployeeSkill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EmployeeSkill record);

    int updateByPrimaryKey(EmployeeSkill record);
}