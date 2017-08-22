package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.HSBCProject;

public interface HSBCProjectMapper
{
    List<HSBCProject> queryProjectNameById(String hsbcSubDeptId);
    HSBCProject queryProjectName(String hsbcProjectId);
}
