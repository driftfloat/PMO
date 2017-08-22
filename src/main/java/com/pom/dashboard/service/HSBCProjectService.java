package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.HSBCProject;

public interface HSBCProjectService
{
    
    List<HSBCProject> queryProjectNameById(String hsbcSubDeptId);
    HSBCProject queryProjectName(String hsbcProjectId);

}
