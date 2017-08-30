package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.CSDept;

public interface CSDeptService
{
    List<CSDept> queryCSDeptName();
    List<CSDept> queryCSSubDeptName(String csSubDeptId);
    CSDept  queryCSDeptById(String csSubDeptId);
    List<CSDept> queryAllCSSubDeptName();
    public List<CSDept> queryCSSubDeptNameByCsBuName(String csBuName);
}
