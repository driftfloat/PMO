package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pom.dashboard.service.CandidateService;

/**
 * 在线预览pdf
 * 
 * @Auothor gkf
 * @Date 2017/11/20
 */
@Controller
@RequestMapping(value = "/display")
public class DisplayPDFController {

	@Resource
	private CandidateService candidateService;

	@RequestMapping("/getPdf")
	public String getPdf(final HttpServletRequest request, final HttpServletResponse response) {
		return "playResume";
	}

	@RequestMapping("/displayPDF")
	public void displayPDF(HttpServletRequest request, HttpServletResponse response) {
		String candidateId = (String) request.getSession().getAttribute("candidateId");

		CandidateInfo candidate = candidateService.queryCandidateForId(candidateId);
		String resumePath = candidate.getResumePath();
		try {
			// File file1 = new File(resumePath);
			InputStream fis = new BufferedInputStream(new FileInputStream(resumePath));
			// FileInputStream fileInputStream = new FileInputStream(file1);
			response.setHeader("Content-Disposition", "attachment;fileName=简历.pdf");
			response.setContentType("multipart/form-data");
			OutputStream outputStream = response.getOutputStream();
			IOUtils.write(IOUtils.toByteArray(fis), outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}