package com.pmo.dashboard.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.DemandDraftMapper;
import com.pmo.dashboard.entity.DemandDraft;
import com.pom.dashboard.service.DemandDraftService;

@Service
public class DemandDraftServiceImpl implements DemandDraftService{
	
	@Resource
	private DemandDraftMapper demandDraftMapper;

	@Override
	public boolean add(DemandDraft record) {
		int i = demandDraftMapper.add(record);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean update(DemandDraft record) {
		int i = demandDraftMapper.update(record);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delete(Map<String,String> param) {
		int i = demandDraftMapper.delete(param);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public DemandDraft getByID(Map<String,String> param) {
		return demandDraftMapper.getByID(param);
	}

}
