package com.pmo.dashboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.User;

@Controller
@RequestMapping(value="/offline/report")
public class TestController {
	
	@RequestMapping("/listPage")
	public String listPage(HttpServletRequest request,Model model){
		
		return "offline/report";
	}

}
