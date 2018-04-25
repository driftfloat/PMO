package com.pmo.dashboard.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pmo.dashboard.dao.CSDeptMapper;
import com.pmo.dashboard.dao.ChinaWorkHourMapper;
import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.dao.HKWorkHourMapper;
import com.pmo.dashboard.dao.MLWorkHourMapper;
import com.pmo.dashboard.dao.OfflineOperMapper;
import com.pmo.dashboard.dao.UserMapper;
import com.pmo.dashboard.dao.WorkHourMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.Employee;
import com.pmo.dashboard.entity.HKWorkHour;
import com.pmo.dashboard.entity.MLWorkHour;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.WorkHour;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.OfflineOperService;

@Service
public class OfflineOperServiceImpl implements OfflineOperService {
	@Resource
	private OfflineOperMapper offlineOperMapper;
	
	@Resource
    private CSDeptMapper csDeptMapper;
	
	@Resource
    private EmployeeMapper employeeMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	ChinaWorkHourMapper chinaWorkHourMapper;
	
	@Resource
	HKWorkHourMapper HKWorkHourMapper;
	
	@Resource
	MLWorkHourMapper MLWorkHourMapper;
	
	@Override
	public boolean delete(String id) {
		if(offlineOperMapper.deleteByPrimaryKey(id)>0){
            return true;
        }
		return false;
	}

	@Override
	public boolean insert(OfflineOper offlineOper) {
		if(offlineOperMapper.insertSelective(offlineOper)>0){
            return true;
        }
		return false;
	}

