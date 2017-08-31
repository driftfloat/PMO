package com.pmo.dashboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.Resume;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.ResumeService;

/**
 * 候选人的信息的Controller
 * 
 * @author dilu
 * @version 1.0 2017-8-25 15:09:30
 */
@Controller
@RequestMapping(value="/resume")
public class ResumeController {
	
	@Resource
	private ResumeService resumeService;
	/**
	 * 跳转到录入信息的页面
	 * 
	 * @param request
	 * @param response
	 * @return 信息录入页面
	 */
	@RequestMapping("/input")
	public String input(){
		
		return "resume/add";
	}
	
	@RequestMapping("/checkTel")
	@ResponseBody
	public String checkTel(String tel){
		boolean result = true;
		Resume c=resumeService.searchTel(tel);
		if(c!=null){
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
	@RequestMapping("/add")
	public String add(Resume resume,Model model ){
		String uuid = Utils.getUUID();
		resume.setId(uuid);
		resume.setHr("小翠");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = df.format(new Date());
        resume.setCreate_date(todayDate);
		//调用service
        resumeService.add(resume);
		return null;
		
	}
}
