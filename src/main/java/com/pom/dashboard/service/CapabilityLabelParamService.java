package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.CapabilityLabelParam;

public interface CapabilityLabelParamService {
	boolean delete(String id);  //deleteByPrimaryKey

//    int insert(CapabilityLabelParam record);

	boolean insert(CapabilityLabelParam record); //insertSelective

    CapabilityLabelParam select(String id);  // selectByPrimaryKey

    boolean update(CapabilityLabelParam record);  //updateByPrimaryKeySelective

//    int updateByPrimaryKey(CapabilityLabelParam record);
    List<CapabilityLabelParam> query(CapabilityLabelParam condition);
    
    List<CapabilityLabelParam> majorcateIds();
    
    String maxSubCate(String majorcateId);
    
    boolean save(CapabilityLabelParam capabilityLabelParam);
}
