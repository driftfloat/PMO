package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.HSBCDept;

public interface HSBCDeptService
{
    
	
	List<HSBCDept> queryTopParent(HSBCDept hsbcDept);
	
	List<HSBCDept> queryChild(HSBCDept hsbcDept);
	
	List<HSBCDept> queryById(HSBCDept hsbcDept);
	
	
	
	List<HSBCDept> queryHSBCDeptName();
    List<HSBCDept> queryHSBCDubDeptNameById(String hsbcDeptName);
    HSBCDept queryHSBCSubDeptById(String hsbcProjectId);
    List<HSBCDept> queryHSBCSubDeptNameByDeptName(String hsbcDeptName);
    String queryHsbcSubDeptId(String str1,String str2);
    HSBCDept queryDemandHSBCSubDeptById(String hsbcSubDeptId);
    List<HSBCDept> queryAllHSBCDept();
}
