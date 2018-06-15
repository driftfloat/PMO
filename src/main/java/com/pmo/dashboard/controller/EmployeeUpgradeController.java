package com.pmo.dashboard.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeeUpgradeRecord;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.EmployeeUpgradeService;

@Controller
@RequestMapping(value="/employee/upgrade")
public class EmployeeUpgradeController {
	
	
	
	@Resource
	private EmployeeUpgradeService employeeUpgradeService;
	@Resource
	private EmployeeService employeeService;
	
	private ObjectMapper objectMapper = new ObjectMapper();  
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getUpgradeRecord/{id}")
	@ResponseBody
	public String getUpgradeRecord(int pageSize,int pageNumber,@PathVariable("id") String id,HttpServletRequest request) throws JsonProcessingException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeUpgradeRecord eur = new EmployeeUpgradeRecord();
		eur.setEmployeeId(id);
		//第一个参数当前页码，第二个参数每页条数
		PageHelper.startPage(pageNumber,pageSize);  
		List<EmployeeUpgradeRecord> list = employeeUpgradeService.queryById(eur);
		/**
		 * 处理员工信息
		 */
		Employee em = employeeService.queryEmployeeById(id);
		for(int i=0;i<list.size();i++){
			if(em!=null){
				list.get(i).setEhr(em.geteHr());
				list.get(i).setStaffid(em.getHsbcStaffId());
				list.get(i).setLob(em.getLob());
			}
			list.get(i).setStringcreatedate(sf.format(list.get(i).getCreateDate()));
			list.get(i).setStringeffectivedate(sf.format(list.get(i).getEffectDate()));
		}
		Map map = new HashMap();
		PageInfo<EmployeeUpgradeRecord> page = new PageInfo(list);
		map.put("total",page.getTotal());
		map.put("rows", list);
		return objectMapper.writeValueAsString(map);
	}
	
	
	@RequestMapping(value="/{saveUpgradeRecord}", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveUpgradeRecord(EmployeeUpgradeRecord eur,HttpServletRequest request) throws JsonProcessingException, ParseException{
		User user = (User) request.getSession().getAttribute("loginUser");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		eur.setCreateDate(new Date());
		eur.setEffectDate(sf.parse(eur.getStringeffectivedate()));
		eur.setId(Utils.getUUID());
		eur.setOperateId(user.getUserId());
		int i = employeeUpgradeService.save(eur);
		if(i>0){
			return true;
		}
		return false;
	}

}
