package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.EmployeeUpgradeRecord;

public interface EmployeeUpgradeRecordMapper {
   
    int insert(EmployeeUpgradeRecord record);

    List<EmployeeUpgradeRecord> queryById(EmployeeUpgradeRecord record);

    int update(EmployeeUpgradeRecord record);
}