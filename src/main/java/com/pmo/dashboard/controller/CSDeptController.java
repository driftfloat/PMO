package com.pmo.dashboard.controller;

import java.util.ArrayList;
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

import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.CSDeptService;

@Controller
@RequestMapping(value = "/csDept")
public class CSDeptController {
	private static Logger logger = LoggerFactory.getLogger(CSDeptController.class);

	@Resource
	private CSDeptService csDeptService;

	@RequestMapping("/queryCSDeptById")
	@ResponseBody
	public Object queryCSDeptById(final HttpServletRequest request, final HttpServletResponse response) {
		String csSubDeptId = request.getParameter("csSubDeptId");
		CSDept csDept = csDeptService.queryCSDeptById(csSubDeptId);
		return csDept;
	}

	@RequestMapping("/queryAllCSSubDeptName")
	@ResponseBody
	public Object queryAllCSSubDeptName(final HttpServletRequest request, final HttpServletResponse response) {
		List<CSDept> list = csDeptService.queryAllCSSubDeptName();
		return list;
	}

	@RequestMapping("/queryAllCSSubDept")
	@ResponseBody
	public Object queryAllCSSubDept(final HttpServletRequest request, final HttpServletResponse response) {
		
		User user = (User) request.getSession().getAttribute("loginUser");
		
		Map<String, Object> result = new HashMap<String, Object>();

		List<CSDept> list = csDeptService.queryAllCSDept();

		List<CSDept> cSDepts = csDeptService.queryCSDeptByIds(user.getCsDeptId().split(","));

		result.put("user", user);
		result.put("data", list);
		result.put("csSubDepts", cSDepts);
		return result;
	}

}
