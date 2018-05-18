package com.pmo.dashboard.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HSBCDept;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.HSBCDeptService;

import jxl.Sheet;
import jxl.Workbook;
@Component
public class InportEmployeeInfo
{
    
	
	
    public static void main(String[] args){
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/spring-mybatis.xml");
    	EmployeeService employeeService = (EmployeeService) context.getBean(EmployeeService.class);
    	HSBCDeptService hsbcDeptService = (HSBCDeptService) context.getBean(HSBCDeptService.class);
    	List<Employee> list = new ArrayList<Employee>();
        try
        {
            Workbook rwb = Workbook.getWorkbook(new File("C:/Users/gaonana/Desktop/a.xls"));
            Sheet rs = rwb.getSheet(0);
            int clos = rs.getColumns();
            int rows = rs.getRows();
            HSBCDept hsbcDept = new HSBCDept();
            Employee em = new Employee();
            for(int i=1;i<rows;i++){
                //处理GBGF
            	/**String ehr = rs.getCell(3, i).getContents();
            	String gbgf = rs.getCell(0, i).getContents();
            	System.out.println(ehr);
            	//根据Name去获取ID
            	hsbcDept.setName(gbgf);
            	List<HSBCDept> hsbcDeptList = hsbcDeptService.queryById(hsbcDept);


            	if(hsbcDeptList!=null && hsbcDeptList.size()>0){
            		em.seteHr(ehr);
                	em.setGbGf(hsbcDeptList.get(0).getId());
                	employeeService.importEmployeeProject(em);
            	}*/
            	//处理HSBCDept和HSBCSubDept
            	String ehr = rs.getCell(3, i).getContents();
            	String hsbcDeptt = rs.getCell(1, i).getContents();
            	String hsbcSubDeptt = rs.getCell(2, i).getContents();
            	
            	System.out.println(ehr);
            	//根据Name去获取ID
            	HSBCDept hsbcDept2 = new HSBCDept();
            	HSBCDept hsbcDept3 = new HSBCDept();
            	hsbcDept2.setName(hsbcDeptt);
            	hsbcDept3.setName(hsbcSubDeptt);
            	List<HSBCDept> hsbcDeptList1 = hsbcDeptService.queryById(hsbcDept2);
            	List<HSBCDept> hsbcDeptList2 = hsbcDeptService.queryById(hsbcDept3);

            	String zuhe="";
            	if(hsbcDeptList1!=null && hsbcDeptList1.size()>0){
            		zuhe = zuhe+hsbcDeptList1.get(0).getId()+",";
            		if(hsbcDeptList2!=null && hsbcDeptList2.size()>0){
            			zuhe = zuhe + hsbcDeptList2.get(0).getId();
            		}
            	}
            	em.seteHr(ehr);
                em.setHsbcSubDept(zuhe);
                employeeService.importEmployeeProject(em);
            }
            System.out.println("处理完成");    
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return;
    }

}
