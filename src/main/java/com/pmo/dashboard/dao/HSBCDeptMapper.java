package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.HSBCDept;

public interface HSBCDeptMapper
{
    List<HSBCDept> queryHSBCDeptName();
    List<HSBCDept> queryHSBCDubDeptNameById(String hsbcSubDeptId);
    HSBCDept queryHSBCSubDeptById(String hsbcProjectId);
}