	@Override
	public OfflineOper selectById(String id) {
		return offlineOperMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(OfflineOper offlineOper) {
		if(offlineOperMapper.updateByPrimaryKeySelective(offlineOper)>0){
            return true;
        }
		return false;
	}

	@Override
	public List<OfflineOper> query(OfflineOper condition, User user,int pageSize,int pageNumber) {
		//1. count
//		int rmCount = offlineOperMapper.rmCount(condition);
//		if( 0 == rmCount) {
//		}
		List<OfflineOper> rtn = null ;
		Set<User> rmSet = new HashSet();
		if("5".equals(user.getUserType())) { // RM
			condition.setRmId(user.getUserId());
//			String[] rmIDs = {user.getUserId()};
//			condition.setRmIDs(rmIDs); 
			//第一个参数当前页码，第二个参数每页条数
			
//			int count = offlineOperMapper.rmCount(condition);
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = offlineOperMapper.queryByRM(condition) ;
//			if(0 == count) {
//				PageHelper.startPage(pageNumber,pageSize); 
//				rtn = offlineOperMapper.queryFromEmployeeByRM(condition);
//			}else {
//				PageHelper.startPage(pageNumber,pageSize); 
//				rtn = offlineOperMapper.queryByRM(condition) ;
//			}
		}else if("3".equals(user.getUserType())) {  // 交付部经理
			List<CSDept> csDepts = csDeptMapper.queryCSDeptByIds(user.getCsdeptId().split(","));  // 交付部经理所在的部门
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			//第一个参数当前页码，第二个参数每页条数
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = offlineOperMapper.queryBySubDept(condition) ;
		}else if("1".equals(user.getUserType())){ // 事业部经理
			List<CSDept> csDepts = csDeptMapper.queryCSSubDeptNameByCsBuName(user.getBu());  // csBuName 根据事业部名称查
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			//第一个参数当前页码，第二个参数每页条数
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = offlineOperMapper.queryByDept(condition) ;
		}
		for(OfflineOper offlineOper :rtn) {
			workHour(offlineOper);
		}
		return rtn ;
	}
	
	@Override
	public boolean save(OfflineOper offlineOper, User user) {
		if(!"5".equals(user.getUserType())) { // ! RM
			offlineOper.setOperatorId(user.getUserId());
		}
//		if(StringUtils.isBlank(offlineOper.getRmName())) { 
//			Map<String, Object> userMap = new HashMap<String, Object>();
//			userMap.put("userid", offlineOper.getRmId());
//			List<User> users = userMapper.getUser(userMap);
//			if(users.size()>0) {
//				offlineOper.setRmName(users.get(0).getNickname());  // userName 是 EHR，中文名是 NICKNAME
//			}
//		} 
		
		offlineOper = workHour(offlineOper);
		Employee employee = employeeMapper.queryEmployeeById(offlineOper.getEmployeeId()) ;
		
		int rtnCount = 0;
		int count = offlineOperMapper.employeeCount(offlineOper);
		if(1 == count) {
			if(null == offlineOper.getId()) {
				OfflineOper rtn = offlineOperMapper.queryByEmployeeID(offlineOper);
				if(rtn != null) {
					offlineOper.setId(rtn.getId());
				}
			}
			offlineOper.setCreateUpdate(new Date());
			calculte(offlineOper, employee);
			rtnCount = offlineOperMapper.updateByPrimaryKeySelective(offlineOper) ;
		}else {
			offlineOper.setCreateDate(new Date());
			offlineOper.setId(Utils.getUUID());
			calculte(offlineOper, employee);
			rtnCount = offlineOperMapper.insertSelective(offlineOper) ;
		}
		return rtnCount>0? true : false  ;
	}
	
	private boolean checkCalculte(OfflineOper offlineOper,Employee employee) {
		if(null == offlineOper.getChsoftiMskHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiAwHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiIwHours()) {
			return false;
		}
		
		if(null == offlineOper.getChsoftiOtHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiToHours()) {
			return false;
		}
		if(null == offlineOper.getChsoftiApwHours()) {
			return false;
		}
		
		if(null == offlineOper.getChsoftiInfTravel()) {
			return false;
		}
		if(null == offlineOper.getChsoftiInfEquipment()) {
			return false;
		}
		if(null == offlineOper.getChsoftiInfSub()) {
			return false;
		}
		
		String emploeeBill = employee.getBillRate() ;
		if(StringUtils.isBlank(emploeeBill)) {
			return false;
		}
		return true;
	}
	private void calculte(OfflineOper oper,Employee employee) {
		if(!checkCalculte(oper, employee)) {
			return ;
		}
		/**
		 *  CHSOFTI_IFAW	　		实际工时收入=				员工单价*中软实际工时
			CHSOFTI_INVALID	　		无效工时收入=				员工单价*中软无效工时
			CHSOFTI_INF_OT	　		加班费工时收入=			员工单价*中软加班费工时
			CHSOFTI_INF_PT	　		调休工时收入=				员工单价*中软调休工时
			CHSOFTI_INF_AD	　		调整上月工时收入=			员工单价*中软调整上月工时
			CHSOFTI_INF_TOTAL	　	月收入合计=				实际工时收入+无效工时收入+加班费工时收入+调休工时收入+调整上月工时收入+差旅收入+付费设备收入+分包收入
			CHSOFTI_INF_CURRENT	　	当月有效收入=				月收入合计-无效工时收入			
			CHSOFTI_EFFECTIVE_NR	有效NR=					当月有效收入/1.06
			CHSOFTI_EFFECTIVE_ST	当月有效人力=				中软实际工时/中软月标准工时
			CHSOFTI_INVALID_ST	　	当月无效人力=				中软无效工时/中软月标准工时
		 */
//		oper.set
		// 员工单价  BILL_RATE , BILL_CURRENCY 货币类型
		
		BigDecimal billRate = new BigDecimal(employee.getBillRate());	// 员工单价
		oper.setChsoftiIfaw( billRate.multiply( oper.getChsoftiAwHours())) ; 		//实际工时收入 
		oper.setChsoftiInvalid( billRate.multiply( oper.getChsoftiIwHours())) ; 	//无效工时收入
		oper.setChsoftiInfOt( billRate.multiply( oper.getChsoftiOtHours())) ; 	//加班费工时收入 
		oper.setChsoftiInfPt( billRate.multiply( oper.getChsoftiToHours())) ; 	//调休工时收入 
		oper.setChsoftiInfAd( billRate.multiply( oper.getChsoftiApwHours())) ; 	//调整上月工时收入
		
		oper.setChsoftiInfTotal(oper.getChsoftiIfaw().add(oper.getChsoftiInvalid()).add(oper.getChsoftiInfOt())
				.add(oper.getChsoftiInfPt()).add(oper.getChsoftiInfAd()).add(oper.getChsoftiInfTravel())
				.add(oper.getChsoftiInfEquipment()).add(oper.getChsoftiInfSub()));
		oper.setChsoftiInfCurrent(oper.getChsoftiInfTotal().subtract(oper.getChsoftiInvalid()));
		oper.setChsoftiEffectiveNr(oper.getChsoftiInfCurrent().divide(new BigDecimal("1.06"),2 , BigDecimal.ROUND_HALF_EVEN));
		oper.setChsoftiEffectiveSt(oper.getChsoftiAwHours().divide(oper.getChsoftiMskHours(),2 , BigDecimal.ROUND_HALF_EVEN));
		oper.setChsoftiInvalidSt(oper.getChsoftiIwHours().divide(oper.getChsoftiMskHours(),2, BigDecimal.ROUND_HALF_EVEN ));
		return ;
	}
	
	private OfflineOper workHour(OfflineOper offlineOper) {
//		标准工时
		String  location = employeeMapper.queryEmployeeById(offlineOper.getEmployeeId()).getStaffLocation() ;
		if(StringUtils.isNotBlank(location) ) {
			WorkHourMapper workHourMapper = chinaWorkHourMapper ;
			WorkHour workHour = new ChinaWorkHour() ;
			if("HK".equals(location)) {
				workHourMapper = HKWorkHourMapper ;
				workHour = new HKWorkHour() ;
			}else if("Malaysia".equals(location)) { 
				workHourMapper = MLWorkHourMapper ;	
				workHour = new MLWorkHour() ;
			}
			workHour.setYear(offlineOper.getYear());
			workHour.setMonth(offlineOper.getMonth()+"月");
			offlineOper.setChsoftiMskHours( workHourMapper.queryWorkHour(workHour)); 
		}
		return offlineOper;
	}

	@Override
	public void export(User user) {
		XSSFWorkbook workBook = new XSSFWorkbook();
	    XSSFSheet sheet = workBook.createSheet();
	    workBook.setSheetName(0,"过程数据");
	    XSSFRow titleRow = sheet.createRow(0);
	    
		if("5".equals(user.getUserType())) { // RM
			
		}else if("3".equals(user.getUserType())) {  // 交付部经理
			
		}else if("1".equals(user.getUserType())){ // 事业部经理
			
		}
			
	}

}
