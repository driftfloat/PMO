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
    public List<HSBCDept> queryHSBCDeptName()
    {
        List<HSBCDept> list = hsbcDeptMapper.queryHSBCDeptName();
        return list;
    }

    @Override
    public List<HSBCDept> queryHSBCDubDeptNameById(String hsbcSubDeptId)
    {
        List<HSBCDept> list = hsbcDeptMapper.queryHSBCDubDeptNameById(hsbcSubDeptId);
        return list;
    }

    @Override
    public HSBCDept queryHSBCSubDeptById(String hsbcProjectId)
    {
        HSBCDept hsbcDept = hsbcDeptMapper.queryHSBCSubDeptById(hsbcProjectId);
        return hsbcDept;
    }

}
