package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.EmployeeLogPageCondition;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeLogService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.UserService;

/**
 * 日志
 * @author Devin
 * @since 2018-1-12
 *
 */
@Controller
@RequestMapping(value="/employee")
public class RecordLogController {
	
	@Resource
	private EmployeeLogService employeeLogService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CSDeptService csDeptService;
	
	@Resource
	private EmployeeService employeeService;
	
	@RequestMapping("/recordLog")
    public String employeeInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/recordLog";
    }
	
	@RequestMapping("/employeeLogInfo")
    public String employeeLogInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {
		String employeeId = request.getParameter("employeeId");
		request.setAttribute("employeeId", employeeId);
		return "employee/employeeLogList";
    }
	
	@RequestMapping("/employeeLogDetail")
    public String employeeLogDetail(final HttpServletRequest request,
            final HttpServletResponse response)
    {
		String employeeLogId = request.getParameter("employeeLogId");
		request.setAttribute("employeeLogId", employeeLogId);
		return "employee/employeeLogDetail";
    }
	
	@RequestMapping("/queryEmployeeLogByID")
    @ResponseBody
    public Object queryEmployeeLogByID(final HttpServletRequest request,
            final HttpServletResponse response,String employeeID) throws JsonProcessingException
    {
        String pageState = request.getParameter("pageState");
        
        Employee employee = employeeService.queryEmployeeById(employeeID);
		int pageRecordsNum = Constants.TEN;
		if (request.getParameter("pageRecordsNum") != null) {
			pageRecordsNum = Integer.parseInt(request.getParameter("pageRecordsNum"));
		}
        @SuppressWarnings("unused")
		User user = (User) request.getSession().getAttribute("loginUser");
        int countPage = 0;
        String currentPage = null;
        EmployeeLogPageCondition employeeLogPageCondition = new EmployeeLogPageCondition();
        if("".equals(pageState) || pageState == null){
            currentPage = "0";
            employeeLogPageCondition.setEmployeeID(employeeID);
            employeeLogPageCondition.setPageRecordsNum(pageRecordsNum);
            employeeLogPageCondition.setCurrentPage(currentPage);
            countPage = employeeLogService.countEmployeeLogList(employeeLogPageCondition);
            employeeLogPageCondition.setPageCount(countPage+"");
            request.getSession().setAttribute("employeeLogPageCondition", employeeLogPageCondition);
        }else if("frist".equals(pageState)){
            currentPage = "0";
            employeeLogPageCondition = (EmployeeLogPageCondition) request.getSession().getAttribute("employeeLogPageCondition");
            employeeLogPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLogPageCondition", employeeLogPageCondition);
        }else if("next".equals(pageState)){
        	employeeLogPageCondition = (EmployeeLogPageCondition) request.getSession().getAttribute("employeeLogPageCondition");
            currentPage = Integer.parseInt(employeeLogPageCondition.getCurrentPage()) + employeeLogPageCondition.getPageRecordsNum()+"";
            employeeLogPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLogPageCondition", employeeLogPageCondition);
        }else if("previous".equals(pageState)){
        	employeeLogPageCondition = (EmployeeLogPageCondition) request.getSession().getAttribute("employeeLogPageCondition");
            currentPage = Integer.parseInt(employeeLogPageCondition.getCurrentPage()) - employeeLogPageCondition.getPageRecordsNum() +"";
            employeeLogPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLogPageCondition", employeeLogPageCondition);
        }else if("last".equals(pageState)){
        	employeeLogPageCondition = (EmployeeLogPageCondition) request.getSession().getAttribute("employeeLogPageCondition");
            currentPage = (Integer.parseInt(employeeLogPageCondition.getPageCount()) - Constants.ONE) * employeeLogPageCondition.getPageRecordsNum() +"";
            employeeLogPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLogPageCondition", employeeLogPageCondition);
        }
        
        List<EmployeeLog> list = employeeLogService.queryEmployeeLogList(employeeLogPageCondition);
        for(int i=0;i<list.size();i++){
        	User u = userService.queryUserById(list.get(i).getOperationPerson());
        	CSDept duNew = csDeptService.queryCSDeptById(list.get(i).getCsSubdeptIdNew());
        	CSDept duOld = csDeptService.queryCSDeptById(list.get(i).getCsSubdeptIdOriginal());
        	list.get(i).setOperationPersonName(u.getUserName());
        	list.get(i).setCsSubdeptIdNewName(duNew.getCsBuName());
        	list.get(i).setCsSubdeptIdOriginalName(duOld.getCsBuName());
        }
        
        Map<String,Object> result = new HashMap<String,Object>();
    
        result.put("data", list);
        result.put("pageInfo", request.getSession().getAttribute("employeeLogPageCondition"));
        result.put("employee",employee);
        
        return result;
    }
	
	@RequestMapping("/queryEmployeeLogById")
	@ResponseBody
	public EmployeeLog queryEmployeeLogById(String employeeLogId,HttpServletRequest request) {
		EmployeeLog log = (EmployeeLog) employeeLogService.getById(employeeLogId);
		User u = userService.queryUserById(log.getOperationPerson());
    	CSDept duNew = csDeptService.queryCSDeptById(log.getCsSubdeptIdNew());
    	CSDept duOld = csDeptService.queryCSDeptById(log.getCsSubdeptIdOriginal());
    	log.setOperationPersonName(u.getUserName());
    	log.setCsSubdeptIdNewName(duNew.getCsBuName());
    	log.setCsSubdeptIdOriginalName(duOld.getCsBuName());
		return log;
	}

}
