package com.pmo.dashboard.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.StayinCandidate;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.HSBCDeptService;
import com.pom.dashboard.service.HSBCProjectService;
import com.pom.dashboard.service.UserService;

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
	
	@Resource
	private UserService userService;

	
    @RequestMapping("/welcome")
    public String welcome(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "welcome";
    }
	
    @SuppressWarnings("unchecked")
	@RequestMapping("/index")
    public String index(final HttpServletRequest request,
            final HttpServletResponse response,Model model,String candId)
    {
    	Employee em = new Employee();
		List<StayinCandidate> list1 = (List<StayinCandidate>) request.getSession().getAttribute("candidateList");
    	List<Demand> list2 = (List<Demand>) request.getSession().getAttribute("demandList");
    	//拿到需求和待入职的人员信息  gkf
    	if(list1!=null &&list1.size()>0&&list2!=null &&list2.size()>0&&candId!=null) {
    		for(StayinCandidate candidate :list1 ) {
    			if(candidate.getCandidateId().equals(candId)) {
    				em.setStaffName(candidate.getCandidateName());
    				//em.setBillRate();
    				em.setGraduationDate(candidate.getGraduateDate());
    			}
    		}
    		for(Demand demand:list2) {
    			if(candId.equals(demand.getCandidateId())) {
    				Demand demandinfo=(Demand) request.getAttribute("onboardInfo");
    				em.setStaffRegion(demand.getLocation());
    				em.setStaffLocation("China");
    				em.setSow(demand.getDoNumber());
    				em.setRole(demand.getPosition());
    				em.setSkill(demand.getSkill());
    				em.setResourceStatus("Active");
    			}
    		}
    		model.addAttribute("employee", em);
			return "OnboardEmployeeInsert";
    	}
    	
        return "indexa";
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
        String eHr = request.getParameter("eHr");
        String lob = request.getParameter("lob");
        String hsbcStaffId = request.getParameter("hsbcStaffId");
        String staffName = request.getParameter("staffName");
        String LN = request.getParameter("LN");
        String staffRegion = request.getParameter("staffRegion");
        String staffLocation = request.getParameter("staffLocation");
        String locationType = request.getParameter("locationType");
        String onshoreOrOffshore = request.getParameter("onshoreOrOffshore");
        String csSubDept = request.getParameter("csSubDept");
        String hsbcSubDept = request.getParameter("hsbcSubDept");
        String projectName = request.getParameter("projectName");
        String projectManager = request.getParameter("projectManager");
        String sow = request.getParameter("sow");
        String sowExpiredDate = request.getParameter("sowExpiredDate");
        String staffCategory = request.getParameter("staffCategory");
        String engagementType = request.getParameter("engagementType");
        String hsbcDOJ = request.getParameter("hsbcDOJ");
        String graduationDate = request.getParameter("graduationDate");
        String role = request.getParameter("role");
        String skill = request.getParameter("skill");
        String billingCurrency = request.getParameter("billingCurrency");
        String billRate = request.getParameter("billRate");
        String resourceStatus = request.getParameter("resourceStatus");
        String terminatedDate = request.getParameter("terminatedDate");
        String terminationReason = request.getParameter("terminationReason");
        
        String email = request.getParameter("email");
        String gbGf = request.getParameter("gbGf");
        String entryDate = request.getParameter("entryDate");
        String rmUserId = request.getParameter("rmUserId");
        Employee employee = new Employee(employeeId,eHr,lob,
                hsbcStaffId, staffName, LN, staffRegion,
                staffLocation, locationType, onshoreOrOffshore,
                csSubDept, hsbcSubDept, projectName,
                projectManager, sow, sowExpiredDate,
                staffCategory, engagementType, hsbcDOJ,
                graduationDate, role, skill,
                billingCurrency, billRate, resourceStatus,
                terminatedDate, terminationReason,email,gbGf,entryDate,rmUserId);
        
        boolean resultFlag = employeeService.addEmployee(employee);
        
        return resultFlag;
    }
   
    
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public boolean updateEmployee(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String employeeId = request.getParameter("employeeId");
        String eHr = request.getParameter("eHr");
        String lob = request.getParameter("lob");
        String hsbcStaffId = request.getParameter("hsbcStaffId");
        String staffName = request.getParameter("staffName");
        String LN = request.getParameter("LN");
        String staffRegion = request.getParameter("staffRegion");
        String staffLocation = request.getParameter("staffLocation");
        String locationType = request.getParameter("locationType");
        String onshoreOrOffshore = request.getParameter("onshoreOrOffshore");
        String csSubDept = request.getParameter("csSubDept");
        String hsbcSubDept = request.getParameter("hsbcSubDept");
        String projectName = request.getParameter("projectName");
        String projectManager = request.getParameter("projectManager");
        String sow = request.getParameter("sow");
        String sowExpiredDate = request.getParameter("sowExpiredDate");
        String staffCategory = request.getParameter("staffCategory");
        String engagementType = request.getParameter("engagementType");
        String hsbcDOJ = request.getParameter("hsbcDOJ");
        String graduationDate = request.getParameter("graduationDate");
        String role = request.getParameter("role");
        String skill = request.getParameter("skill");
        String billingCurrency = request.getParameter("billingCurrency");
        String billRate = request.getParameter("billRate");
        String resourceStatus = request.getParameter("resourceStatus");
        String terminatedDate = request.getParameter("terminatedDate");
        String terminationReason = request.getParameter("terminationReason");
        
        String email = request.getParameter("email");
        String gbGf = request.getParameter("gbGf");
        String entryDate = request.getParameter("entryDate");
        String rmUserId = request.getParameter("rmUserId");
        Employee employee = new Employee(employeeId,eHr,lob,
                hsbcStaffId, staffName, LN, staffRegion,
                staffLocation, locationType, onshoreOrOffshore,
                csSubDept, hsbcSubDept, projectName,
                projectManager, sow, sowExpiredDate,
                staffCategory, engagementType, hsbcDOJ,
                graduationDate, role, skill,
                billingCurrency, billRate, resourceStatus,
                terminatedDate, terminationReason,email,gbGf,entryDate,rmUserId);
        
        boolean resultFlag = employeeService.updateEmployee(employee);
        
        return resultFlag;
    }

    /**
     * 校验Ehr是否存在
     * gkf
     * @param eHr
     * @return
     */
    @RequestMapping("/checkEhr")
    @ResponseBody
	public String checkEhr(String eHr){
		boolean result = true;
		
		List<Employee> e = employeeService.selectByEhr(eHr);
		
		if(e!=null&&e.size()>0){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }
        return resultString;
	}
    /**
     * 校验员工id是否存在
     * gkf
     * @param hsbcStaffId
     * @return
     */
    @RequestMapping("/checkHSBCStaffID")
	@ResponseBody
	public String checkHSBCStaffID(String hsbcStaffId){
		boolean result = true;
		
		List<Employee> e = employeeService.selectByHSBCStaffID(hsbcStaffId);
		
		if(e!=null&&e.size()>0){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }
        return resultString;
	}
    /**
     * 校验lob是否存在
     * gkf
     * @param lob
     * @return
     */
    @RequestMapping("/checkLob")
	@ResponseBody
	public String checkLob(String lob){
		boolean result = true;
		
		List<Employee> e = employeeService.selectByLob(lob);
		
		if(e!=null&&e.size()>0){
			result = false;
		}
		Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }
        return resultString;
    }
