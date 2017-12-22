package com.pmo.dashboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pmo.dashboard.entity.TransferDept;
import com.pom.dashboard.service.EmployeeModifyService;

@Controller
@RequestMapping(value="/employee")
public class EmployeeModifyController {
	@Resource
	private EmployeeModifyService employeeModifyService;
	
    @SuppressWarnings("unused")
	@RequestMapping("/propertiesModify")
    @ResponseBody
    public boolean updateProperties(HttpServletRequest request,TransferDept transferDept){   
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		if(!employeeModifyService.modifyProperties(transferDept)){
    			result = false;
    		}
    	}
        return result;
    }
    @SuppressWarnings("unused")
	@RequestMapping("/rolesModify")
    @ResponseBody
    public boolean updateRoles(HttpServletRequest request,TransferDept transferDept){ 
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		if(!employeeModifyService.modifyRoles(transferDept)){
    			result = false;
    		}
    	}
        return result;
    }
    @SuppressWarnings("unused")
	@RequestMapping("/deptModify")
    @ResponseBody
    public boolean updateDept(HttpServletRequest request,TransferDept transferDept){   
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		if(!employeeModifyService.modifyDept(transferDept)){
    			result = false;
    		}
    	}
        return result;
    }
    
    @RequestMapping("/updateRM")
    @ResponseBody
    public boolean updateRM(HttpServletRequest request,TransferDept transferDept) {
    	boolean result = true;
    	String staffIds=transferDept.getStaffIds();
    	String[] staffs=staffIds.split(",");
    	for(int i=0;i<staffs.length;i++){
    		transferDept.setEmployeeId(staffs[i]);
    		if(!employeeModifyService.modifyRM(transferDept)){
    			result = false;
    		}
    	}
    	return result;
    }
}