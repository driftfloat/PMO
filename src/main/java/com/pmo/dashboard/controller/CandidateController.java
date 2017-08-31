package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.pmo.dashboard.entity.PageCondition;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CandidateService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
         
            String fileName =  Constants.PATH+""+Utils.getUUID()+".xls";
            
            WritableWorkbook wwb = null;
         
            // 创建可写入的Excel工作簿           
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);

            // 创建工作表
            WritableSheet ws = wwb.createSheet("Candidate List", 0);
            
            //要插入到的Excel表格的行号，默认从0开始           
            Label labelSL= new Label(0, 0, "SL#");
            ws.addCell(labelSL);
            
            for(int k=0;k<conditionList.size();k++)
            {
                Label label = new Label(k+1, 0, conditionList.get(k));
                ws.addCell(label);
            }
            
            for (int i = 1; i-1 < candidateDatalist.size(); i++) {
            	LinkedHashMap<String,String> map = candidateDatalist.get(i-1);
            	int j = 0;
            	Label labelSL_i= new Label(j, i, i+"");
                ws.addCell(labelSL_i);
            	for (Map.Entry<String, String> entry : map.entrySet()) 
            	{
            		 Label label= new Label(++j, i, entry.getValue());
            		 ws.addCell(label);
            	}
            }
          
           //写进文档
            wwb.write();
           // 关闭Excel工作簿对象
            wwb.close();

            //获取当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = df.format(new Date());
            String filename = "PMO_Candidate_Info_"+date+".xls";

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(fileName));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename,"UTF-8"));
            //response.setContentType("application/octet-stream");
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            
           file.delete();         
     } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } 
        return null;
    }
}