@RequestMapping("/exportExcel")
    @ResponseBody
    public HttpServletResponse exportExcel(HttpServletRequest request,
             HttpServletResponse response)
    {
        //EmployeePageCondition empListCondition = (EmployeePageCondition) request.getSession().getAttribute("empListCondition");
        
        EmployeePageCondition empListCondition = (EmployeePageCondition) request.getSession().getAttribute("employeePageCondition");
        
        List<String> conditionList = (List<String>) request.getSession().getAttribute("conditionList");
        
        try {
               //获取当前日期
               SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
               String date = df.format(new Date());
            
               String fileName =  Constants.PATH+""+Utils.getUUID()+".xls";
               
               
               WritableWorkbook wwb = null;
            
               // 创建可写入的Excel工作簿
               
               //创建 Constants.PATH文件夹
               File folder = new File( Constants.PATH);
               if(!folder.exists()){
            	   folder.mkdirs();
               }
               
               //创建  file
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
               
               List<CSDept> allCSDept = csDeptService.queryAllCSDept();               
               List<HSBCDept> allHSBCDept = hsbcDeptService.queryHSBCDeptName();
               List<User> allRM = userService.getUserForRM();
               
               CSDept csDept = new CSDept();
               HSBCDept hsbcDept = new HSBCDept();
               
               for (int i = 1; i - 1 < listE.size(); i++) {
				
					for (CSDept csD : allCSDept) {
						if (csD.getCsSubDeptId().equals(listE.get(i - 1).getCsSubDept())) {
							csDept = csD;
							break;
						}
					}
	
					for (HSBCDept hsbcD : allHSBCDept) {
						if (hsbcD.getHsbcSubDeptId().equals(listE.get(i - 1).getHsbcSubDept())) {
							hsbcDept = hsbcD;
							break;
						}
					}
                   
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
                   
                   if(conditionList.contains("E-Mail")){
                       Label label= new Label(++j, i, listE.get(i-1).getEmail());
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
                       Label label= new Label(++j, i, listE.get(i-1).getOnshoreOrOffshore());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("GB/GF")){
                       Label label= new Label(++j, i, listE.get(i-1).getGbGf());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("HSBC Dept")){
                       Label label = null;
                       if(hsbcDept == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, hsbcDept.getHsbcDeptName());
                       }
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("HSBC Sub Dept")){
                       Label label = null;
                       if(hsbcDept == null){
                           label= new Label(++j, i, "");
                       }else if(hsbcDept.getHsbcSubDeptName() == null || ("").equals(hsbcDept.getHsbcSubDeptName())){
                           label= new Label(++j, i, hsbcDept.getHsbcDeptName());
                       }else{
                           label= new Label(++j, i, hsbcDept.getHsbcSubDeptName());
                       }
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("HSBC Manager")){
                       Label label= new Label(++j, i, listE.get(i-1).getProjectManager());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Project Name")){
                       Label label= new Label(++j, i, listE.get(i-1).getProjectName());
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
                       Label label= new Label(++j, i, listE.get(i-1).getHsbcDOJ());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Experience on HSBC account in Months")){
                       Label label = null; 
                       if(listE.get(i-1).getHsbcDOJ() == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, Utils.caculateMonth(listE.get(i-1).getHsbcDOJ())+"");
                       }
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Graduation Date")){
                       Label label = null;
                       if(listE.get(i-1).getGraduationDate() == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, listE.get(i-1).getGraduationDate());
                       }
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Total Experience in Months")){
                       Label label = null;
                       if(listE.get(i-1).getGraduationDate() == null){
                           label= new Label(++j, i, "");
                       }else{
                           label= new Label(++j, i, Utils.caculateMonth(listE.get(i-1).getGraduationDate())+"");
                       }
                       
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
                   
                   if(conditionList.contains("Entry Date")){
                       Label label= new Label(++j, i, listE.get(i-1).getEntryDate());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Billing Currency")){
                       Label label= new Label(++j, i, listE.get(i-1).getBillingCurrency());
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
                   
                   if(conditionList.contains("If terminated mention LWD")){
                       Label label= new Label(++j, i, listE.get(i-1).getTerminatedDate());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("Reason for Termination")){
                       Label label= new Label(++j, i, listE.get(i-1).getTerminationReason());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("E-HR")){
                       Label label= new Label(++j, i, listE.get(i-1).geteHr());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("LOB")){
                       Label label= new Label(++j, i, listE.get(i-1).getLob());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("RM")){
						String RMName = "";
						for (User user : allRM) {
							if (user.getUserId().equalsIgnoreCase(listE.get(i - 1).getRmUserId())) {
								RMName = user.getNickname();
								break;
							}
						}
                       Label label= new Label(++j, i, RMName);
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("AM")){
                       Label label= new Label(++j, i, csDept.getAm());
                       ws.addCell(label);
                   }
                   
                   if(conditionList.contains("CS Dept")){
                       Label label= new Label(++j, i, csDept.getCsSubDeptName());
                       ws.addCell(label);
                   }
                   
               }
             
              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();
               
               String filename = "GSV Engagement Dashboard_"+date+".xls";
               
               //fileName = URLEncoder.encode(filename,"UTF-8");
               //fileName = fileName.replace("+", " ");

               // 以流的形式下载文件。
               InputStream fis = new BufferedInputStream(new FileInputStream(fileName));
               byte[] buffer = new byte[fis.available()];
               fis.read(buffer);
               fis.close();
               // 清空response
               response.reset();
               // 设置response的Header
               response.addHeader("Content-Disposition", "attachment;filename=" + filename);
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
    


/**
 * 校验员工id是否存在
 * @param hsbcStaffId
 * @return
 */
@RequestMapping("/getTMemployee")
public String getTMemployee(final HttpServletRequest request,
        final HttpServletResponse response,String status){
	
	if("1".equals(status)){
		return "index2";
	}else if("2".equals(status)){
		return "index2";
	}else if("3".equals(status)){
		return "index4";
	}else if("4".equals(status)){
		return "index3";
	}
	return "index2";
}

}