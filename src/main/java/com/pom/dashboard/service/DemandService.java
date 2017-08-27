package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.Demand;

/**
 * 需求service类
 * @author tianzhao
 */
public interface DemandService {

	public List<Demand> queryDemandList(Demand demand);
}
