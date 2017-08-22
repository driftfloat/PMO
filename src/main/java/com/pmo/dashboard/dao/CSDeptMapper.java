package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.CSDept;

public interface CSDeptMapper
{
    List<CSDept> queryCSDeptName();
    List<CSDept> queryCSSubDeptName(String csSubDeptId);
    CSDept  queryCSDeptById(String csSubDeptId);
    List<CSDept> queryAllCSSubDeptName();
}
