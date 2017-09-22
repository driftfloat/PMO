package com.pmo.dashboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserAuthority;
import com.pom.dashboard.service.UserAuthorityService;

@Controller
@RequestMapping("/manage")
public class LoginController
{

    private static Logger logger = LoggerFactory
            .getLogger(LoginController.class);

    @Resource
	private UserAuthorityService userAuthorityService;
    
    @RequestMapping("/loginPage")
    public String loginPage(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "login";
    }

    @RequestMapping("/top")
    public String top(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "top";
    }

    @RequestMapping("/left")
    public ModelAndView left(final HttpServletRequest request,HttpSession session,
            final HttpServletResponse response)
    {
    	User u=(User)session.getAttribute("loginUser");
    	List<UserAuthority> list= userAuthorityService.queryUserAuthority(u.getUser_type());    	
    	ModelAndView v =new ModelAndView();    	
    	v.setViewName("left");
    	v.addObject("list", list);
        return v;
    }

    @RequestMapping("/footer")
    public String footer(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "footer";
    }

}
