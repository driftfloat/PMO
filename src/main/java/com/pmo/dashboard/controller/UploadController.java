package com.pmo.dashboard.controller;

import java.io.File;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pmo.dashboard.entity.Resume;
import com.pmo.dashboard.util.Constants;

/**
 * 文件上传的controller
 * 
 */
@Controller
public class UploadController {
	
	/**
	 * 文件上传的方法
	 * @throws Exception 
	 */
	@RequestMapping(value="/uploadFile.html")
	public void uploadFile(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		//处理中文乱码
		request.setCharacterEncoding("UTF-8");
		//获取文件名称
		String originalFileName = file.getOriginalFilename();
		//System.out.println(originalFileName);
		//获取唯一文件名
		String fileName = UUID.randomUUID().toString().replaceAll("-", "")+".pdf";
		
		
		
		//相对路径
		String url = Constants.RESUME_PATH + fileName;
		
		//上传路径
		//String url = request.getSession().getServletContext().getRealPath("")+path;
		
		File subFile = new File(Constants.RESUME_PATH);
		request.getSession().setAttribute("url", url);
		 //判断文件路径是否存在
		if(!subFile.exists()){
			 //文件路径不存在时，创建保存文件所需要的路径
			subFile.mkdirs();
		}

		request.getSession().setAttribute("file", file);
		//file.transferTo(new File(url));

		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url",url );
		response.setContentType("text/html;charset = utf-8");
		response.getWriter().write(jsonObject.toString());
	}
}
