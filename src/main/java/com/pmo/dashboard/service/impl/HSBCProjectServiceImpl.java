package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.HSBCProjectMapper;
import com.pmo.dashboard.entity.HSBCProject;
import com.pom.dashboard.service.HSBCProjectService;

@Service
public class HSBCProjectServiceImpl implements HSBCProjectService
{
    
    @Resource
    private HSBCProjectMapper hsbcProjectMapper;

    @Override
    public List<HSBCProject> queryProjectNameById(String hsbcSubDeptId)
    {
        List<HSBCProject> list = hsbcProjectMapper.queryProjectNameById(hsbcSubDeptId);
        return list;
    }

    @Override
    public HSBCProject queryProjectName(String hsbcProjectId)
    {
        HSBCProject hsbcProject = hsbcProjectMapper.queryProjectName(hsbcProjectId);
        return hsbcProject;
    }
    
    

}
