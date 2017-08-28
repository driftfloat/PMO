package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.DemandMapper;
import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.HSBCDept;
import com.pmo.dashboard.entity.PageCondition;
import com.pom.dashboard.service.DemandService;

/**
 * 需求service实现类
 * @author tianzhao
 *
 */

@Service
public class DemandServiceImpl implements DemandService{

	@Resource
	DemandMapper demandMapper;
	
	@Resource
	HSBCDeptMapper hsbcDeptMapper;
	
	@Override
	public List<Demand> queryDemandList(Demand demand,PageCondition pageCondition) {
		List<String> hsbcSubDeptIdList = hsbcDeptMapper.queryHSBCSubDeptId(demand);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hsbcSubDeptIdList", hsbcSubDeptIdList);
		params.put("demand", demand);
		params.put("pageCondition", pageCondition);
		int num = (pageCondition.getCurrPage() - 1)*pageCondition.getPageSize();
		params.put("num", num);
		List<Demand> list = demandMapper.queryDemandList(params);
		//设置总页数
		int queryDemandCount = demandMapper.queryDemandCount(params);
		//if()
		pageCondition.setTotalPage((int) Math.ceil(queryDemandCount*1.0 / pageCondition.getPageSize()));
		for (Demand demands : list) {
			HSBCDept hsbcDept = hsbcDeptMapper.queryDemandHSBCSubDeptById(demands.getHsbcSubDeptId());
			demands.setHsbcDept(hsbcDept);
		}
		return list;
	}

}
