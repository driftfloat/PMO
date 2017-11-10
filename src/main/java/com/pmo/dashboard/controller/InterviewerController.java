package com.pmo.dashboard.controller;

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

import com.pmo.dashboard.entity.Interviewer;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.InterviewerService;

@Controller
@RequestMapping(value="/interviewer")
public class InterviewerController
{
   
	private static Logger logger = LoggerFactory.getLogger(InterviewerController.class);
    
    @Resource
    private InterviewerService interviewerService;
   
    @RequestMapping("/rm")
    public String getCandidate(final HttpServletRequest request,
            final HttpServletResponse response)
    {
    	return "rm/rm";
    }
    /*
     * 按照条件查询面试官和分页功能
     * */
 
    @RequestMapping("/queryInterviewerList")
    @ResponseBody
    public Object interviewerQuery(Interviewer interviewer, HttpServletRequest request)
    {
    	
    	if(interviewer.getCurrPage() == null || "".equals(interviewer.getCurrPage()))
    	{
    		interviewer.setCurrPage(1);
		}
    	User user = (User)request.getSession().getAttribute("loginUser");
    	interviewer.setEmployeeId(user.getUserId());
    	List<Interviewer> list = interviewerService.queryInterviewerList(interviewer);
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("list", list);
    	result.put("pageCondition", interviewer);
    	return result;
    }
    
    //更改面试官状态指定是否为面试官
    @RequestMapping("/interviewerRenDing")
    @ResponseBody
    public boolean updateInterviewer(final HttpServletRequest request,
            final HttpServletResponse response){
    	String employeeId = request.getParameter("employeeId");
    	String status = request.getParameter("status");
    	
    	Interviewer interviewer = new Interviewer();
    	
    	
    	interviewer.setEmployeeId(employeeId);
    	
    	boolean resultFlag = false;
    	
    	if("1".equals(status)){
    		interviewer.setStatus("0");
    		resultFlag = interviewerService.update(interviewer);
    		return resultFlag;
    	}else{
    		interviewer.setStatus("1");
    		boolean flag1 =  interviewerService.update(interviewer);
    		
    		Interviewer interviewers = interviewerService.selectInterviewer(employeeId);
    		interviewers.setEmployeeId(employeeId);
    		boolean flag2=true;
    		User user = interviewerService.selectUser(employeeId);
    		if("".equals(user)||user==null){
    		 flag2 = interviewerService.insertInterviewerToUser(interviewers);
    		}
    		
    		
    		
    		
    		boolean flag = flag1 & flag2;
    		return flag;
    	}

    }
  
}
