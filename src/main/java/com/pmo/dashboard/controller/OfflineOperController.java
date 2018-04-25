package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.OfflineOperService;

@Controller
@RequestMapping(value="/offlineOper")
public class OfflineOperController {
	private static Logger logger = LoggerFactory
            .getLogger(OfflineOperController.class);
	private ObjectMapper objectMapper = new ObjectMapper();  
	
	@Resource
	private OfflineOperService offlineOperService;
	
	@Resource
	private CSDeptService csDeptService;
	
	@Resource
    private EmployeeService employeeService;
	
	
	@RequestMapping("/listPage")
	public String listPage(){
		return "offline/offline";
	}
	
	/**
	 * 查询数据
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/query")
	@ResponseBody
	public String query(int pageSize,int pageNumber,OfflineOper condition,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		List<OfflineOper> data = offlineOperService.query(condition,user,pageSize,pageNumber);
		PageInfo<OfflineOper> page = new PageInfo(data);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotal());
		map.put("rows", data);
		return objectMapper.writeValueAsString(map);
	}
	
	/**
	 * 保存数据
	 * @param offo
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value= {"/save"},method=RequestMethod.POST)
	@ResponseBody
	public String save(@RequestBody OfflineOper offlineOper,HttpServletRequest request) throws JsonProcessingException{
		User user = (User) request.getSession().getAttribute("loginUser");
		boolean rtn = offlineOperService.save(offlineOper,user);
		return objectMapper.writeValueAsString( rtn? "0" : "-1");
	}
}
