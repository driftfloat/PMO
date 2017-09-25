package com.pmo.dashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/logOut")
public class LoginOutController {

	/**
	 * 注销登陆方法
	 * @throws IOException 
	 */
	
	@RequestMapping("/logOut")
	public String LoginOut(HttpServletResponse response,HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession(false);
		
		if(session == null){
			response.sendRedirect("welcome");
			System.out.println("!!");
			
		}
		request.getSession().removeAttribute("loginUser");
		request.getSession().invalidate();
//		response.sendRedirect(request.getContextPath()+"/page/welcome.jsp");
		//response.sendRedirect("login");
		return "login";
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
       LoginOut(response, request);
       
    
	}
}
