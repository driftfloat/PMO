package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.CSDept;

public interface CSDeptService
{
    List<CSDept> queryAllCSDept();
    CSDept  queryCSDeptById(String csSubDeptId);
    List<CSDept> queryAllCSSubDeptName();
    List<CSDept> queryCSSubDeptNameByCsBuName(String csBuName);
    List<CSDept>  queryCSDeptByIds(String[] csSubDeptIds);
	String changeCsSubDeptNameToId(String csSubdeptName);
}
