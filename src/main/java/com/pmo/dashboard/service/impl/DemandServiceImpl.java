package com.pmo.dashboard.service.impl;

import java.util.List;

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
	public List<Demand> queryDemandList() {
		List<Demand> list = demandMapper.queryDemandList();
		for (Demand demand : list) {
			HSBCDept hsbcDept = hsbcDeptMapper.queryDemandHSBCSubDeptById(demand.getHsbcSubDeptId());
			demand.setHsbcDept(hsbcDept);
		}
		return list;
	}

}
