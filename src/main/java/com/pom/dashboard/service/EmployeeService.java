package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;

public interface EmployeeService
{
    boolean addEmployee(Employee employee);
    Employee queryEmployeeById(String employeeId);
    boolean updateEmployee(Employee employee);
    List<Employee> queryEmployeeList(EmployeePageCondition employeePageCondition);
}
