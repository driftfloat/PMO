package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CSDeptMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pom.dashboard.service.CSDeptService;

@Service
public class CSDeptServiceImpl implements CSDeptService
{

    @Resource
    private CSDeptMapper csDeptMapper;
    

    @Override
    public CSDept queryCSDeptById(String csSubDeptId)
    {
        CSDept csDept = csDeptMapper.queryCSDeptById(csSubDeptId);
        return csDept;
    }

    @Override
    public List<CSDept> queryAllCSSubDeptName()
    {
        List<CSDept> list = csDeptMapper.queryAllCSSubDeptName();
        return list;
    }

	@Override
	public List<CSDept> queryCSSubDeptNameByCsBuName(String csBuName) {
		List<CSDept> list = csDeptMapper.queryCSSubDeptNameByCsBuName(csBuName);
		return list;
	}

    @Override
    public List<CSDept> queryAllCSDept()
    {
        List<CSDept> list = csDeptMapper.queryAllCSDept();
        return list;
    }

	@Override
	public List<CSDept> queryCSDeptByIds(String[] csSubDeptIds) {
		List<CSDept> csDepts = csDeptMapper.queryCSDeptByIds(csSubDeptIds);
		return csDepts;
	}

	@Override
	public String changeCsSubDeptNameToId(String csSubdeptName) {
		List<CSDept> allCSDept = queryAllCSDept();
		String csSubdeptId = null;
		for (CSDept csDept : allCSDept) {
			if(csDept.getCsSubDeptName().equals(csSubdeptName)){
				csSubdeptId = csDept.getCsSubDeptId();
				break;
			}
		}
		return csSubdeptId;
	}


}
