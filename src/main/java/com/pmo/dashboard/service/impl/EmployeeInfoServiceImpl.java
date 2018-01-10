package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.EmployeeInfoMapper;
import com.pmo.dashboard.entity.EmployeeInfo;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.EmployeeInfoService;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService
{

    @Resource
    private EmployeeInfoMapper employeeInfoMapper;
    
    @Override
    public List<EmployeeInfo> queryEmployeeList(EmployeePageCondition employeePageCondition)
    {
        List<EmployeeInfo> list =  employeeInfoMapper.queryEmployeeList(employeePageCondition);
        return list;
    }

    @Override
    public int countEmployeeList(EmployeePageCondition employeePageCondition)
    {
        int pageNumber = employeeInfoMapper.countEmployeeList(employeePageCondition)/employeePageCondition.getPageRecordsNum() + Constants.ONE;
        return pageNumber;
    }

}
