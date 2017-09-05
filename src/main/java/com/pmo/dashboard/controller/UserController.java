package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.UserService;

/**
 * 用户的Controller
 * 
 * @author dilu
 * @version 1.0，2017-9-4 21:50:23
 */
@Controller
@RequestMapping(value="user")
public class UserController {

	@Resource
	private UserService userService;
	
	/**
	 * 验证用户名存在的方法法
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public String checkUser(String userName){
		boolean result = true;
		User user = userService.checkUser(userName);
		if(user==null){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
	}
	
	/**
	 * 验证用户登录的方法
	 * 
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(String userName,String password,HttpSession session,Model model){
		User user = userService.login(userName,password);
		
		if(user!=null){
			//登录成功 ，将user放入session，跳转到首页
			session.setAttribute("loginUser", user);
			return "0";
		}else{
			//登录失败，返回登录页面，设置错误信息
			//model.addAttribute("error", "用户名或密码有误  请重新输入");
			return "1";
		}
		
	}
	
	@RequestMapping("/welcome")
	public String welcome(){
		
		return "welcome";
	}
}

