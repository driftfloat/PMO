package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.CandidateInterview;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.SendUtil;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.InterviewService;
import com.pom.dashboard.service.UserService;


/**
 * 邮件发送
 * @author Devin
 * @since 2018-3-27
 *
 */
@Controller
@RequestMapping(value="/sendemail")
public class SendEmailController {
	
	
	@Resource
	UserService userService;
	
	@Resource
	InterviewService interviewService;
	
	@Resource
	EmployeeService employeeService;
	
	/**
	 * 发送邮件--添加需求时
	 * @param sei
	 * @return
	 */
	@RequestMapping("/send1")
	@ResponseBody
	public boolean send1(final HttpServletRequest request,
            final HttpServletResponse response){
		try{
			String eHr = request.getParameter("ehr");
			Map<String,Object> map = new HashMap<String,Object>();
			JSONArray array= new JSONArray(eHr);
			for(int i=0;i<array.length();i++){
				if(array.get(i)!=null && !"".equals(array.get(i))){
					map.put("ehr", array.get(i));
					List<User> list = userService.getUser(map);
					if(list.get(0).getEmail()!=null && !"".equals(list.get(0).getEmail())){
						SendUtil.send(list.get(0).getEmail(), "Pmo系统有新的需求被添加", "你好：Pmo系统有新的需求被添加，请及时查看,谢谢!");
					}
				}
			}
			return true;
		}catch(Exception e){
			
		}
		return false;
	}

	
	/**
	 * 发送邮件--修改需求时
	 * @param sei
	 * @return
	 */
	@RequestMapping("/send2")
	@ResponseBody
	public boolean send2(final HttpServletRequest request,
            final HttpServletResponse response){
		try{
			String eHr = request.getParameter("ehr");
			//获取需求编号
			String demandid = request.getParameter("demandid");
			Map<String,Object> map = new HashMap<String,Object>();
			JSONArray array= new JSONArray(eHr);
			for(int i=0;i<array.length();i++){
				if(array.get(i)!=null && !"".equals(array.get(i))){
					map.put("ehr", array.get(i));
					List<User> list = userService.getUser(map);
					if(list.get(0).getEmail()!=null && !"".equals(list.get(0).getEmail())){
						SendUtil.send(list.get(0).getEmail(), "Pmo系统编号为["+demandid+"]的需求被修改", "你好：Pmo系统编号为["+demandid+"]的需求被修改，请及时查看,谢谢!");
					}
				}
			}
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
	
	/**
	 * 发送邮件--招聘管理-HR确认
	 * @param sei
	 * @return
	 */
	@RequestMapping("/send3")
	@ResponseBody
	public boolean send3(final HttpServletRequest request,
            final HttpServletResponse response){
		try{
			String eHr = request.getParameter("ehr");
			//获取候选人ID
			String canid = request.getParameter("candidateId");
			//获取是否发送到面试官标志
			String issendtointerview = request.getParameter("issendtointerview");
			
			Map<String,Object> map = new HashMap<String,Object>();
			JSONArray array= new JSONArray(eHr);
			//发送到RM
			for(int i=0;i<array.length();i++){
				if(array.get(i)!=null && !"".equals(array.get(i))){
					map.put("ehr", array.get(i));
					List<User> list = userService.getUser(map);
					if(list.get(0).getEmail()!=null && !"".equals(list.get(0).getEmail())){
						SendUtil.send(list.get(0).getEmail(), "Pmo系统,HR已确认面试安排", "你好：HR已确认面试安排，请及时查看,谢谢!");
					}
				}
			}
			//发送到面试官
			if(issendtointerview.equals("1")){
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("candidid", canid);
				CandidateInterview ci = interviewService.getInteviewer(param);
				if(ci.getInterviewerId()!=null && !"".equals(ci.getInterviewerId())){
					Employee ep = employeeService.queryEmployeeById(ci.getInterviewerId());
					if(ep.getEmail()!=null && !"".equals(ep.getEmail())){
						SendUtil.send(ep.getEmail(), "Pmo系统,HR已确认面试安排", "你好：HR已确认面试安排，请及时面试,谢谢!");
					}
				}
			}
			
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
	
	/**
	 * 发送邮件--面试管理-面试安排
	 * @param sei
	 * @return
	 */
	@RequestMapping("/send4")
	@ResponseBody
	public boolean send4(final HttpServletRequest request,
            final HttpServletResponse response){
		try{
			String eHr = request.getParameter("ehr");
			//获取需求编号
			//String demandid = request.getParameter("demandid");
			Map<String,Object> map = new HashMap<String,Object>();
			JSONArray array= new JSONArray(eHr);
			for(int i=0;i<array.length();i++){
				if(array.get(i)!=null && !"".equals(array.get(i))){
					map.put("ehr", array.get(i));
					List<User> list = userService.getUser(map);
					if(list.get(0).getEmail()!=null && !"".equals(list.get(0).getEmail())){
						SendUtil.send(list.get(0).getEmail(), "Pmo系统,RM已成功安排面试", "你好：RM已成功安排面试，请及时跟候选人确认,谢谢!");
					}
				}
			}
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
	
	/**
	 * 发送邮件--招聘管理-推送候选人
	 * @param sei
	 * @return
	 */
	@RequestMapping("/send5")
	@ResponseBody
	public boolean send5(final HttpServletRequest request,
            final HttpServletResponse response){
		try{
			String eHr = request.getParameter("ehr");
			//获取需求编号
			//String demandid = request.getParameter("demandid");
			Map<String,Object> map = new HashMap<String,Object>();
			JSONArray array= new JSONArray(eHr);
			for(int i=0;i<array.length();i++){
				if(array.get(i)!=null && !"".equals(array.get(i))){
					map.put("ehr", array.get(i));
					List<User> list = userService.getUser(map);
					if(list.get(0).getEmail()!=null && !"".equals(list.get(0).getEmail())){
						SendUtil.send(list.get(0).getEmail(), "Pmo系统,HR已成功推送候选人", "你好：HR已成功推送候选人，请及时安排面试,谢谢!");
					}
				}
			}
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
	
}
