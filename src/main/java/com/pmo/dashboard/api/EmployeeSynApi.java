package com.pmo.dashboard.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.api.constant.ErrorConstant;
import com.pmo.dashboard.api.constant.ResultConstant;
import com.pmo.dashboard.api.reqmodel.EmployeeSynReqmodel;
import com.pmo.dashboard.api.respmodel.EmployeeSynRespmodel;
import com.pmo.dashboard.api.templete.ResponseTemplete;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.UserService;



/**
 * 员工数据同步接口
 * @author Devin
 * @since 2018-1-26
 * @version 1.0
 *
 */
@Controller
@RequestMapping(value="syncEmployInfo")
public class EmployeeSynApi {
	
	@Resource
	private UserService userService;
	
	@Resource
    private EmployeeService employeeService;
	
	
	@RequestMapping(value="",method=RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> syncEmployInfo(@RequestBody EmployeeSynReqmodel reqmodel){
		Map<String,Object> resmap = new HashMap<String,Object>();
		List<String> errorList = new ArrayList<String>();
		ResponseTemplete resTem = new ResponseTemplete();
		/**
		 * 登录权限认证
		 */
		boolean certificationFlag = false;
		User user = null;
		System.out.println("请求到接口=========================="+reqmodel.getUserName());
		try{
			user = userService.login(reqmodel.getUserName(),reqmodel.getPassword());
		}catch(Exception e){
			resTem.setResult(ResultConstant.Fail);
			errorList.add(ErrorConstant.Exception);
			resTem.setErrorList(errorList);
			
			return resmap;
		}
		
		if(user!=null){
			if(!"0".equals(user.getLoginStatus())){
				//认证失败
				certificationFlag = false;
				errorList.add(ErrorConstant.CertificationFail);
				resTem.setErrorList(errorList);
				resTem.setResult(ResultConstant.Fail);
				
				return resmap;
			}
			//认证成功
			certificationFlag = true;
		}else{
			//认证失败
			System.out.println("认证失败==========================");
			certificationFlag = false;
			errorList.add(ErrorConstant.CertificationFail);
			resTem.setErrorList(errorList);
			resTem.setResult(ResultConstant.Fail);
			
			return resmap;
		}
		
		/**
		 * 认证成功同步员工信息
		 */
		if(certificationFlag){
			try{
				List<Employee> emp = employeeService.getAllEmployee();
				List<Object> resmodelList = changeModelList(emp);
				resTem.setResult(ResultConstant.Success);
				resTem.setDataList(resmodelList);
				
				return resmap;
			}catch(Exception e){
				resTem.setResult(ResultConstant.Fail);
				errorList.add(ErrorConstant.Exception);
				resTem.setErrorList(errorList);
				
				return resmap;
			}
		}
		return resmap;
	}
	
	
	private EmployeeSynRespmodel changeModel(Employee emp){
		EmployeeSynRespmodel resemp =  new EmployeeSynRespmodel();
		if(emp != null){
			resemp.setSkill(emp.getSkill());
			resemp.setOrgName("");
			resemp.setHrNum(emp.getLob());
			resemp.seteUserName(emp.getLn());
			resemp.setErNum(emp.geteHr());
			resemp.setcUserName(emp.getStaffName());
			resemp.setBuName("");
		}
		return resemp;
	}

	private List<Object> changeModelList(List<Employee> empList){
		List<Object> respList = new ArrayList<Object>();
		if(empList != null && empList.size()>0){
			for(int i=0;i<empList.size();i++){
				EmployeeSynRespmodel model = changeModel(empList.get(i));
				respList.add(model);
			}
		}
		return respList;
	}
}
