package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pmo.dashboard.entity.CandidatePush;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.PageCondition;

/**
 * 需求service类
 * @author tianzhao
 */
public interface DemandService {

	public List<Demand> queryDemandList(Demand demand, PageCondition pageCondition,String csBuName,HttpServletRequest request);
	public List<Demand> queryAllDemand(Map<String, Object> params);
	public List<Demand> queryOfferDemandList(CandidatePush candidatePush);
	public void updateCandidateIdById(CandidatePush candidateId, String demandId, String pushId);
	//gkf add
	public boolean updateDemandOnBoardById(Demand demand);
	public Demand queryDemandById(String demandId);
}
