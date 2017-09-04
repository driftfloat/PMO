package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.Demand;

public interface DemandMapper
{
	public List<Demand> queryDemandList(Map<String, Object> params);
	public int queryDemandCount(Map<String, Object> params);
	public List<Demand> queryAllDemand(Map<String, Object> params);
}
