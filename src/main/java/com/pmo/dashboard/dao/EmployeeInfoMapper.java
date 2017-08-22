package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeInfo;
import com.pmo.dashboard.entity.EmployeePageCondition;

public interface EmployeeInfoMapper
{
    List<EmployeeInfo> queryEmployeeList(EmployeePageCondition employeePageCondition);
    int countEmployeeList(EmployeePageCondition employeePageCondition);
}
