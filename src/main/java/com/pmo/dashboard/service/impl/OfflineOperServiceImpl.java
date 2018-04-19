package com.pmo.dashboard.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	public List<OfflineOper> query(OfflineOper condition, User user) {
		//1. count
//		int rmCount = OfflineOperMapper.rmCount(condition);
//		if( 0 == rmCount) {
//		}
		Set<User> rmSet = new HashSet();
		if("5".equals(user.getUserType())) { // RM
			condition.setRmId(user.getUserId());
			return OfflineOperMapper.queryByRM(condition) ;
		}else if("3".equals(user.getUserType())) {  // 交付部经理
			List<CSDept> csDepts = csDeptMapper.queryCSDeptByIds(user.getCsdeptId().split(","));  // 交付部经理所在的部门
			for(CSDept d :csDepts ) {
				 List<User> rms = OfflineOperMapper.queryRMFromDept(d.getCsSubDeptId()) ; csDepts.get(0).getCsSubDeptId() ;  // subDeptId
				 rmSet.addAll(rms) ;
			}
			int a = 1;
		}else if("1".equals(user.getUserType())){ // 事业部经理
			List<CSDept> list = csDeptMapper.queryCSSubDeptNameByCsBuName("csBuName");  // csBuName 根据事业部名称查
		}
		return null ;
	}

}
