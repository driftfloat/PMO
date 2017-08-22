package com.pmo.dashboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.HSBCProject;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.HSBCDeptService;
import com.pom.dashboard.service.HSBCProjectService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController {
	
	private static Logger logger = LoggerFactory
            .getLogger(EmployeeController.class);
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
    private CSDeptService csDeptService;
	
	@Resource
    private HSBCDeptService hsbcDeptService;
	
	@Resource
    private HSBCProjectService hsbcProjectService;

	
    @RequestMapping("/index")
    public String index(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "index";
    }
    
    @RequestMapping("/employeeInfo")
    public String employeeInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "employee/employeeInfo";
    }
    
    @RequestMapping("/updateEmployeeInfo")
    public String updateEmployeeInfo(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String employeeId = request.getParameter("employeeId");
        request.setAttribute("employeeId", employeeId);
        return "employee/updateEmployeeInfo";
    }
    
    
    @RequestMapping("/queryEmployeeById")
    @ResponseBody
    public Object queryEmployeeById(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String employeeId = request.getParameter("employeeId");
        
        Employee employee = employeeService.queryEmployeeById(employeeId);
        
        return employee;
    }
    
    
    @RequestMapping("/addEmployee")
    @ResponseBody
    public boolean addEmployee(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String employeeId = Utils.getUUID();
        String hsbcStaffId = request.getParameter("hsbcStaffId");
        String staffName = request.getParameter("staffName");
        String LN = request.getParameter("LN");
        String staffRegion = request.getParameter("staffRegion");
        String staffLocation = request.getParameter("staffLocation");
        String locationType = request.getParameter("locationType");
        String onshoreOrOffshore = request.getParameter("onshoreOrOffshore");
        String projectName = request.getParameter("projectName");
        String sow = request.getParameter("sow");
        String sowExpiredDate = request.getParameter("sowExpiredDate");
        String staffCategory = request.getParameter("staffCategory");
        String engagementType = request.getParameter("engagementType");
        String hsbcDOJ = request.getParameter("hsbcDOJ");
        String graduationDate = request.getParameter("graduationDate");
        String role = request.getParameter("role");
        String skill = request.getParameter("skill");
        String billingEntity = request.getParameter("billingEntity");
        String billingCurrency = request.getParameter("billingCurrency");
        String billRate = request.getParameter("billRate");
        String resourceStatus = request.getParameter("resourceStatus");
        String terminatedDate = request.getParameter("terminatedDate");
        String terminationReason = request.getParameter("terminationReason");
        String eHr = request.getParameter("eHr");
        String csSubDept = request.getParameter("csSubDept");
        String nicheSkill = request.getParameter("nicheSkill");
        
        Employee employee = new Employee(employeeId,hsbcStaffId,staffName,
                LN,staffRegion,staffLocation,
                locationType, onshoreOrOffshore, sow,
                sowExpiredDate, staffCategory, engagementType,
                hsbcDOJ, graduationDate, billingEntity,
                billingCurrency, billRate, resourceStatus,
                terminatedDate, terminationReason, eHr,
                nicheSkill, projectName, role, skill,
                csSubDept);
        
        boolean resultFlag = employeeService.addEmployee(employee);
        
        return resultFlag;
    }
   
    
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public boolean updateEmployee(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String employeeId = request.getParameter("employeeId");
        String hsbcStaffId = request.getParameter("hsbcStaffId");
        String staffName = request.getParameter("staffName");
        String LN = request.getParameter("LN");
        String staffRegion = request.getParameter("staffRegion");
        String staffLocation = request.getParameter("staffLocation");
        String locationType = request.getParameter("locationType");
        String onshoreOrOffshore = request.getParameter("onshoreOrOffshore");
        String projectName = request.getParameter("projectName");
        String sow = request.getParameter("sow");
        String sowExpiredDate = request.getParameter("sowExpiredDate");
        String staffCategory = request.getParameter("staffCategory");
        String engagementType = request.getParameter("engagementType");
        String hsbcDOJ = request.getParameter("hsbcDOJ");
        String graduationDate = request.getParameter("graduationDate");
        String role = request.getParameter("role");
        String skill = request.getParameter("skill");
        String billingEntity = request.getParameter("billingEntity");
        String billingCurrency = request.getParameter("billingCurrency");
        String billRate = request.getParameter("billRate");
        String resourceStatus = request.getParameter("resourceStatus");
        String terminatedDate = request.getParameter("terminatedDate");
        String terminationReason = request.getParameter("terminationReason");
        String eHr = request.getParameter("eHr");
        String csSubDept = request.getParameter("csSubDept");
        String nicheSkill = request.getParameter("nicheSkill");
        
        Employee employee = new Employee(employeeId,hsbcStaffId,staffName,
                LN,staffRegion,staffLocation,
                locationType, onshoreOrOffshore, sow,
                sowExpiredDate, staffCategory, engagementType,
                hsbcDOJ, graduationDate, billingEntity,
                billingCurrency, billRate, resourceStatus,
                terminatedDate, terminationReason, eHr,
                nicheSkill, projectName, role, skill,
                csSubDept);
        
        boolean resultFlag = employeeService.updateEmployee(employee);
        
        return resultFlag;
    }
    
    
    @RequestMapping("/exportExcel")
    @ResponseBody
    public HttpServletResponse exportExcel(HttpServletRequest request,
             HttpServletResponse response)
    {
        EmployeePageCondition empListCondition = (EmployeePageCondition) request.getSession().getAttribute("empListCondition");
        
        List<String> conditionList = (List<String>) request.getSession().getAttribute("conditionList");
        
        try {
               //获取当前日期
               SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               String date = df.format(new Date());
            
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
               WritableSheet ws = wwb.createSheet("Headcount", 0);
               
               //查询数据库中所有的数据
               List<Employee> listE = employeeService.queryEmployeeList(empListCondition);
               
               //要插入到的Excel表格的行号，默认从0开始
               
                   Label labelSL= new Label(0, 0, "SL#");
                   ws.addCell(labelSL);
               
               for(int k=0;k<conditionList.size();k++){
                   
                   Label label = new Label(k+1, 0, conditionList.get(k));
                   ws.addCell(label);
               }
               
               //
               CSDept csDept = new CSDept();
               HSBCDept hsbcDept = new HSBCDept();
               HSBCProject hsbcProject = new HSBCProject();
               
               
               for (int i = 1; i-1 < listE.size(); i++) {
                   
                   csDept = csDeptService.queryCSDeptById(listE.get(i-1).getCsSubDeptId());
                   hsbcProject = hsbcProjectService.queryProjectName(listE.get(i-1).getHsbcProjectId());
                   hsbcDept = hsbcDeptService.queryHSBCSubDeptById(listE.get(i-1).getHsbcProjectId());
                   
                   int j = 0;
                   
                   Label labelSL_i= new Label(j, i, i+"");
                   ws.addCell(labelSL_i);
                   
                   if(conditionList.contains("HSBC Staff ID")){
                       Label label= new Label(++j, i, listE.get(i-1).getHsbcStaffId());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Staff Name")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffName());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("LN")){
                       Label label= new Label(++j, i, listE.get(i-1).getLn());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Staff Region")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffRegion());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Staff Location (country)")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffLocation());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Location Type (HSBCOffice/ODC)")){
                       Label label= new Label(++j, i, listE.get(i-1).getLocationType());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Onshore or Offshore")){
                       Label label= new Label(++j, i, listE.get(i-1).getOnShoreOrOffShore());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("HSBC Dept")){
                       Label label= new Label(++j, i, hsbcDept.getHsbcDeptName());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("HSBC Sub Dept")){
                       Label label= new Label(++j, i, hsbcDept.getHsbcSubDeptName());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("HSBC Manager")){
                       Label label= new Label(++j, i, hsbcProject.getHsbcManager());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Project Name")){
                       Label label= new Label(++j, i, hsbcProject.getHsbcProjectName());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Project Description")){
                       Label label= new Label(++j, i, hsbcProject.getHsbcProjectDescription());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("SOW#")){
                       Label label= new Label(++j, i, listE.get(i-1).getSow());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("SOW# Expired Date")){
                       Label label= new Label(++j, i, listE.get(i-1).getSowExpiredDate());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Staff Category(CATG/CATB)")){
                       Label label= new Label(++j, i, listE.get(i-1).getStaffCategory());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Engagement Type (T&M/FixedCost)")){
                       Label label= new Label(++j, i, listE.get(i-1).getEngagementType());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("HSBC DOJ")){
                       Label label= new Label(++j, i, listE.get(i-1).getHsbcDoj());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Experience on HSBC account in Months")){
                       Label label= new Label(++j, i, Utils.caculateMonth(listE.get(i-1).getHsbcDoj())+"");
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Graduation Date")){
                       Label label= new Label(++j, i, listE.get(i-1).getGraduationDate());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Total Experience in Months")){
                       Label label= new Label(++j, i, Utils.caculateMonth(listE.get(i-1).getGraduationDate())+"");
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("MSA Role")){
                       Label label= new Label(++j, i, listE.get(i-1).getRole());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Skills/Technology")){
                       Label label= new Label(++j, i, listE.get(i-1).getSkill());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Billing Entity")){
                       Label label= new Label(++j, i, listE.get(i-1).getBillingEntity());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Billing Currency")){
                       Label label= new Label(++j, i, listE.get(i-1).getBillCurrency());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Bill Rate")){
                       Label label= new Label(++j, i, listE.get(i-1).getBillRate());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Resource Status (Active/Terminated)")){
                       Label label= new Label(++j, i, listE.get(i-1).getResourceStatus());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("If terminated,mention LWD")){
                       Label label= new Label(++j, i, listE.get(i-1).getMentionLWD());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Reason for Termination")){
                       Label label= new Label(++j, i, listE.get(i-1).getReasonForTermination());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("E-HR")){
                       Label label= new Label(++j, i, listE.get(i-1).geteHr());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("AM")){
                       Label label= new Label(++j, i, csDept.getAm());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("交付部")){
                       Label label= new Label(++j, i, csDept.getCsSubDeptName());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("大部门")){
                       Label label= new Label(++j, i, csDept.getCsDeptName());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("niche skill")){
                       Label label= new Label(++j, i, listE.get(i-1).getNicheSkill());
                       ws.addCell(label);
                   }
                   
                   
               }
             
              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();
               
               String filename = "GSV Engagement Dashboard_"+date+".xls";

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

