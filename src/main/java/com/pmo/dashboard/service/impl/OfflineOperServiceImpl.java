package com.pmo.dashboard.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
			rtnCount = offlineOperMapper.updateByPrimaryKeySelective(offlineOper) ;
		}else {
			offlineOper.setCreateDate(new Date());
			offlineOper.setId(Utils.getUUID());
			rtnCount = offlineOperMapper.insertSelective(offlineOper) ;
		}
		return rtnCount>0? true : false  ;
	}
	
	private OfflineOper calculte(OfflineOper offlineOper) {
		
		return null;
	}

}
