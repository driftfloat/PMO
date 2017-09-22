package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.HSBCDept;

public interface HSBCDeptService
{
    List<HSBCDept> queryHSBCDeptName();
    List<HSBCDept> queryHSBCDubDeptNameById(String hsbcSubDeptId);
    HSBCDept queryHSBCSubDeptById(String hsbcProjectId);
    List<HSBCDept> queryHSBCSubDeptNameByDeptName(String hsbcDeptName);
    String queryHsbcSubDeptId(String str1,String str2);
    HSBCDept queryDemandHSBCSubDeptById(String hsbcSubDeptId);
}
