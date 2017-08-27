package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.DemandMapper;
import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.entity.Demand;
import com.pmo.dashboard.entity.HSBCDept;
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
	public List<Demand> queryDemandList(Demand demand) {
		List<String> hsbcSubDeptIdList = hsbcDeptMapper.queryHSBCSubDeptId(demand);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hsbcSubDeptIdList", hsbcSubDeptIdList);
		params.put("demand", demand);
		List<Demand> list = demandMapper.queryDemandList(params);
		//List<Demand> newList = new ArrayList<Demand>();
		for (Demand demands : list) {
			//demands.setHsbcSubDeptId(hsbcSubDeptId);
			HSBCDept hsbcDept = hsbcDeptMapper.queryDemandHSBCSubDeptById(demands.getHsbcSubDeptId());
			demands.setHsbcDept(hsbcDept);
		}
		return list;
	}

}
