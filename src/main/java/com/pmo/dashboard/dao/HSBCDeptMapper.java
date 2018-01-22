package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.HSBCDept;

public interface HSBCDeptMapper
{
    List<HSBCDept> queryHSBCDeptName();
    List<HSBCDept> queryHSBCDubDeptNameById(String hsbcSubDeptId);
    HSBCDept queryHSBCSubDeptById(String hsbcProjectId);
    public List<HSBCDept> queryHSBCSubDeptNameByDeptName(String hsbcDeptName);
    public HSBCDept queryDemandHSBCSubDeptById(String hsbcSubDeptId);
    public List<String> queryHSBCSubDeptId(Demand demand);
    public String queryHsbcSubDeptId(Map<String, Object> params);
	List<HSBCDept> queryAllHSBCDept();
}
