package com.pmo.dashboard.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传的controller
 * 
 * @author dilu
 * @version 1.0，2017-8-29 23:16:01
 */
@Controller
public class UploadController {

	/**
	 * 文件上传的方法
	 * @throws Exception 
	 */
	@RequestMapping(value="/uploadFile.html")
	public void uploadFile(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//处理中文乱码
		request.setCharacterEncoding("UTF-8");
		//获取文件名称
		String originalFileName = file.getOriginalFilename();
		//System.out.println(originalFileName);
		//获取唯一文件名
		String fileName = UUID.randomUUID().toString().replaceAll("-", "")+"_"+originalFileName;
		
		//相对路径
		String url = "c:\\resume\\" + fileName;
		
		//上传路径
		//String url = request.getSession().getServletContext().getRealPath("")+path;
		
		file.transferTo(new File(url));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url",url );
		response.setContentType("text/html;charset = utf-8");
		response.getWriter().write(jsonObject.toString());
	}
}
