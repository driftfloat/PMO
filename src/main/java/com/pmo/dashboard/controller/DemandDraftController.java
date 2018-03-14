package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.DemandDraft;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.DemandDraftService;

/**
 * 需求草稿
 * @author Devin
 * @since 2018-3-13
 *
 */
@Controller
@RequestMapping(value="/demandDraft")
public class DemandDraftController {
	
	
	@Resource
	DemandDraftService demandDraftService;
	
	
	@RequestMapping("/add")
	@ResponseBody
	public boolean add(DemandDraft record,HttpServletRequest request,HttpServletResponse response){
		boolean flag0 = false;
		if(record.getType().equals("1")){
			flag0 = checkField1(record);
		}
        if(record.getType().equals("2")){
        	flag0 = checkField2(record);
		}
        if(record.getType().equals("3")){
        	flag0 = checkField3(record);
		}
		if(flag0==false){
			return false;
		}
		
		User u=(User)request.getSession().getAttribute("loginUser");
		boolean flag = false;
		Map<String,String> param = new HashMap<String,String>();
		param.put("operateId", u.getUserId());
		param.put("type", record.getType());
		DemandDraft demandDraft = demandDraftService.getByID(param);
		if(demandDraft!=null){
			record.setDemandId(demandDraft.getDemandId());
			flag = demandDraftService.update(record);
		}else{
			String demandDraftID = Utils.getUUID(); 
			record.setDemandId(demandDraftID);
			record.setOperateId(u.getUserId());
			flag = demandDraftService.add(record);
		}
		return flag;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(DemandDraft record,HttpServletRequest request,HttpServletResponse response){
		User u=(User)request.getSession().getAttribute("loginUser");
		Map<String,String> param = new HashMap<String,String>();
		param.put("operateId", u.getUserId());
		param.put("type", record.getType());
		return demandDraftService.delete(param);
	}
	
	@RequestMapping("/getByID")
	@ResponseBody
	public DemandDraft getByID(DemandDraft record,HttpServletRequest request,HttpServletResponse response){
		User u=(User)request.getSession().getAttribute("loginUser");
		Map<String,String> param = new HashMap<String,String>();
		param.put("operateId", u.getUserId());
		param.put("type", record.getType());
		DemandDraft demandDraft = demandDraftService.getByID(param);
		return demandDraft;
	}
	
	private boolean checkField1(DemandDraft record){
		boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false,flag5 = false,flag6 = false,flag7 = false,flag8 = false,flag9 = false,flag10 = false,flag11 = false,flag12 = false;
		if(record.getEngagementType()==null || "".equals(record.getEngagementType())){
			flag1 = false;
		}else{
			flag1 = true;
		}
		
        if(record.getRr()==null || "".equals(record.getRr())){
			flag2 = false;
		}else{
			flag2 = true;
		}
        
        if(record.getSkill()==null || "".equals(record.getSkill())){
	        flag3 = false;
        }else{
        	flag3 = true;
        }
        
        if(record.getLocation()==null || "".equals(record.getLocation())){
	        flag4 = false;
        }else{
        	flag4 = true;
        }
        
        if(record.getHsbcSubdeptId()==null || "".equals(record.getHsbcSubdeptId())){
	        flag5 = false;
        }else{
        	flag5 = true;
        }
        
        if(record.getCsSubdept()==null || "".equals(record.getCsSubdept())){
	        flag6 = false;
        }else{
        	flag6 = true;
        }
        
        if(record.getJobCode()==null || "".equals(record.getJobCode())){
	        flag7 = false;
        }else{
        	flag7 = true;
        }
        
        if(record.getPosition()==null || "".equals(record.getPosition())){
	        flag8 = false;
        }else{
        	flag8 = true;
        }
        
        if(record.getPlannedOnboardDate()==null || "".equals(record.getPlannedOnboardDate())){
	        flag9 = false;
        }else{
        	flag9 = true;
        }
        
        if(record.getRequestor()==null || "".equals(record.getRequestor())){
	        flag10 = false;
        }else{
        	flag10 = true;
        }
        
        if(record.getReqPublishedDate()==null || "".equals(record.getReqPublishedDate())){
	        flag11 = false;
        }else{
        	flag11 = true;
        }
        
        if(record.getRemark()==null || "".equals(record.getRemark())){
	        flag12 = false;
        }else{
        	flag12 = true;
        }
        
        if(flag1 || flag2 || flag3 || flag4 || flag5 || flag6 || flag7 || flag8 || flag9 || flag10 || flag11 || flag12){
        	return true;
        }else{
        	return false;
        }
	}
	
	
	private boolean checkField2(DemandDraft record){
		boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false,flag5 = false,flag6 = false,flag7 = false,flag8 = false;
        
        if(record.getSkill()==null || "".equals(record.getSkill())){
	        flag1 = false;
        }else{
        	flag1 = true;
        }
        
        if(record.getLocation()==null || "".equals(record.getLocation())){
	        flag2 = false;
        }else{
        	flag2 = true;
        }
        
        if(record.getCsSubdept()==null || "".equals(record.getCsSubdept())){
	        flag3 = false;
        }else{
        	flag3 = true;
        }
        
        if(record.getPosition()==null || "".equals(record.getPosition())){
	        flag4 = false;
        }else{
        	flag4 = true;
        }
        
        if(record.getPlannedOnboardDate()==null || "".equals(record.getPlannedOnboardDate())){
	        flag5 = false;
        }else{
        	flag5 = true;
        }
        
        if(record.getRequestor()==null || "".equals(record.getRequestor())){
	        flag6 = false;
        }else{
        	flag6 = true;
        }
        
        if(record.getReqPublishedDate()==null || "".equals(record.getReqPublishedDate())){
	        flag7 = false;
        }else{
        	flag7 = true;
        }
        
        if(record.getRemark()==null || "".equals(record.getRemark())){
	        flag8 = false;
        }else{
        	flag8 = true;
        }
        
        if(flag1 || flag2 || flag3 || flag4 || flag5 || flag6 || flag7 || flag8){
        	return true;
        }else{
        	return false;
        }
	}
	
	private boolean checkField3(DemandDraft record){
		boolean flag1 = false,flag2 = false,flag3 = false,flag4 = false,flag5 = false,flag6 = false;
        
        if(record.getSkill()==null || "".equals(record.getSkill())){
	        flag1 = false;
        }else{
        	flag1 = true;
        }
        
        if(record.getLocation()==null || "".equals(record.getLocation())){
	        flag2 = false;
        }else{
        	flag2 = true;
        }
        
        if(record.getCsSubdept()==null || "".equals(record.getCsSubdept())){
	        flag3 = false;
        }else{
        	flag3 = true;
        }
        
        if(record.getPosition()==null || "".equals(record.getPosition())){
	        flag4 = false;
        }else{
        	flag4 = true;
        }
        
        if(record.getPlannedOnboardDate()==null || "".equals(record.getPlannedOnboardDate())){
	        flag5 = false;
        }else{
        	flag5 = true;
        }
        
        if(record.getRemark()==null || "".equals(record.getRemark())){
	        flag6 = false;
        }else{
        	flag6 = true;
        }
        
        if(flag1 || flag2 || flag3 || flag4 || flag5 || flag6){
        	return true;
        }else{
        	return false;
        }
	}

}
