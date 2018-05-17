package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.HSBCDept;

public interface HSBCDeptMapper
{
    
	int insert(HSBCDept hsbcDept);
	
	int update(HSBCDept hsbcDept);
	
	List<HSBCDept> queryTopParent(HSBCDept hsbcDept);
	
	List<HSBCDept> queryChild(HSBCDept hsbcDept);
	
	List<HSBCDept> queryById(HSBCDept hsbcDept);
	
	
	/*List<HSBCDept> queryHSBCDeptName();
    List<HSBCDept> queryHSBCDubDeptNameById(String hsbcSubDeptId);
    HSBCDept queryHSBCSubDeptById(String hsbcProjectId);
    public List<HSBCDept> queryHSBCSubDeptNameByDeptName(String hsbcDeptName);
    public HSBCDept queryDemandHSBCSubDeptById(String hsbcSubDeptId);
    public List<String> queryHSBCSubDeptId(Demand demand);
    public String queryHsbcSubDeptId(Map<String, Object> params);
	List<HSBCDept> queryAllHSBCDept();*/
}
