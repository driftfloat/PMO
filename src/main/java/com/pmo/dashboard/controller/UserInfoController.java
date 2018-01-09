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

import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserPageCondition;
import com.pmo.dashboard.util.Constants;
import com.pom.dashboard.service.UserInfoService;

@Controller
@RequestMapping(value="/userInfo")
public class UserInfoController {
	
	@Resource
	private UserInfoService userInfoService;

	@RequestMapping("/queryUserList")
    @ResponseBody
	public Object queryUserList(final HttpServletRequest request,
            final HttpServletResponse response){
		String pageState = request.getParameter("pageState");
		
        String eHr = request.getParameter("eHr");
        
        String userName = request.getParameter("userName");
        
        String bu = request.getParameter("csBuName");
        
        String du = request.getParameter("csSubDeptName");
        
        String userType = request.getParameter("userType");
        
        UserPageCondition userPageCondition = new UserPageCondition();
        String currentPage = null;
        int countPage = 0;
        
		if("".equals(pageState) || pageState == null){
            currentPage = "0";
            userPageCondition.seteHr(eHr);
            userPageCondition.setUserName(userName);
            userPageCondition.setBu(bu);
            userPageCondition.setDu(du);
            userPageCondition.setUserType(userType);
            userPageCondition.setCurrentPage(currentPage);
            countPage = userInfoService.countUserList(userPageCondition);
            userPageCondition.setPageCount(countPage + "");
            request.getSession().setAttribute("userPageCondition", userPageCondition);
        }else if("frist".equals(pageState)){
            currentPage = "0";
            userPageCondition = (UserPageCondition) request.getSession().getAttribute("userPageCondition");
            userPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("userPageCondition", userPageCondition);
        }else if("next".equals(pageState)){
            userPageCondition = (UserPageCondition) request.getSession().getAttribute("userPageCondition");
            currentPage = Integer.parseInt(userPageCondition.getCurrentPage()) + Constants.TEN +"";
            userPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("userPageCondition", userPageCondition);
        }else if("previous".equals(pageState)){
        	userPageCondition = (UserPageCondition) request.getSession().getAttribute("userPageCondition");
            currentPage = Integer.parseInt(userPageCondition.getCurrentPage()) - Constants.TEN +"";
            userPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("userPageCondition", userPageCondition);
        }else if("last".equals(pageState)){
        	userPageCondition = (UserPageCondition) request.getSession().getAttribute("userPageCondition");
            currentPage = (Integer.parseInt(userPageCondition.getPageCount()) - Constants.ONE) * Constants.TEN +"";
            userPageCondition.setCurrentPage(currentPage);
            request.getSession().setAttribute("userPageCondition", userPageCondition);
        }
		Map<String,Object> result = new HashMap<String,Object>();
		List<User> list = userInfoService.queryUserList(userPageCondition);
		result.put("userType", userType);
		result.put("data", list);
		result.put("pageInfo", request.getSession().getAttribute("userPageCondition"));
		return result;
	}
}
