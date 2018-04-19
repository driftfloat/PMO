package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;

public interface OfflineOperMapper {
	int deleteByPrimaryKey(String id);
	
//  int insert(OfflineOper record);

  int insertSelective(OfflineOper record);

  OfflineOper selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(OfflineOper record);

//  int updateByPrimaryKeyWithBLOBs(OfflineOper record);
//
//  int updateByPrimaryKey(OfflineOper record);
  
  List<OfflineOper> queryByRM(OfflineOper condition);
  
  /**
   * RM count
   */
  int rmCount(OfflineOper condition);
  
  List<User> queryRMFromDept(String subDeptId) ;
}