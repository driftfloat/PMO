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

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.pmo.dashboard.dao.CurrencysMapper;
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
	private static final int PAGESIZE = 2 << 16 ;
	
	@Resource
	private OfflineOperService offlineOperService;
	
	@Resource
	private CSDeptService csDeptService;
	
	@Resource
    private EmployeeService employeeService;
	
	@Resource
	private UserService userService;
	
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
		
		return (null == b)? "0.00" : b.toPlainString() ;
	}
	
	public List<String[]> exportData() {
		List<String[]> dataList = new ArrayList<String[]>();
		String[] cellTitle = { "月份", "业务线", "事业部", "执行交付部", "项目类型", "员工E-HR编码", "员工姓名", "项目编号", "项目名称", "工作地" 
				,"技能", "级别", "币种", "当月汇率", "员工单价（h）-原币种", "月标准工时", "实际工时", "无效工时", "加班费工时", "调休工时"
				, "调整上月工时", "实际工时收入-收入1", "无效工时收入", "加班费工时收入-收入2", "调休工时收入-收入3", "调整上月工时收入-收入4" 
				,"差旅收入-收入5", "付费设备收入-收入6", "分包收入-收入7", "当月收入合计-原币种", "当月收入合计-RMB", "当月有效收入", "有效NR-与月滚一致"
				, "当月有效人力", "当月无效人力", "RM", "备注"  };
		dataList.add(cellTitle);
		String[] row = new String[cellTitle.length];
		
		OfflineOper condition = new OfflineOper();
		condition.seteHr("E000834441");
//		condition.setStaffName("白世铭");
		User user = new User();
		
		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培  12
////		user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰 9
//		user.setUserId("20f1aeff297d49d4b3c42877687a7076");  // 张盛  9,12
		user.setUserType("5");
		
		user = userService .queryUserById("cb00bad3f16a4e8baf450e7b88af7c4b");
		user.setCsDept(csDeptService.queryCSDeptById(user.getCsdeptId()));
		
		List<OfflineOper> list = offlineOperService.query(condition, user, PAGESIZE, 1) ; //PAGESIZE
		
		for(OfflineOper o:list) {
			Employee e = employeeService.queryEmployeeById(o.getEmployeeId())  ;
			Currencys currencyCondition = new Currencys();
			currencyCondition.setYear(o.getYear());
			currencyCondition.setMonth(o.getMonth());
			e.getStaffLocation();
			String staffLocation = e.getStaffLocation()  ;
			if("China".equals(staffLocation)  ) {
				staffLocation = "北京";
			}else if("HK".equals(staffLocation)){
				staffLocation = "HK";
			}else if("Malaysia".equals(staffLocation)){
				staffLocation = "马来";
			}
			currencyCondition.setPlaceWork(staffLocation);
			Currencys currencys = currencysMapper.queryCurrency(currencyCondition);
			
			row[0] = o.getMonth()+"月";
			row[1] = "汇丰业务线";
			row[2] = user.getBu();
			row[3] = user.getCsDept().getCsBuName();
			row[4] = e.getEngagementType() ; 
			
			row[5] = o.geteHr();
			row[6] = o.getStaffName();
			row[7] = e.getChsoftiProNumber();
			row[8] = e.getChsoftiProName();
			row[9] = e.getStaffLocation();
			
			row[10] = e.getSkill();
			row[11] = e.getRole();
			row[12] = e.getBillingCurrency();
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
			row[35] = o.getRmName();
			row[36] = o.getRemark();
			
			dataList.add(row) ;
		}
		return dataList;
		
	}
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/spring-mybatis.xml");
		ExportExcel exportExcel = (ExportExcel)context.getBean(ExportExcel.class);
		String fileName = exportExcel.getExportFile() ;
//		System.out.println(fileName);
		
		List<String[]> dataList = exportExcel.exportData(); 
		String sheetName = "过程数据" ;
		exportExcel.export(dataList, fileName, sheetName);
	}

	public void export(List<String[]> dataList, String fileName, String sheetName)  {
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
		CellStyle style = workBook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.YELLOW.index) ;
		
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
			
//			row.createCell(0).setCellValue(i);
//			row.createCell(0).setCellValue(dataList.get(i)[0]);
//			row.createCell(1).setCellValue(dataList.get(i)[1]);
//			row.createCell(2).setCellValue(dataList.get(i)[2]);
//			row.createCell(3).setCellValue(dataList.get(i)[3]);
			
			for (int j = 0; j < cellTitle.length; j++) {
				row.createCell(j).setCellValue(dataList.get(i)[j]);
			}
		}
		
//		for (int i = 0; i < cellTitle.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
		
//		sheet.setColumnWidth(1, 7.88); 
		// 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
		
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
		System.out.println("导出成功！" /* + basePath */ + exportFileName);
	}

}
