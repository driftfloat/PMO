package com.pmo.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 运营
 * @author gaonana
 *
 */
@Controller
@RequestMapping(value="/offline")
public class OfflineController {
	
	@RequestMapping("/listPage")
	public String listPage(){
		return "offline/offline";
	}

}
