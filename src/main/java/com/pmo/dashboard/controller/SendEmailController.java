package com.pmo.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.SendUtil;
import com.pom.dashboard.service.UserService;


/**
 * 邮件发送
 * @author Devin
 * @since 2018-3-27
 *
 */
@Controller
@RequestMapping(value="/sendemail")
public class SendEmailController {
	
	
	@Resource
	UserService userService;
	
	/**
	 * 发送邮件--添加需求时
	 * @param sei
	 * @return
	 */
	@RequestMapping("/send1")
	@ResponseBody
	public boolean send1(final HttpServletRequest request,
            final HttpServletResponse response){
		try{
			String eHr = request.getParameter("ehr");
			Map<String,Object> map = new HashMap<String,Object>();
			JSONArray array= new JSONArray(eHr);
			for(int i=0;i<array.length();i++){
				if(array.get(i)!=null && !"".equals(array.get(i))){
					map.put("ehr", array.get(i));
					List<User> list = userService.getUser(map);
					if(list.get(0).getEmail()!=null && !"".equals(list.get(0).getEmail())){
						SendUtil.send(list.get(0).getEmail(), "Pmo系统有新的需求被添加", "你好：Pmo系统有新的需求被添加，请及时查看,谢谢!");
					}
				}
			}
			return true;
		}catch(Exception e){
			
		}
		return false;
	}

	
	/**
	 * 发送邮件--修改需求时
	 * @param sei
	 * @return
	 */
	@RequestMapping("/send2")
	@ResponseBody
	public boolean send2(final HttpServletRequest request,
            final HttpServletResponse response){
		try{
			String eHr = request.getParameter("ehr");
			//获取需求编号
			String demandid = request.getParameter("demandid");
			Map<String,Object> map = new HashMap<String,Object>();
			JSONArray array= new JSONArray(eHr);
			for(int i=0;i<array.length();i++){
				if(array.get(i)!=null && !"".equals(array.get(i))){
					map.put("ehr", array.get(i));
					List<User> list = userService.getUser(map);
					if(list.get(0).getEmail()!=null && !"".equals(list.get(0).getEmail())){
						SendUtil.send(list.get(0).getEmail(), "Pmo系统编号为["+demandid+"]的需求被修改", "你好：Pmo系统编号为["+demandid+"]的需求被修改，请及时查看,谢谢!");
					}
				}
			}
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
}
