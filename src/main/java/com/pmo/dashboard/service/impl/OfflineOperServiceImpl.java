package com.pmo.dashboard.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pmo.dashboard.dao.CSDeptMapper;
import com.pmo.dashboard.dao.EmployeeMapper;
import com.pmo.dashboard.dao.OfflineOperMapper;
import com.pmo.dashboard.entity.CSDept;
import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.OfflineOperService;

@Service
public class OfflineOperServiceImpl implements OfflineOperService {
	@Resource
	private OfflineOperMapper OfflineOperMapper;
	
	@Resource
    private CSDeptMapper csDeptMapper;
	
	@Resource
    private EmployeeMapper employeeMapper;
	
	@Override
	public boolean delete(String id) {
		if(OfflineOperMapper.deleteByPrimaryKey(id)>0){
            return true;
        }
		return false;
	}

	@Override
	public boolean insert(OfflineOper offlineOper) {
		if(OfflineOperMapper.insertSelective(offlineOper)>0){
            return true;
        }
		return false;
	}

	@Override
	public OfflineOper selectById(String id) {
		return OfflineOperMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean update(OfflineOper offlineOper) {
		if(OfflineOperMapper.updateByPrimaryKeySelective(offlineOper)>0){
            return true;
        }
		return false;
	}

	@Override
	public List<OfflineOper> query(OfflineOper condition, User user,int pageSize,int pageNumber) {
		//1. count
//		int rmCount = OfflineOperMapper.rmCount(condition);
//		if( 0 == rmCount) {
//		}
		List<OfflineOper> rtn = null ;
		Set<User> rmSet = new HashSet();
		if("5".equals(user.getUserType())) { // RM
			condition.setRmId(user.getUserId());
//			String[] rmIDs = {user.getUserId()};
//			condition.setRmIDs(rmIDs); 
			//第一个参数当前页码，第二个参数每页条数
			
//			int count = OfflineOperMapper.rmCount(condition);
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = OfflineOperMapper.queryByRM(condition) ;
//			if(0 == count) {
//				PageHelper.startPage(pageNumber,pageSize); 
//				rtn = OfflineOperMapper.queryFromEmployeeByRM(condition);
//			}else {
//				PageHelper.startPage(pageNumber,pageSize); 
//				rtn = OfflineOperMapper.queryByRM(condition) ;
//			}
		}else if("3".equals(user.getUserType())) {  // 交付部经理
			List<CSDept> csDepts = csDeptMapper.queryCSDeptByIds(user.getCsdeptId().split(","));  // 交付部经理所在的部门
//			for(CSDept d :csDepts ) {
//				 List<User> rms = OfflineOperMapper.queryRMFromDept(d.getCsSubDeptId()) ; csDepts.get(0).getCsSubDeptId() ;  // subDeptId
//				 rmSet.addAll(rms) ;
//			}
//			String[] rmIDs = new String[rmSet.size()] ;
//			for(User u : rmSet) {
//				rmIDs[index] = u.getUserId() ;
//				index++ ;
//			}
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			//第一个参数当前页码，第二个参数每页条数
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = OfflineOperMapper.queryBySubDept(condition) ;
		}else if("1".equals(user.getUserType())){ // 事业部经理
			List<CSDept> csDepts = csDeptMapper.queryCSSubDeptNameByCsBuName(user.getBu());  // csBuName 根据事业部名称查
//			for(CSDept d :csDepts ) {
//				 List<User> rms = OfflineOperMapper.queryRMFromDept(d.getCsSubDeptId()) ; csDepts.get(0).getCsSubDeptId() ;  // subDeptId
//				 rmSet.addAll(rms) ;
//			}
//			String[] rmIDs = new String[rmSet.size()] ;
//			int index = 0;
//			for(User u : rmSet) {
//				rmIDs[index] = u.getUserId() ;
//				index++ ;
//			}
//			condition.setRmIDs(rmIDs); 
			
			int index = 0;
			String[] ids = new String[csDepts.size()] ;
			for(CSDept d :csDepts ) {
				ids[index] = d.getCsSubDeptId() ;
				index++ ;
			}
			condition.setIds(ids);
			//第一个参数当前页码，第二个参数每页条数
			PageHelper.startPage(pageNumber,pageSize); 
			rtn = OfflineOperMapper.queryByDept(condition) ;
		}
		return rtn ;
	}

}
