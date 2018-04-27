package com.pmo.dashboard.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pmo.dashboard.dao.CurrencysMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.Currencys;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.CSDeptService;
import com.pom.dashboard.service.EmployeeService;
import com.pom.dashboard.service.OfflineOperService;
import com.pom.dashboard.service.UserService;

@Component
public class ExportExcel {
	@Resource
	private OfflineOperService offlineOperService;
	
	@Resource
	private CSDeptService csDeptService;
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private UserService userService;
	
	public OfflineOperService getOfflineOperService() {
		return offlineOperService;
	}
	public CSDeptService getCsDeptService() {
		return csDeptService;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public UserService getUserService() {
		return userService;
	}
	public CurrencysMapper getCurrencysMapper() {
		return currencysMapper;
	}


	@Resource
	private CurrencysMapper currencysMapper;
	
	private String exportFile;
	
	public String getExportFile() {
		return exportFile;
	}
	@Value("${exportFile}")
	public void setExportFile(String exportFile) {
		this.exportFile = exportFile;
	}
	
	private String toValue(BigDecimal b) {
		if(null == b) {
			return "";
		}else if(BigDecimal.ZERO.compareTo(b)==0){
			return "";
		}
		return (null == b)? "" : b.toPlainString() ;
	}
	
	public String export(String sheetName, User user ) {
		List<String[]> dataList = exportData(user);
		return exportExcel(dataList, exportFile, sheetName);
	}
	
	private List<String[]> exportData(User user) {
		List<String[]> dataList = new ArrayList<String[]>();
		String[] cellTitle = { "月份", "业务线", "事业部", "执行交付部", "项目类型", "员工E-HR编码", "员工姓名", "项目编号", "项目名称", "工作地" 
				,"技能", "级别", "币种", "当月汇率", "员工单价（h）-原币种", "月标准工时", "实际工时", "无效工时", "加班费工时", "调休工时"
				, "调整上月工时", "实际工时收入-收入1", "无效工时收入", "加班费工时收入-收入2", "调休工时收入-收入3", "调整上月工时收入-收入4" 
				,"差旅收入-收入5", "付费设备收入-收入6", "分包收入-收入7", "当月收入合计-原币种", "当月收入合计-RMB", "当月有效收入", "有效NR-与月滚一致"
				, "当月有效人力", "当月无效人力", "RM", "备注"  };
		dataList.add(cellTitle);
		
		
		OfflineOper condition = new OfflineOper();
//		condition.seteHr("E000834441");
//		condition.setStaffName("白世铭");
		
		List<OfflineOper> list = offlineOperService.exportData(user) ; 
		
		for(OfflineOper o:list) {
			String[] row = new String[cellTitle.length];
			Employee e = employeeService.queryEmployeeById(o.getEmployeeId())  ;
			CSDept csdept = csDeptService.queryCSDeptById(e.getCsSubDept()) ; 
//			csDeptService.queryCSDeptById(e.getCsSubDeptName())
			
			Currencys currencyCondition = new Currencys();
			currencyCondition.setYear(o.getYear());
			currencyCondition.setMonth(o.getMonth());
			String staffLocation = e.getStaffLocation()  ;
			staffLocation = ( null == staffLocation ) ? "China":staffLocation ;
			if("China".equals(staffLocation)  ) {
				staffLocation = "北京";
			}else if("HK".equals(staffLocation)){
				staffLocation = "HK";
			}else if("Malaysia".equals(staffLocation)){
				staffLocation = "马来";
			}
			currencyCondition.setPlaceWork(staffLocation);
			Currencys currencys = currencysMapper.queryCurrency(currencyCondition);
			if(null == currencys) {
				int i = 1;
			}
			if(null == currencys.getExRate()) {
				currencys.setExRate(BigDecimal.ZERO);
			}
			
			row[0] = o.getMonth()+"月";
			row[1] = "汇丰业务线";
			row[2] = csdept == null ?"": csdept.getCsBuName();  // user.getBu() 
			row[3] = e.getCsSubDeptName();  // e.getCsSubDeptName()  user.getCsDept().getCsSubDeptName()
			row[4] = e.getEngagementType() ; 
			
			row[5] = o.geteHr();
			row[6] = o.getStaffName();
			row[7] = e.getChsoftiProNumber();
			row[8] = e.getChsoftiProName();
			row[9] = e.getStaffLocation();
			
			row[10] = e.getSkill();
			row[11] = e.getRole();
			row[12] = StringUtils.isBlank(e.getBillingCurrency()) ?currencys.getCurrency():e.getBillingCurrency();
			row[13] = toValue(currencys.getExRate());  //当月汇率
			row[14] = e.getBillRate();
//			
			row[15] = toValue( o.getChsoftiMskHours() );
			row[16] = toValue(o.getChsoftiAwHours() );
			row[17] = toValue(o.getChsoftiIwHours());
			row[18] = toValue(o.getChsoftiOtHours());
			row[19] = toValue(o.getChsoftiToHours());
//			
			row[20] = toValue(o.getChsoftiApwHours());
			row[21] = toValue(o.getChsoftiIfaw());
			row[22] = toValue(o.getChsoftiInvalid());
			row[23] = toValue(o.getChsoftiInfOt());
			row[24] = toValue(o.getChsoftiInfPt());
//			
			row[25] = toValue(o.getChsoftiInfAd());
			row[26] = toValue(o.getChsoftiInfTravel());
			row[27] = toValue(o.getChsoftiInfEquipment());
			row[28] = toValue(o.getChsoftiInfSub());
			row[29] = toValue(o.getChsoftiInfTotal());   // 收入合计-原币种
//			
			row[30] = toValue(o.getChsoftiInfTotal().multiply(currencys.getExRate()).setScale(2, BigDecimal.ROUND_HALF_UP));  //收入合计-RMB
			row[31] = toValue(o.getChsoftiInfCurrent()); 
			row[32] = toValue(o.getChsoftiEffectiveNr());
			row[33] = toValue(o.getChsoftiEffectiveSt());
			row[34] = toValue(o.getChsoftiInvalidSt());
//			
			if(null == o.getRmName()) {
				User rm = userService.queryUserById(e.getRmUserId());
				if(rm != null) {
					row[35] = rm.getNickname();
				}
				
			}else {
				row[35] = o.getRmName();
			}
			
			row[36] = o.getRemark();
			
			dataList.add(row) ;
		}
		return dataList;
		
	}
	

	private String exportExcel(List<String[]> dataList, String fileName, String sheetName)  {
		String[] cellTitle = dataList.get(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String now = dateFormat.format(new Date());
		// 导出文件路径
//		String basePath = "t:/";
		// 文件名
		String exportFileName = fileName +"_" + now + ".xlsx";

		// 需要导出的数据
		// 声明一个工作薄
		XSSFWorkbook workBook = null;
		workBook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, sheetName);
		// 创建表格标题行 第一行
		XSSFRow titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(30);
		CellStyle style = workBook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);  
		
		style.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		style.setBorderTop(CellStyle.BORDER_THIN);   
		style.setBorderLeft(CellStyle.BORDER_THIN);   
		style.setBorderRight(CellStyle.BORDER_THIN);  
		
		Font ztFont = workBook.createFont();  
		ztFont.setFontName("微软雅黑"); 
		ztFont.setFontHeightInPoints((short)9);
		ztFont.setBold(true);
		style.setFont(ztFont);                   
		
		CellStyle numberStyle = workBook.createCellStyle();
//		numberStyle.setDataFormat(XSSFDataFormat  .getBuiltinFormat("0.00"));
		numberStyle.setAlignment(HorizontalAlignment.RIGHT);
		numberStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		numberStyle.setBorderTop(CellStyle.BORDER_THIN);   
		numberStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		numberStyle.setBorderRight(CellStyle.BORDER_THIN);  
		
		CellStyle dataStyle = workBook.createCellStyle();
//		numberStyle.setDataFormat(XSSFDataFormat  .getBuiltinFormat("0.00"));
		dataStyle.setAlignment(HorizontalAlignment.LEFT);
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN); //    BORDER_THICK BORDER_DASHED BORDER_DOUBLE
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);   
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);   
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);  
		
		for (int i = 0; i < cellTitle.length; i++) {
			XSSFCell cell = titleRow.createCell(i);
			cell.setCellValue(cellTitle[i]);
			cell.setCellStyle(style);
		}
		
		
//		style.set
		// 插入需导出的数据
		for (int i = 1; i < dataList.size(); i++) {
			XSSFRow row = sheet.createRow(i);
//			row.setHeight(height);
//			row.setRowStyle(style);
			
			for (int j = 0; j < cellTitle.length; j++) {
//				row.createCell(j).setCellValue(dataList.get(i)[j]);
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(dataList.get(i)[j]);
				if(j>12 && j < 35 ) {
					cell.setCellStyle(numberStyle);
				}else {
					cell.setCellStyle(dataStyle);
				}
			}
		}
		
//		for (int i = 0; i < cellTitle.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
		
//		sheet.setColumnWidth(1, 7.88); 
		// 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 16);
		
		File file = new File(/*basePath + */ exportFileName);
		// 文件输出流
		try {
			FileOutputStream outStream = new FileOutputStream(file);
			workBook.write(outStream);
			workBook.close();
			outStream.flush();
			outStream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
//		System.out.println("导出成功！" /* + basePath */ + exportFileName);
		return exportFileName;
	}

}
