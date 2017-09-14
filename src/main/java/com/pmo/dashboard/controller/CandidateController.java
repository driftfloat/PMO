package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.CandidatePush;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CandidateService;

@Controller
@RequestMapping(value="/candidate")
public class CandidateController
{
    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CandidateController.class);
    
    @Resource
    private CandidateService candidateService;
    
    @RequestMapping("/getCandidate")
    public String getCandidate(final HttpServletRequest request,
            final HttpServletResponse response)
    {
    	return "candidate/candidateinfo";
    }
    
    @RequestMapping("/candidateFeedback")
    @ResponseBody
    public boolean candidateFeedback(String candidateId,String hrFeedBack, HttpServletRequest request){
		CandidateInfo candidateinfo=new CandidateInfo();
		candidateinfo.setFeedbackId(Utils.getUUID());
		candidateinfo.setCandidateId(candidateId);
		candidateinfo.setHrFeedback(hrFeedBack);
	   // String loginUser=request.getSession().getAttribute("loginUser").toString();
		candidateinfo.setUserId(request.getSession().getAttribute("loginUser").toString());
		boolean result=candidateService.updateCandidateInfo(candidateinfo);
		if(result){
			return true;
			
		}else{
			return false;
		}
		
    }
    @RequestMapping("/queryCandidateList")
    @ResponseBody
    public Object queryCandidateList(CandidateInfo candidate)
    {
    	 String pageState = candidate.getPageState();
    	 PageCondition page = new PageCondition();

     	String  expriencesWork = candidate.getExperienceYears();
    	 if("0".equals(expriencesWork)){
    		 candidate.setWorkYearsEnd("2");
    	 }else if("1".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("3");
    		 candidate.setWorkYearsEnd("5");
    	 }else if("2".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("6");
    		 candidate.setWorkYearsEnd("10");
    	 }else if("3".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("11");
    	 }
    	 if("-- ALL--".equalsIgnoreCase(candidate.getSkill()))
    	 {
    		 candidate.setSkill("");
    	 }
		 int dataCount = candidateService.queryCandidateCount(candidate);
	     page.setDataCount(dataCount+"");
		 page.setPageCount((dataCount-1)/10 + 1 +"");
		 page.setPageDataCount(Constants.PAGE_DATA_COUNT+"");
    	 if("".equals(pageState) || pageState == null ||"frist".equals(pageState)){
    		 page.setCurrentPage("1");
         }else if("next".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())+1+"");
         }else if("previous".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())-1+"");
         }else if("last".equals(pageState)){
        	 page.setCurrentPage(page.getPageCount());
         }
    	candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage())-1)*Constants.PAGE_DATA_COUNT+"");
    	candidate.setPageDataCount(Constants.PAGE_DATA_COUNT+"");
        List<CandidateInfo> list = candidateService.queryCandidateList(candidate);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", list);
        result.put("pageInfo", page);
        return result;
    }
    
    //下载
    @RequestMapping("/transformCandidateData")
    @ResponseBody
    public String transformCandidateData(CandidateInfo candidate,HttpServletRequest request)
    {
        String exportDataColumn = candidate.getExportDataColumn(); 
        if(exportDataColumn == null || "".equals(exportDataColumn))
        {
        	return "0";
        }
        if(exportDataColumn.lastIndexOf(",") == exportDataColumn.length()-1){
        	exportDataColumn = exportDataColumn.substring(0, exportDataColumn.length()-1);
        }
        candidate.setExportDataColumn(exportDataColumn);
        List<LinkedHashMap<String,String>> candidateDatalist = candidateService.queryExportData(candidate);
        request.getSession().setAttribute("candidate", candidate);
        request.getSession().setAttribute("candidateDatalist", candidateDatalist);
        return "1";
    }
    
    @RequestMapping("/exportExcel")
    @ResponseBody
    public HttpServletResponse exportExcel(HttpServletRequest request,HttpServletResponse response)
    {
    	CandidateInfo candidate = (CandidateInfo) request.getSession().getAttribute("candidate");
        if(candidate == null)
        {
        	return null;
        }
        @SuppressWarnings("unchecked")
        List<LinkedHashMap<String,String>> candidateDatalist = (List<LinkedHashMap<String,String>>) request.getSession().getAttribute("candidateDatalist");
        
        List<String> conditionList = Arrays.asList(candidate.getExportPageColumn().split(","));
        
        try {
        	  String tempfileName =  Constants.PATH+""+Utils.getUUID()+".xls";
              
              // 创建可写入的Excel工作簿           
              File file=new File(tempfileName);
              if (!file.exists()) {
                  file.createNewFile();
              }
              //写Excel
            candidateService.transferExportData(candidateDatalist,conditionList,file);
            //获取当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = df.format(new Date());
            String fileName = "PMO_Candidate_Info_"+date+".xls";

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(tempfileName));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
            //response.setContentType("application/octet-stream");
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            
           file.delete();         
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    @RequestMapping("/downLoadCandidateResume")
    @ResponseBody
    public String downLoadCandidateResume(HttpServletRequest request,HttpServletResponse response)
    {
    	String candidateId = request.getParameter("candidateId");
    	if(null == candidateId || "".equals(candidateId))
    	{
    		return "此候选人不存在，请刷新页面！";
    	}
    	CandidateInfo candidate = candidateService.queryCandidateForId(candidateId);
    	String resumePath = candidate.getResumePath();
        File file=new File(resumePath);
        if (!file.exists()) {
        	return "此候选人未上传简历！";
        }
        
        String suffix = resumePath.substring(resumePath.lastIndexOf("."));
        String fileName = candidate.getCandidateName()+"简历"+suffix;

        try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(resumePath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
			//response.setContentType("application/octet-stream");
//			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "2";
    }
    
    
    
    @RequestMapping("/getMyCandidate")
    public String getMyCandidate(final HttpServletRequest request,
            final HttpServletResponse response)
    {
    	return "candidate/myCandidateinfo";
    }
    
    @RequestMapping("/queryMyCandidateList")
    @ResponseBody
    public Object queryMyCandidateList(CandidateInfo candidate,HttpServletRequest request)
    {
    	User user =  (User)request.getSession().getAttribute("loginUser");
    	String lockPerson = user.getUserId();
    	if(null == lockPerson || "".equals(lockPerson))
    	{
    		return null;
    	}
    	candidate.setLockPerson(lockPerson);
    	
    	String pageState = candidate.getPageState();
    	PageCondition page = new PageCondition();

     	String  expriencesWork = candidate.getExperienceYears();
    	 if("0".equals(expriencesWork)){
    		 candidate.setWorkYearsEnd("2");
    	 }else if("1".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("3");
    		 candidate.setWorkYearsEnd("5");
    	 }else if("2".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("6");
    		 candidate.setWorkYearsEnd("10");
    	 }else if("3".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("11");
    	 }
    	
		 int dataCount = candidateService.queryMyCandidateCount(candidate);
	     page.setDataCount(dataCount+"");
		 page.setPageCount((dataCount-1)/10 + 1 +"");
		 page.setPageDataCount(Constants.PAGE_DATA_COUNT+"");
    	 if("".equals(pageState) || pageState == null ||"frist".equals(pageState)){
    		 page.setCurrentPage("1");
         }else if("next".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())+1+"");
         }else if("previous".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())-1+"");
         }else if("last".equals(pageState)){
        	 page.setCurrentPage(page.getPageCount());
         }
    	candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage())-1)*Constants.PAGE_DATA_COUNT+"");
    	candidate.setPageDataCount(Constants.PAGE_DATA_COUNT+"");
        List<CandidateInfo> list = candidateService.queryMyCandidateList(candidate);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", list);
        result.put("pageInfo", page);
        return result;
    }

    @RequestMapping("/loadCusDeptInfo")
    @ResponseBody
    public Object loadCusDeptInfo()
    {
    	return candidateService.queryCusDeptInfo();
    }
    
    @RequestMapping("/pushCandidateOk")
    @ResponseBody
    public String pushCandidateOk(CandidatePush candidatePush,HttpServletRequest request)
    {
    	return candidateService.pushCandidateOk(candidatePush,request);
    }
    
    @RequestMapping("/backCandidateToDept")
    @ResponseBody
    public String backCandidateToDept(HttpServletRequest request)
    {
    	return candidateService.backCandidateToDept(request);
    }
    
    @RequestMapping("/updateCandidateStatusOk")
    @ResponseBody
    public boolean updateCandidateStatusOk(CandidateInfo candidate,HttpServletRequest request)
    {    	
    	return candidateService.updateCandidateStatus(candidate);
    }
    
	@RequestMapping("/interviewFeedBackInfo")
	public String interviewFeedBackInfo(final HttpServletRequest request, final HttpServletResponse response) {
		return "candidate/interviewFeedBackInfo";
	}
    	
	@RequestMapping("/updateInterviewFeedBack")
	@ResponseBody
	public boolean updateInterviewFeedBack(String interviewId, String feedBackInfo, String interviewStatus) {
		CandidateInfo candidate = new CandidateInfo();
		candidate.setInterviewFeedBack(feedBackInfo);
		candidate.setInterviewId(interviewId);
		candidate.setInterviewStatus(interviewStatus);
		boolean flag = candidateService.updateInterviewFeedBack(candidate);
		return flag;
	}
    	
    @RequestMapping("/queryInterviewFeedBack")
	@ResponseBody
	public Object queryCandidateListOfInterview(CandidateInfo candidate, HttpServletRequest request) {
		String pageState = candidate.getPageState();
		PageCondition page = new PageCondition();

		 User user = (User) request.getSession().getAttribute("loginUser");
		 String userId = user.getUserId();
		 if (userId == null) {
		 return null;
		 }
		 candidate.setUserId(userId);
		
		int dataCount = candidateService.queryinterviewFeedBackCount(candidate);
		page.setDataCount(dataCount + "");
		page.setPageCount((dataCount - 1) / 10 + 1 + "");
		page.setPageDataCount(Constants.PAGE_DATA_COUNT + "");
		if ("".equals(pageState) || pageState == null || "frist".equals(pageState)) {
			page.setCurrentPage("1");
		} else if ("next".equals(pageState)) {
			page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage()) + 1 + "");
		} else if ("previous".equals(pageState)) {
			page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage()) - 1 + "");
		} else if ("last".equals(pageState)) {
			page.setCurrentPage(page.getPageCount());
		}

		candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage()) - 1) * Constants.PAGE_DATA_COUNT + "");
		candidate.setPageDataCount(Constants.PAGE_DATA_COUNT + "");
		List<CandidateInfo> list = candidateService.queryinterviewFeedBack(candidate);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
		result.put("pageInfo", page);
		return result;
	}
    
	@RequestMapping("/getMyWaitEntryCandidate")
	public String getMyWaitEntryCandidate() 
	{
		return "candidate/myWaitEntryCandidateinfo";
	}
	@RequestMapping("/queryMyWaitEntryCandidateList")
    @ResponseBody
    public Object queryMyWaitEntryCandidateList(CandidateInfo candidate,HttpServletRequest request)
    {
    	User user =  (User)request.getSession().getAttribute("loginUser");
    	String lockPerson = user.getUserId();
    	if(null == lockPerson || "".equals(lockPerson))
    	{
    		return null;
    	}
    	candidate.setLockPerson(lockPerson);
    	
    	String pageState = candidate.getPageState();
    	PageCondition page = new PageCondition();

     	String  expriencesWork = candidate.getExperienceYears();
    	 if("0".equals(expriencesWork)){
    		 candidate.setWorkYearsEnd("2");
    	 }else if("1".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("3");
    		 candidate.setWorkYearsEnd("5");
    	 }else if("2".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("6");
    		 candidate.setWorkYearsEnd("10");
    	 }else if("3".equals(expriencesWork)){
    		 candidate.setWorkYearsStart("11");
    	 }
    	
		 int dataCount = candidateService.queryMyWaitEntryCandidateCount(candidate);
	     page.setDataCount(dataCount+"");
		 page.setPageCount((dataCount-1)/10 + 1 +"");
		 page.setPageDataCount(Constants.PAGE_DATA_COUNT+"");
    	 if("".equals(pageState) || pageState == null ||"frist".equals(pageState)){
    		 page.setCurrentPage("1");
         }else if("next".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())+1+"");
         }else if("previous".equals(pageState)){
        	 page.setCurrentPage(Integer.valueOf(candidate.getCurrentPage())-1+"");
         }else if("last".equals(pageState)){
        	 page.setCurrentPage(page.getPageCount());
         }
    	candidate.setCurrentPage((Integer.valueOf(page.getCurrentPage())-1)*Constants.PAGE_DATA_COUNT+"");
    	candidate.setPageDataCount(Constants.PAGE_DATA_COUNT+"");
        List<CandidateInfo> list = candidateService.queryMyWaitEntryCandidateList(candidate);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", list);
        result.put("pageInfo", page);
        return result;
    }
	
	@RequestMapping("/entryMyWaitCandidateOk")
    @ResponseBody
    public boolean entryMyWaitCandidateOk(CandidateInfo candidate)
    {
		return candidateService.entryMyWaitCandidateOk(candidate);
    }
	
	@RequestMapping("/delayMyWaitCandidateOk")
    @ResponseBody
    public boolean delayMyWaitCandidateOk(CandidateInfo candidate)
    {
		return candidateService.delayMyWaitCandidateOk(candidate);
    }
	
	@RequestMapping("/abortMyWaitCandidateOk")
    @ResponseBody
    public boolean abortMyWaitCandidateOk(CandidateInfo candidate)
    {
		return candidateService.abortMyWaitCandidateOk(candidate);
    }
}
