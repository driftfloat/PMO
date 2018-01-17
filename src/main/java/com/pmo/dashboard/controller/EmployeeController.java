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
import com.pmo.dashboard.entity.EmployeeLog;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.StayinCandidate;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.util.Constants;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeLogService;
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
	
	@Resource
	private EmployeeLogService  employeeLogService;

	
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
            final HttpServletResponse response,String type)
    {
        String employeeId = request.getParameter("employeeId");
        request.setAttribute("employeeId", employeeId);
        
        if("1".equals(type)) {
        	return "employee/updateEmployeeInfo";
    	}else if("2".equals(type)) {
    		return "employee/updateEmployeeInfo2";
    	}else {
    		return "employee/updateEmployeeInfo3";
    	}
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
        
        /**
         * 添加日志
         */
        /*try{
        	User user = (User)request.getSession().getAttribute("loginUser");
        	EmployeeLog log = getEmployeeLog(employee,user,"0");
    		@SuppressWarnings({ "unused", "unchecked" })
			boolean flag = employeeLogService.save(log);
        }catch(Exception e){
        	e.printStackTrace();
        }*/
        
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
        
        /**
         * 添加日志
         */
        /*try{
        	User user = (User)request.getSession().getAttribute("loginUser");
        	EmployeeLog log = getEmployeeLog(employee,user,"1");
    		@SuppressWarnings({ "unused", "unchecked" })
			boolean flag = employeeLogService.save(log);
        }catch(Exception e){
        	e.printStackTrace();
        }*/
        
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
               
               CSDept csDept = null;
               HSBCDept hsbcDept = null;
               
               for (int i = 1; i - 1 < listE.size(); i++) {
				if(listE.get(i - 1).getCsSubDept()!=null){
					for (CSDept csD : allCSDept) {
						if (csD.getCsSubDeptId().equals(listE.get(i - 1).getCsSubDept())) {
							csDept = csD;
							break;
						}
					}
				}else{
					csDept =null;
				}
	
				if(listE.get(i - 1).getHsbcSubDept()!=null){
					for (HSBCDept hsbcD : allHSBCDept) {
						if (hsbcD.getHsbcSubDeptId().equals(listE.get(i - 1).getHsbcSubDept())) {
							hsbcDept = hsbcD;
							break;
						}
					}
				}else {
					hsbcDept = null;
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
                	   if(csDept!=null) {
                		   Label label= new Label(++j, i, csDept.getAm());
                           ws.addCell(label);
                	   }else {
                		   Label label= new Label(++j, i, "");
                           ws.addCell(label);
                	   }
                       
                   }
                   
                   if(conditionList.contains("CS Dept")){
                	   if(csDept!=null) {
                		   Label label= new Label(++j, i, csDept.getCsSubDeptName());
                           ws.addCell(label); 
                	   }else {
                		   Label label= new Label(++j, i, "");
                           ws.addCell(label); 
                	   }
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
		return "index1";
	/*}else if("2".equals(status)){
		return "index2";*/
	}else if("3".equals(status)){
		return "index3";
	}else if("4".equals(status)){		return "index4";
	}
	return "index2";
}

    private EmployeeLog getEmployeeLog(Employee employee,User user,String type){
    	//修改前
    	Employee em = employeeService.queryEmployeeById(employee.getEmployeeId());
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    	String time = simpleDateFormat.format(new Date());
    	EmployeeLog log = new EmployeeLog();
    	StringBuffer changeInfo = null;
    	StringBuffer addInfo = null;
    	if(type.equals("0")){
    		addInfo = addInfo(employee);
    	}
        if(type.equals("1")){
        	changeInfo = checkFieldChange(em,employee);
    	}
    	if(em!=null && employee !=null){
    		if(!em.getCsSubDept().equals(employee.getCsSubDept())){
        		log.setCsSubdeptIdNew(employee.getCsSubDept());
            	log.setCsSubdeptIdOriginal(em.getCsSubDept());
        	}
            if(!em.getRole().equals(employee.getRole())){
            	log.setRoleNew(employee.getRole());
            	log.setRoleOriginal(em.getRole());
        	}
            if(!em.getResourceStatus().equals(employee.getResourceStatus())){
            	log.setStatusNew(employee.getResourceStatus());
            	log.setStatusOriginal(em.getResourceStatus());
        	}
        	if(type.equals("0")){
        		log.setChangeInformation(addInfo.toString());
        	}
            if(type.equals("1")){
            	log.setChangeInformation(changeInfo.toString());
        	}
        	log.setEmployeeId(employee.getEmployeeId());
        	log.setLogId(Utils.getUUID());
        	if(type.equals("0")){
        		log.setLogType("0");
        	}
            if(type.equals("1")){
            	log.setLogType("1");
        	}
        	log.setOperationPerson(user.getUserId());
        	log.setUpdateDate(time);
    	}
    	return log;
    }
    private StringBuffer checkFieldChange(Employee before,Employee after){
    	StringBuffer changeInfo = new StringBuffer();
    	if(before!=null && after!=null){
    		//E-HR
            if(before.geteHr()!=null && !before.geteHr().equals(after.geteHr())){
            	changeInfo.append("eHr由["+before.geteHr()+"]变更为["+after.geteHr()+"];");
        	}
        	//LOB
            if(before.getLob()!=null && !before.getLob().equals(after.getLob())){
            	changeInfo.append("lob由["+before.getLob()+"]变更为["+after.getLob()+"];");
        	}
        	//HSBC Staff ID
            if(before.getHsbcStaffId()!=null && !before.getHsbcStaffId().equals(after.getHsbcStaffId())){
            	changeInfo.append("hsbcStaffId由["+before.getHsbcStaffId()+"]变更为["+after.getHsbcStaffId()+"];");
        	}
        	//Staff Name
            if(before.getStaffName()!=null && !before.getStaffName().equals(after.getStaffName())){
            	changeInfo.append("staffName由["+before.getStaffName()+"]变更为["+after.getStaffName()+"];");
        	}
        	//LN
            if(before.getLn()!=null && !before.getLn().equals(after.getLn())){
            	changeInfo.append("ln由["+before.getLn()+"]变更为["+after.getLn()+"];");
        	}
        	//Email
            if(before.getEmail()!=null && !before.getEmail().equals(after.getEmail())){
            	changeInfo.append("email由["+before.getEmail()+"]变更为["+after.getEmail()+"];");
        	}
        	//Staff location
            if(before.getStaffLocation()!=null && !before.getStaffLocation().equals(after.getStaffLocation())){
            	changeInfo.append("staffLocation由["+before.getStaffLocation()+"]变更为["+after.getStaffLocation()+"];");
        	}
        	//Location Type
            if(before.getLocationType()!=null && !before.getLocationType().equals(after.getLocationType())){
            	changeInfo.append("locationType由["+before.getLocationType()+"]变更为["+after.getLocationType()+"];");
        	}
        	//CS Dept
            if(before.getCsSubDept()!=null && !before.getCsSubDept().equals(after.getCsSubDept())){
            	CSDept old = csDeptService.queryCSDeptById(before.getCsSubDept());
            	CSDept news = csDeptService.queryCSDeptById(after.getCsSubDept());
            	String oldname="";
            	String newsname="";
            	if(old!=null){
            		oldname=old.getCsSubDeptName();
            	}
            	if(news!=null){
            		newsname=news.getCsSubDeptName();
            	}
            	changeInfo.append("csSubDept由["+oldname+"]变更为["+newsname+"];");
        	}
            
        	//GBGF
            if(before.getGbGf()!=null && !before.getGbGf().equals(after.getGbGf())){
            	changeInfo.append("gbGf由["+before.getGbGf()+"]变更为["+after.getGbGf()+"];");
        	}
        	//HSBC Sub Dept
            if(before.getHsbcSubDept()!=null && !before.getHsbcSubDept().equals(after.getHsbcSubDept())){
            	HSBCDept old = hsbcDeptService.queryHSBCSubDeptById(before.getHsbcSubDept());
            	HSBCDept news = hsbcDeptService.queryHSBCSubDeptById(after.getHsbcSubDept());
            	String oldname="";
            	String newsname="";
            	if(old!=null){
            		oldname=old.getHsbcSubDeptName();
            	}
            	if(news!=null){
            		newsname=news.getHsbcSubDeptName();
            	}
            	changeInfo.append("hsbcSubDept由["+oldname+"]变更为["+newsname+"];");
        	}
        	//HSBC Project Name
            if(before.getProjectName()!=null && !before.getProjectName().equals(after.getProjectName())){
            	changeInfo.append("projectName由["+before.getProjectName()+"]变更为["+after.getProjectName()+"];");
        	}
        	//HSBC Project Manager
            if(before.getProjectManager()!=null && !before.getProjectManager().equals(after.getProjectManager())){
            	changeInfo.append("projectManager由["+before.getProjectManager()+"]变更为["+after.getProjectManager()+"];");
        	}
        	//SOW#
            if(before.getSow()!=null && !before.getSow().equals(after.getSow())){
            	changeInfo.append("sow由["+before.getSow()+"]变更为["+after.getSow()+"];");
        	}
        	//SOW# Expired Date
            if(before.getSowExpiredDate()!=null && !before.getSowExpiredDate().equals(after.getSowExpiredDate())){
            	changeInfo.append("sowExpiredDate由["+before.getSowExpiredDate()+"]变更为["+after.getSowExpiredDate()+"];");
        	}
        	//Staff Category
            if(before.getStaffCategory()!=null && !before.getStaffCategory().equals(after.getStaffCategory())){
            	changeInfo.append("staffCategory由["+before.getStaffCategory()+"]变更为["+after.getStaffCategory()+"];");
        	}
        	//Engagement Type
            if(before.getEngagementType()!=null && !before.getEngagementType().equals(after.getEngagementType())){
            	changeInfo.append("engagementType由["+before.getEngagementType()+"]变更为["+after.getEngagementType()+"];");
        	}
        	//Graduation Date
            if(before.getGraduationDate()!=null && !before.getGraduationDate().equals(after.getGraduationDate())){
            	changeInfo.append("graduationDate由["+before.getGraduationDate()+"]变更为["+after.getGraduationDate()+"];");
        	}
        	//EntryDate
            if(before.getEntryDate()!=null && !before.getEntryDate().equals(after.getEntryDate())){
            	changeInfo.append("entryDate由["+before.getEntryDate()+"]变更为["+after.getEntryDate()+"];");
        	}
        	//Staff Region
            if(before.getStaffRegion()!=null && !before.getStaffRegion().equals(after.getStaffRegion())){
            	changeInfo.append("staffRegion由["+before.getStaffRegion()+"]变更为["+after.getStaffRegion()+"];");
        	}
        	//Onshore or Offshore
            if(before.getOnshoreOrOffshore()!=null && !before.getOnshoreOrOffshore().equals(after.getOnshoreOrOffshore())){
            	changeInfo.append("onshoreOrOffshore由["+before.getOnshoreOrOffshore()+"]变更为["+after.getOnshoreOrOffshore()+"];");
        	}
        	//MSA Role
            if(before.getRole()!=null && !before.getRole().equals(after.getRole())){
            	changeInfo.append("role由["+before.getRole()+"]变更为["+after.getRole()+"];");
        	}
        	//Skills/Technology
            if(before.getSkill()!=null && !before.getSkill().equals(after.getSkill())){
            	changeInfo.append("skill由["+before.getSkill()+"]变更为["+after.getSkill()+"];");
        	}
        	//Billing Currency
            if(before.getBillingCurrency()!=null && !before.getBillingCurrency().equals(after.getBillingCurrency())){
            	changeInfo.append("billingCurrency由["+before.getBillingCurrency()+"]变更为["+after.getBillingCurrency()+"];");
        	}
        	//Bill Rate
            if(before.getBillRate()!=null && !before.getBillRate().equals(after.getBillRate())){
            	changeInfo.append("billRate由["+before.getBillRate()+"]变更为["+after.getBillRate()+"];");
        	}
        	//HSBC DOJ
            if(before.getHsbcDOJ()!=null && !before.getHsbcDOJ().equals(after.getHsbcDOJ())){
            	changeInfo.append("hsbcDOJ由["+before.getHsbcDOJ()+"]变更为["+after.getHsbcDOJ()+"];");
        	}
        	//Resource Status
            if(before.getResourceStatus()!=null && !before.getResourceStatus().equals(after.getResourceStatus())){
            	changeInfo.append("resourceStatus由["+before.getResourceStatus()+"]变更为["+after.getResourceStatus()+"];");
        	}
        	//LWD
            if(before.getTerminatedDate()!=null && !before.getTerminatedDate().equals(after.getTerminatedDate())){
            	changeInfo.append("terminatedDate由["+before.getTerminatedDate()+"]变更为["+after.getTerminatedDate()+"];");
        	}
        	//Reason for Termination
            if(before.getTerminationReason()!=null && !before.getTerminationReason().equals(after.getTerminationReason())){
            	changeInfo.append("terminationReason由["+before.getTerminationReason()+"]变更为["+after.getTerminationReason()+"];");
        	}
            //interviewStatus
            if(before.getInterviewStatus()!=null && !before.getInterviewStatus().equals(after.getInterviewStatus())){
            	changeInfo.append("interviewStatus由["+before.getInterviewStatus()+"]变更为["+after.getInterviewStatus()+"];");
            }
    	}
    	return changeInfo;
    }
    
    
    private StringBuffer addInfo(Employee employee){
    	StringBuffer changeInfo = new StringBuffer();
    	if(employee!=null){
    		//E-HR
            changeInfo.append("eHr["+employee.geteHr()+"];");
        	//LOB
            changeInfo.append("lob["+employee.getLob()+"];");
        	//HSBC Staff ID
            changeInfo.append("hsbcStaffId["+employee.getHsbcStaffId()+"];");
        	//Staff Name
            changeInfo.append("staffName["+employee.getStaffName()+"];");
        	//LN
            changeInfo.append("ln["+employee.getLn()+"];");
        	//Email
            changeInfo.append("email["+employee.getEmail()+"];");
        	//Staff location
            changeInfo.append("staffLocation["+employee.getStaffLocation()+"];");
        	//Location Type
            changeInfo.append("locationType["+employee.getLocationType()+"];");
        	//CS Dept
            changeInfo.append("csSubDept["+employee.getCsSubDept()+"];");
        	//GBGF
            changeInfo.append("gbGf["+employee.getGbGf()+"];");
        	//HSBC Dept
//            if(!before.get.equals(after.getHsbcSubDept())){
//        		
//        	}
        	//HSBC Sub Dept
            changeInfo.append("hsbcSubDept["+employee.getHsbcSubDept()+"];");
        	//HSBC Project Name
            changeInfo.append("projectName["+employee.getProjectName()+"];");
        	//HSBC Project Manager
            changeInfo.append("projectManager["+employee.getProjectManager()+"];");
        	//SOW#
            changeInfo.append("sow["+employee.getSow()+"];");
        	//SOW# Expired Date
            changeInfo.append("sowExpiredDate["+employee.getSowExpiredDate()+"];");
        	//Staff Category
            changeInfo.append("staffCategory["+employee.getStaffCategory()+"];");
        	//Engagement Type
            changeInfo.append("engagementType["+employee.getEngagementType()+"];");
        	//Graduation Date
            changeInfo.append("graduationDate["+employee.getGraduationDate()+"];");
        	//EntryDate
            changeInfo.append("entryDate["+employee.getEntryDate()+"];");
        	//Staff Region
            changeInfo.append("staffRegion["+employee.getStaffRegion()+"];");
        	//Onshore or Offshore
            changeInfo.append("onshoreOrOffshore["+employee.getOnshoreOrOffshore()+"];");
        	//MSA Role
            changeInfo.append("role["+employee.getRole()+"];");
        	//Skills/Technology
            changeInfo.append("skill["+employee.getSkill()+"];");
        	//Billing Currency
            changeInfo.append("billingCurrency["+employee.getBillingCurrency()+"];");
        	//Bill Rate
            changeInfo.append("billRate["+employee.getBillRate()+"];");
        	//HSBC DOJ
            changeInfo.append("hsbcDOJ["+employee.getHsbcDOJ()+"];");
        	//Resource Status
            changeInfo.append("resourceStatus["+employee.getResourceStatus()+"];");
        	//LWD
            changeInfo.append("terminatedDate["+employee.getTerminatedDate()+"];");
        	//Reason for Termination
            changeInfo.append("terminationReason["+employee.getTerminationReason()+"];");
            //interviewStatus
            changeInfo.append("interviewStatus["+employee.getInterviewStatus()+"];");
    	}
    	return changeInfo;
    }
    
}