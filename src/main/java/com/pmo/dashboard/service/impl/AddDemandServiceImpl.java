package com.pmo.dashboard.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.AddDemandMapper;
import com.pmo.dashboard.entity.AddDemand;
import com.pom.dashboard.service.AddDemandService;

@Service
public class AddDemandServiceImpl implements AddDemandService{

	@Resource
	AddDemandMapper addDemandMapper;
	
	@Override
	public boolean addDemand(AddDemand demand) {
		// TODO Auto-generated method stub
		if(addDemandMapper.addDemand(demand)>0){
			return true;
		}
		return false;
	}
}