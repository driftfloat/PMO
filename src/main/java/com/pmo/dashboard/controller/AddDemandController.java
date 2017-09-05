package com.pmo.dashboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.AddDemand;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.AddDemandService;

@Controller
@RequestMapping(value="/demand")
public class AddDemandController {
	
	@Resource
	private AddDemandService addDemandService;
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
            .getLogger(AddDemandController.class);
	

    @RequestMapping("/recruitdemand")
    public String recruitdemand(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "/demand/recruitdemand";
    }
 

    @RequestMapping("/addDemand")
    @ResponseBody
    public boolean addDemand(AddDemand demand)
    {      
    	String demandId = Utils.getUUID();
    	demand.setDemandId(demandId);
    	boolean resultFlag = addDemandService.addDemand(demand);
        return resultFlag;
    }

    
}
