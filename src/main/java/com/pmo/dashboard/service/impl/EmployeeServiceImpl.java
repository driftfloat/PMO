package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pom.dashboard.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public boolean addEmployee(Employee employee)
    {
        if(employeeMapper.addEmployee(employee)>0){
            return true;
        }
        return false;
    }

    @Override
    public Employee queryEmployeeById(String employeeId)
    {
        Employee employee =  employeeMapper.queryEmployeeById(employeeId);
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee)
    {
        if(employeeMapper.updateEmployee(employee)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> queryEmployeeList(EmployeePageCondition employeePageCondition)
    {
        List<Employee> list = employeeMapper.queryEmployeeList(employeePageCondition);
        return list;
    }

}
