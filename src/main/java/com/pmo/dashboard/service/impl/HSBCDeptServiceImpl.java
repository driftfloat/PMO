package com.pmo.dashboard.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.entity.HSBCDept;
import com.pom.dashboard.service.HSBCDeptService;

@Service
public class HSBCDeptServiceImpl implements HSBCDeptService
{

    @Resource
    private HSBCDeptMapper hsbcDeptMapper;
    

	@Override
	public List<HSBCDept> queryTopParent(HSBCDept hsbcDept) {
		return hsbcDeptMapper.queryTopParent(hsbcDept);
	}

	@Override
	public List<HSBCDept> queryChild(HSBCDept hsbcDept) {
		return hsbcDeptMapper.queryChild(hsbcDept);
	}

	@Override
	public List<HSBCDept> queryById(HSBCDept hsbcDept) {
		return hsbcDeptMapper.queryById(hsbcDept);
	}
}
