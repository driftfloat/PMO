package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.CSDept;

public interface CSDeptMapper
{
    List<CSDept> queryAllCSDept();
    CSDept  queryCSDeptById(String csSubDeptId);
    List<CSDept> queryAllCSSubDeptName();
    List<CSDept> queryCSSubDeptNameByCsBuName(String csBuName);
    List<CSDept> queryCSDeptByIds(String[] csSubDeptIds);
}
