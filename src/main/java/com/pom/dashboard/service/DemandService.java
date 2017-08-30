package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.PageCondition;

/**
 * 需求service类
 * @author tianzhao
 */
public interface DemandService {

	public List<Demand> queryDemandList(Demand demand, PageCondition pageCondition,String csBuName);
}
