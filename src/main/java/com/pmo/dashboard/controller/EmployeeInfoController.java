package com.pmo.dashboard.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.EmployeeInfo;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.EmployeeInfoService;

@Controller
@RequestMapping(value="/employeeInfo")
public class EmployeeInfoController
{
    private static Logger logger = LoggerFactory
            .getLogger(EmployeeInfoController.class);
    
    @Resource
    private EmployeeInfoService employeeInfoService;

    
    @RequestMapping("/queryEmployeeList")
    @ResponseBody
    public Object queryEmployeeList(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String pageState = request.getParameter("pageState");
        
        String hsbcStaffId = request.getParameter("hsbcStaffId");
        
        String eHr = request.getParameter("eHr");
        
        String csDeptName = request.getParameter("csDeptName");
        
        String csSubDeptName = request.getParameter("csSubDeptName");
        
        String csBuName = request.getParameter("csBuName");
        
        int countPage = 0;
        
        String currentPage = null;
        
        EmployeePageCondition employeePageCondition = new EmployeePageCondition();
        
        if("".equals(pageState) || pageState == null){
            currentPage = "0";
            employeePageCondition.setHsbcStaffId(hsbcStaffId);
            employeePageCondition.seteHr(eHr);
            employeePageCondition.setCsSubDeptName(csSubDeptName);
            employeePageCondition.setCsDeptName(csDeptName);
            employeePageCondition.setCsbuName(csBuName);
            employeePageCondition.setCurrentPage(currentPage);
            countPage = employeeInfoService.countEmployeeList(employeePageCondition);
            employeePageCondition.setPageCount(countPage+"");
            request.getSession().setAttribute("employeePageCondition", employeePageCondition);
        }else if("frist".equals(pageState)){
            currentPage = "0";
            employeePageCondition = (EmployeePageCondition) request.getSession().getAttribute("employeePageCondition");
            employeePageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeePageCondition", employeePageCondition);
        }else if("next".equals(pageState)){
            employeePageCondition = (EmployeePageCondition) request.getSession().getAttribute("employeePageCondition");
            currentPage = Integer.parseInt(employeePageCondition.getCurrentPage()) + Constants.TEN +"";
            employeePageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeePageCondition", employeePageCondition);
        }else if("previous".equals(pageState)){
            employeePageCondition = (EmployeePageCondition) request.getSession().getAttribute("employeePageCondition");
            currentPage = Integer.parseInt(employeePageCondition.getCurrentPage()) - Constants.TEN +"";
            employeePageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeePageCondition", employeePageCondition);
        }else if("last".equals(pageState)){
            employeePageCondition = (EmployeePageCondition) request.getSession().getAttribute("employeePageCondition");
            currentPage = (Integer.parseInt(employeePageCondition.getPageCount()) - Constants.ONE) * Constants.TEN +"";
            employeePageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeePageCondition", employeePageCondition);
        }
        
        List<EmployeeInfo> list = employeeInfoService.queryEmployeeList(employeePageCondition);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", list);
        result.put("pageInfo", request.getSession().getAttribute("employeePageCondition"));
        return result;
    }
    
    
    @RequestMapping("/setEmpConditon")
    @ResponseBody
    public void setEmpConditon(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String csDeptName = request.getParameter("csDeptName");
        String csSubDeptName = request.getParameter("csSubDeptName");
        String csBuName = request.getParameter("csBuName");
        String condition = request.getParameter("condition");
        
        String[] excelCondition = condition.split(",");
        
        List<String> conditionList = Arrays.asList(excelCondition);
        
        
        EmployeePageCondition empListCondition = new EmployeePageCondition();
        
        empListCondition.setCsDeptName(csDeptName);
        empListCondition.setCsSubDeptName(csSubDeptName);
        empListCondition.setCsbuName(csBuName);
        request.getSession().setAttribute("empListCondition", empListCondition);
        
        request.getSession().setAttribute("conditionList", conditionList);
        
    }
    
}
