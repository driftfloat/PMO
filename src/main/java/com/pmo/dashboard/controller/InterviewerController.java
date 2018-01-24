package com.pmo.dashboard.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.InterviewerService;
import com.pom.dashboard.service.UserService;

@Controller
@RequestMapping(value="/interviewer")
public class InterviewerController
{
   
	private static Logger logger = LoggerFactory.getLogger(InterviewerController.class);
    
    @Resource
    private InterviewerService interviewerService;
    
    @Resource
    private UserService userService;
   
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
    	if("2年以下".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceEnd("2");
    	}else if("3-5年".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceStart("3");
    		interviewer.setExperienceEnd("5");
    	}else if("6-10年".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceStart("6");
    		interviewer.setExperienceEnd("10");
    	}else if("11年以上".equals(interviewer.getExperienceYearas())){
    		interviewer.setExperienceStart("11");
    	}
    	if(interviewer.getCurrPage() == null || "".equals(interviewer.getCurrPage()))
    	{
    		interviewer.setCurrPage(1);
		}
    	User user = (User)request.getSession().getAttribute("loginUser");
    	interviewer.setEmployeeId(user.getUserId());
    	List<Interviewer> list = interviewerService.queryInterviewerList(interviewer,user);
    	if(null == list){
    		list = new ArrayList<Interviewer>();
    	}
    	for(int i=0;i<list.size();i++){
    	    
    	    //处理null ""
    	    if(list.get(i).getExperienceYearas()==null){
    	        list.get(i).setExperienceYearas("");
    	        continue;
    	    }
    	    
    		//获取毕业时间
    		String graduate_date=list.get(i).getExperienceYearas();
    		//将毕业时间转换成毫秒
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		String todayDate = df.format(new Date());
    		//将当前时间转换成毫秒
    		long nt;
    		long gt;
    		int year;
			try {
				gt = df.parse(graduate_date).getTime();
				nt = df.parse(todayDate).getTime();
				long cha = nt-gt;
			    year = (int) Math.floor(cha/ 1000 / 60 / 60 / 24/ 30 /12);
			 // 设置工作年限
	    		list.get(i).setExperienceYearas(String.valueOf(year));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		
    	}
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
    		User u = new User();
    		u.setUserId(employeeId);
    		u.setLoginStatus("1");//不可登录
    		userService.update(u);
    		return resultFlag;
    	}else{
    		interviewer.setStatus("1");
    		boolean flag1 =  interviewerService.update(interviewer);
    		Interviewer interviewers = interviewerService.selectInterviewer(employeeId);
    		boolean flag2=true;
    		User user = interviewerService.selectUser(employeeId);
    		User u  = null;
    		if("".equals(user)||user==null){
    		 u = new User();
    		 u.setUserId(employeeId);
    		 u.setUserName(interviewers.getEhr());
    		 u.setNickname(interviewers.getStaffName());
    		 u.setPassword("123");
    		 u.setUserType("10");
    		 u.setCsdeptId(interviewers.getCsSubDeptId());
    		 u.setLoginStatus("0");//可登录
    		 flag2 = userService.addUser(u);
    		}else{
    		 u = new User();
    		 u.setLoginStatus("0");//可登录
    		 u.setUserId(employeeId);
    		 userService.update(u);
    		}
    		boolean flag = flag1 & flag2;
    		return flag;
    	}

    }
  
}
