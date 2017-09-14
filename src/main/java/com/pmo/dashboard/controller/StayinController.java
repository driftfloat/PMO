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

import com.pmo.dashboard.entity.AddDemand;
import com.pmo.dashboard.entity.CandidateInfo;
import com.pmo.dashboard.entity.Interviewer;
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.entity.StayinCandidate;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CandidateService;
import com.pom.dashboard.service.StayinService;

@Controller
@RequestMapping(value="/stayin")
public class StayinController
{
    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(StayinController.class);
    
    @Resource
    private StayinService StayinService;
    
   
    @RequestMapping("/stayin")
    public String stayin(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "/rm/stayin";
    }

    //查询列表
    @RequestMapping("/queryStayinList")
    @ResponseBody
    public Object queryStayinList(StayinCandidate candidate,HttpServletRequest request)
    {
    	 String pageState = candidate.getPageState();
    	 PageCondition page = new PageCondition();

     	
		 int dataCount = StayinService.queryCandidateCount(candidate);
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
        List<StayinCandidate> list = StayinService.queryStayinList(candidate);
        
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data", list);
        result.put("pageInfo", page);
        return result;
    }
    
    
    @RequestMapping("/transformCandidateData")
    @ResponseBody
    public String transformCandidateData(StayinCandidate candidate,HttpServletRequest request)
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
        List<LinkedHashMap<String,String>> candidateDatalist = StayinService.queryExportData(candidate);
        request.getSession().setAttribute("candidate", candidate);
        request.getSession().setAttribute("candidateDatalist", candidateDatalist);
        return "1";
    }
    @RequestMapping("/queryDemandList")
    @ResponseBody
    public Object demandQuery(String candidateId,HttpServletRequest request){
    	
    	AddDemand demand = new  AddDemand();
    
    	
    	return null;
    }
    	
    	
    
    //导出表格
    @RequestMapping("/exportExcel")
    @ResponseBody
    public HttpServletResponse exportExcel(HttpServletRequest request,HttpServletResponse response)
    {
    	StayinCandidate candidate = (StayinCandidate) request.getSession().getAttribute("candidate");
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
              StayinService.transferExportData(candidateDatalist,conditionList,file);
            //获取当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = df.format(new Date());
            String fileName = "PMO_Stayin_Info_"+date+".xls";

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
    
    
    
}
