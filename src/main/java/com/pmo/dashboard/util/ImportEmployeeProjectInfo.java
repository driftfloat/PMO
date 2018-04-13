package com.pmo.dashboard.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.EmployeePageCondition;
import com.pom.dashboard.service.EmployeeService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Component
public class ImportEmployeeProjectInfo {
	private String importFilePath;

	@Value("${importFilePath}")
	public void setImportFilePath(String importFilePath) {
		this.importFilePath = importFilePath;
	}

	public String getImportFilePath() {
		return importFilePath;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/spring-mybatis.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean(EmployeeService.class);
		// System.out.println(employeeService.getAllEmployee().size()); // Invalid bound
		// statement : xml no implement
		// System.out.println(employeeService.queryEmployeeList(new
		// EmployeePageCondition()).size());
		// PropertyPlaceholderConfigurer propertyPlace = (PropertyPlaceholderConfigurer)
		// context
		// .getBean("propertyConfigurer");
		// System.out.println("importFile:" + filePath() );
		ImportEmployeeProjectInfo importEmployeeProjectInfo = (ImportEmployeeProjectInfo) context
				.getBean(ImportEmployeeProjectInfo.class);
		System.out.println(importEmployeeProjectInfo.getImportFilePath());
		String filePath = importEmployeeProjectInfo.getImportFilePath() ;
		importFile(filePath);
	}

	public static void importFile(String filePath) {
		final int SHEETINDEX = 1; // 过程数据
		List<Employee> list = new ArrayList<Employee>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(filePath));
			Sheet rs = rwb.getSheet(SHEETINDEX);
			int clos = rs.getColumns();
			int rowCount = rs.getRows();

			// for (int j = 0; j < rs.getRows(); j++) {
			// Cell[] cells = rs.getRow(j);
			// for(int k=0;k<cells.length;k++) {
			//// sb.append(cells[k].getContents());
			// }
			// }

			for (int row = 1; row <= 2; row++) {
				Employee employee = new Employee();
				
//				String employeeId = Utils.getUUID();
				String value = rs.getCell(5, row).getContents();
				System.out.println(value);
				employee.setEmployeeId(rs.getCell(5, row).getContents());
				
				/*
				 * list.add(new
				 * Employee(employeeId,hsbcStaffId,staffName,ln,staffRegion,staffLocation,
				 * locationType,onShoreOrOffShore,sow,sowExpiredDate,staffCategory,
				 * engagementType,
				 * hsbcDoj,experienceONHSBC,graduationDate,totalExperience,billingEntity,
				 * billCurrency,
				 * billRate,resourceStatus,mentionLWD,reasonForTermination,eHr,nicheSkill,
				 * hsbcProjectId,role,skill,csSubDeptId));
				 */

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	// public static String filePath() {
	// String rtn = "";
	// InputStream inputStream =
	// Object.class.getResourceAsStream("/conf/jdbc.properties");
	// Properties p = new Properties();
	//
	// try {
	// p.load(inputStream);
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// rtn = p.getProperty("importFilePath");
	// return rtn ;
	// }

}
