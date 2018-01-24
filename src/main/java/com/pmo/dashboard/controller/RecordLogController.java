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
            final HttpServletResponse response,EmployeeLogPageCondition employeeLog) throws JsonProcessingException
    {
        String pageState = employeeLog.getPageState();
        System.out.println("变更类型======================"+employeeLog.getChType());
        if(employeeLog.getChType()!=null && !"".equals(employeeLog.getChType())){
        	if(employeeLog.getChType().equals("0")){
            	employeeLog.setProjectStatus("0");
            }
            if(employeeLog.getChType().equals("1")){
            	employeeLog.setContractStatus("0");
            }
            if(employeeLog.getChType().equals("2")){
            	employeeLog.setLevelStatus("0");
            }
            if(employeeLog.getChType().equals("3")){
            	employeeLog.setNewdept("3");
            }
            if(employeeLog.getChType().equals("4")){
            	employeeLog.setNewstatus("4");
            }
        }
        
        Employee employee = employeeService.queryEmployeeById(employeeLog.getEmployeeId());
		int pageRecordsNum = Constants.TEN;
		if (employeeLog.getPageRecordsNum()!=null) {
			pageRecordsNum = employeeLog.getPageRecordsNum();
		}
        
        int countPage = 0;
        String currentPage = null;
        
        if("".equals(pageState) || pageState == null){
            currentPage = "0";
            employeeLog.setEmployeeId(employeeLog.getEmployeeId());
            employeeLog.setPageRecordsNum(pageRecordsNum);
            employeeLog.setCurrentPage(currentPage);
            countPage = employeeLogService.countEmployeeLogList(employeeLog);
            employeeLog.setPageCount(countPage+"");
            request.getSession().setAttribute("employeeLog", employeeLog);
        }else if("frist".equals(pageState)){
            currentPage = "0";
            employeeLog = (EmployeeLog) request.getSession().getAttribute("employeeLog");
            employeeLog.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLog", employeeLog);
        }else if("next".equals(pageState)){
        	employeeLog = (EmployeeLog) request.getSession().getAttribute("employeeLog");
            currentPage = Integer.parseInt(employeeLog.getCurrentPage()) + employeeLog.getPageRecordsNum()+"";
            employeeLog.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLog", employeeLog);
        }else if("previous".equals(pageState)){
        	employeeLog = (EmployeeLog) request.getSession().getAttribute("employeeLog");
            currentPage = Integer.parseInt(employeeLog.getCurrentPage()) - employeeLog.getPageRecordsNum() +"";
            employeeLog.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLog", employeeLog);
        }else if("last".equals(pageState)){
        	employeeLog = (EmployeeLog) request.getSession().getAttribute("employeeLog");
            currentPage = (Integer.parseInt(employeeLog.getPageCount()) - Constants.ONE) * employeeLog.getPageRecordsNum() +"";
            employeeLog.setCurrentPage(currentPage);
            request.getSession().setAttribute("employeeLog", employeeLog);
        }
        List<EmployeeLog> list=null; 
        try{
        	list = employeeLogService.queryEmployeeLogList(employeeLog);
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        for(int i=0;i<list.size();i++){
        	User u=null;
        	CSDept duNew=null;
        	CSDept duOld=null;
        	if(list.get(i).getOperationPerson()!=null && !"".equals(list.get(i).getOperationPerson())){
        		u = userService.queryUserById(list.get(i).getOperationPerson());
        	}
            if(list.get(i).getCsSubdeptIdNew()!=null && !"".equals(list.get(i).getCsSubdeptIdNew())){
            	duNew = csDeptService.queryCSDeptById(list.get(i).getCsSubdeptIdNew());
        	}
            if(list.get(i).getCsSubdeptIdOriginal()!=null && !"".equals(list.get(i).getCsSubdeptIdOriginal())){
            	duOld = csDeptService.queryCSDeptById(list.get(i).getCsSubdeptIdOriginal());
        	}
            
            if(u!=null){
            	list.get(i).setOperationPersonName(u.getNickname());
            }
            if(duNew!=null){
            	list.get(i).setCsSubdeptIdNewName(duNew.getCsBuName());
            }
            if(duOld!=null){
            	list.get(i).setCsSubdeptIdOriginalName(duOld.getCsBuName());
            }
        }
        
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", list);
        result.put("pageInfo", request.getSession().getAttribute("employeeLog"));
        result.put("employee",employee);
        
        return result;
    }
	
	@RequestMapping("/queryEmployeeLogById")
	@ResponseBody
	public EmployeeLog queryEmployeeLogById(String employeeLogId,HttpServletRequest request) {
		EmployeeLog log = (EmployeeLog) employeeLogService.getById(employeeLogId);
		User u=null;
		CSDept duNew=null;
		CSDept duOld=null;
		if(log.getOperationPerson()!=null && !"".equals(log.getOperationPerson())){
			u = userService.queryUserById(log.getOperationPerson());
		}
        if(log.getCsSubdeptIdNew()!=null && !"".equals(log.getCsSubdeptIdNew())){
        	duNew = csDeptService.queryCSDeptById(log.getCsSubdeptIdNew());
		}
        if(log.getCsSubdeptIdOriginal()!=null && !"".equals(log.getCsSubdeptIdOriginal())){
        	duOld = csDeptService.queryCSDeptById(log.getCsSubdeptIdOriginal());
		}
        
        if(u!=null){
        	log.setOperationPersonName(u.getUserName());
        }
        if(duNew!=null){
        	log.setCsSubdeptIdNewName(duNew.getCsBuName());
        }
        if(duOld!=null){
        	log.setCsSubdeptIdOriginalName(duOld.getCsBuName());
        }
		return log;
	}

}
