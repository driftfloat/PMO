package com.pmo.dashboard.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.api.constant.ErrorConstant;
import com.pmo.dashboard.api.constant.ResultConstant;
import com.pmo.dashboard.api.reqmodel.EmployeeSynReqmodel;
import com.pmo.dashboard.api.respmodel.EmployeeSynRespmodel;
import com.pmo.dashboard.entity.ApiUser;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pom.dashboard.service.CSDeptService;
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
@RequestMapping(value="/syncEmployInfo")
public class EmployeeSynApi {
	
	@Resource
	private UserService userService;
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private CSDeptService cSDeptService;
	
	
	@RequestMapping(value="",method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> syncEmployInfo(@RequestBody String requetObj){
		JSONObject jsonObj = new JSONObject(requetObj);
		Map<String,Object> resmap = new HashMap<String,Object>();
		List<String> errorList = new ArrayList<String>();
		List<EmployeeSynRespmodel> dataList = new ArrayList<>();
		EmployeeSynReqmodel reqmodel = new EmployeeSynReqmodel();
		reqmodel.setUserName(jsonObj.getString("userName"));
		reqmodel.setPassword(jsonObj.getString("password"));
		reqmodel.setSystemId(jsonObj.getString("systemId"));
		reqmodel.setLastUpdateTime(jsonObj.getString("lastUpdateTime"));

		
		/**
		 * 登录权限认证
		 */
		ApiUser apiUser = null;
		System.out.println("请求到接口=========================="+reqmodel.getUserName());
		try{
			apiUser = userService.loginApiUser(reqmodel.getUserName(), reqmodel.getPassword(), reqmodel.getSystemId());
		
			if(apiUser!= null){
				//认证成功
					
				//put result 
				resmap.put("result", ResultConstant.Success);
					
				//put errorList
				resmap.put("errorList", errorList);
					
				//put dataList
				List<Employee> emp = employeeService.getEmployeeByLastUpdateTime(reqmodel.getLastUpdateTime());
				dataList = changeModelList(emp);
				resmap.put("dataList", dataList);
					
				return resmap;
			}else{
				//认证失败
				System.out.println("认证失败==========================");
				//put result 
				resmap.put("result", ResultConstant.Fail);
				
				//put errorList
				errorList.add(ErrorConstant.CertificationFail);
				resmap.put("errorList", errorList);
				
				//put dataList
				resmap.put("dataList", dataList);
				
				return resmap;
			}
		
		}catch(Exception e){
			//put result 
			resmap.put("result", ResultConstant.Fail);
			
			//put errorList
			errorList.add(ErrorConstant.Exception);
			resmap.put("errorList", errorList);
			
			//put dataList
			resmap.put("dataList", dataList);
			
			return resmap;
		}
		
	}
	
	
	private EmployeeSynRespmodel changeModel(Employee emp,List<CSDept> cSDepts){
		EmployeeSynRespmodel resemp =  new EmployeeSynRespmodel();
		CSDept empCSDept = null;
		if(emp != null){
			resemp.setSkill(emp.getSkill());
			resemp.setHrNum(emp.getLob());
			resemp.seteUserName(emp.getLn());
			resemp.setErNum(emp.geteHr());
			resemp.setcUserName(emp.getStaffName());
			
			//get Employee CSDept
			if(emp.getCsSubDept()!=null&&emp.getCsSubDept()!=""){
				for (CSDept csDept : cSDepts) {
					if(csDept.getCsSubDeptId().equals(emp.getCsSubDept())){
						empCSDept = csDept;
						break;
					}
				}
			}
			
			//set orgName and buName 
			if(empCSDept!=null){
				resemp.setBuName(empCSDept.getCsBuName());
				resemp.setOrgName(empCSDept.getCsSubDeptName());
			}else{
				resemp.setOrgName("");
				resemp.setBuName("");
			}
		}
		return resemp;
	}

	private List<EmployeeSynRespmodel> changeModelList(List<Employee> empList){
		
		//get all CSDept
		List<CSDept> cSDepts = cSDeptService.queryAllCSDept();
		
		List<EmployeeSynRespmodel> dataList = new ArrayList<EmployeeSynRespmodel>();
		if(empList != null && empList.size()>0){
			for(int i=0;i<empList.size();i++){
				EmployeeSynRespmodel model = changeModel(empList.get(i),cSDepts);
				dataList.add(model);
			}
		}
		return dataList;
	}
}
