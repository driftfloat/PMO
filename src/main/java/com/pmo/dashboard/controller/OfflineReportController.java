package com.pmo.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/offline/report")
public class OfflineReportController {
	
	@RequestMapping("/listPage")
	public String listPage(HttpServletRequest request,Model model){
		
		return "offline/report";
	}

}
