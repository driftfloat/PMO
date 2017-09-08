package com.pmo.dashboard.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.HrFeedback;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.HrFeedbackService;

@Controller
@RequestMapping(value="/hrfeedback")
public class HrFeedbackController
{
    @Resource
    private HrFeedbackService hrFeedbackservice;
    
    
    @RequestMapping("/hrFinalFeedBack")
    @ResponseBody
    public boolean candidateFeedback(String candidateId,String hrFeedBack, HttpServletRequest request){
    	HrFeedback hrFeedback =new HrFeedback();
    	hrFeedback.setFeedbackId(Utils.getUUID());
    	hrFeedback.setCandidateId(candidateId);
    	hrFeedback.setHrFeedback(hrFeedBack);
		
		boolean result=hrFeedbackservice.updateCandidateInfo(hrFeedback);
		if(result){
			return true;
			
		}else{
			return false;
		}
		
    }
   
}
