package com.pmo.dashboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.HSBCProject;
import com.pom.dashboard.service.HSBCProjectService;

@Controller
@RequestMapping(value="/hsbcProject")
public class HSBCProjectController
{
    private static Logger logger = LoggerFactory
            .getLogger(HSBCDeptController.class);
    
    
    @Resource
    private HSBCProjectService hsbcProjectService;
    
    
    @RequestMapping("/queryprojcetName")
    @ResponseBody
    public List queryprojcetName(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String hsbcSubDeptId = request.getParameter("hsbcSubDeptId"); 
        List<HSBCProject> list = hsbcProjectService.queryProjectNameById(hsbcSubDeptId);
        return list;
    }

    
    @RequestMapping("/selectProjectName")
    @ResponseBody
    public Object queryProjectName(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String hsbcProjectId = request.getParameter("hsbcProjectId"); 
        HSBCProject hsbcProject = hsbcProjectService.queryProjectName(hsbcProjectId);
        return hsbcProject;
    }
}
