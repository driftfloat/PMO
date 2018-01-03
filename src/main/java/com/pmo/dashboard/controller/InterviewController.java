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

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.InterviewService;

@Controller
@RequestMapping(value = "/interview")
public class InterviewController {

	@Resource
	private InterviewService interviewService;

	@RequestMapping("/interviewRecordInfo")
	public String interviewRecordInfo(HttpServletRequest request, HttpServletResponse response) {
		return "candidate/interviewRecordInfo";
	}

	@RequestMapping("/getCandidateList")
	@ResponseBody
	public Object getCandidateList(CandidateInfo candidate, HttpServletRequest request) {

		String pageState = candidate.getPageState();
		PageCondition page = new PageCondition();

		int dataCount = interviewService.getCandidateListCount(candidate);
		page.setDataCount(dataCount + "");
		page.setPageCount((dataCount - 1) / 10 + 1 + "");
		page.setPageDataCount(Constants.PAGE_DATA_COUNT + "");
		if ("".equals(pageState) || pageState == null || "frist".equals(pageState)) {
			page.setCurrentPage("1");
		} else if ("next".equals(pageState)) {
			page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage()) + 1 + "");
		} else if ("previous".equals(pageState)) {
			page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage()) - 1 + "");
		} else if ("last".equals(pageState)) {
			page.setCurrentPage(page.getPageCount());
		}

		candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage()) - 1) * Constants.PAGE_DATA_COUNT + "");
		candidate.setPageDataCount(Constants.PAGE_DATA_COUNT + "");
		List<CandidateInfo> list = interviewService.getCandidateList(candidate);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
		result.put("pageInfo", page);
		return result;
	}

	@RequestMapping("/interviewRecord")
	public String interviewRecord(HttpServletRequest request, HttpServletResponse response) {
		String candidateId = request.getParameter("candidateId");
		request.setAttribute("candidateId", candidateId);
		return "candidate/interviewRecord";
	}

	@RequestMapping("/getInterviewRecordByCandId")
	@ResponseBody
	public Object getInterviewRecordByCandId(String candidateId) {
		Map<String, List<CandidateInfo>> map = interviewService.getInterviewRecordByCandId(candidateId);
		return map;
	}

	@RequestMapping(value = "/lockCandidate")
	@ResponseBody
	public boolean lockCandidate(String candidateId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loginUser");
		String userId = user.getUserId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("candidateId", candidateId);

		return interviewService.lockCandidate(params);
	}

	@RequestMapping(value = "/getBillRate")
	@ResponseBody
	public String getBillRate(Employee employee, HttpServletRequest request) {
		return interviewService.getBillRate(employee);
	}

	@RequestMapping("/employeeDetailInfo")
	public String employeeDetailInfo(HttpServletRequest request, HttpServletResponse response) {
		String employeeId = request.getParameter("employeeId");
		request.setAttribute("employeeId", employeeId);
		return "employee/employeeDetailInfo";
	}

	@RequestMapping("/queryEmployeeById")
	@ResponseBody
	public Employee queryEmployeeDetailById(String employeeId,HttpServletRequest request) {
		 User user = (User) request.getSession().getAttribute("loginUser");
		 String userType = user.getUserType();

		Employee employee = interviewService.queryEmployeeById(employeeId);
		 if("5".equals(userType)||"6".equals(userType)){
			 employee.setBillRate("****");
		 }
		return employee;
	}
	
	
	@RequestMapping("/queryHSBCSubDeptById")
	@ResponseBody
	public HSBCDept queryHSBCSubDeptById(String hsbcSubDeptId) {

		HSBCDept hSBCDept = interviewService.queryHSBCSubDeptById(hsbcSubDeptId);
		return hSBCDept;
	}
}
