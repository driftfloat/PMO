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

import com.pmo.dashboard.entity.HSBCDept;
import com.pom.dashboard.service.HSBCDeptService;

@Controller
@RequestMapping(value="/hsbcDept")
public class HSBCDeptController
{
    private static Logger logger = LoggerFactory
            .getLogger(HSBCDeptController.class);
    
    
    @Resource
    HSBCDeptService hsbcDeptService;
    
    
    @RequestMapping("/queryDeptName")
    @ResponseBody
    public List queryDeptName(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        List<HSBCDept> list = hsbcDeptService.queryHSBCDeptName();
        return list;
    }
    
    
    @RequestMapping("/querySubDeptName")
    @ResponseBody
    public List querySubDeptName(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String hsbcSubDeptId = request.getParameter("hsbcSubDeptId");
        List<HSBCDept> list = hsbcDeptService.queryHSBCDubDeptNameById(hsbcSubDeptId);
        return list;
    }
    
    
    @RequestMapping("/queryHSBCSubDeptById")
    @ResponseBody
    public Object queryHSBCSubDeptById(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String hsbcProjectId = request.getParameter("hsbcProjectId");
        HSBCDept hsbcDept = hsbcDeptService.queryHSBCSubDeptById(hsbcProjectId);
        return hsbcDept;
    }

}
