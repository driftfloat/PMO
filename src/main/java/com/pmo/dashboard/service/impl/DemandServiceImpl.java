package com.pmo.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CSDeptMapper;
import com.pmo.dashboard.dao.DemandMapper;
import com.pmo.dashboard.dao.HSBCDeptMapper;
import com.pmo.dashboard.entity.CSDept;
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
	
	@Resource
	CSDeptMapper csDeptMapper;
	
	@Override
	public List<Demand> queryDemandList(Demand demand,PageCondition pageCondition,String csBuName) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(demand.getHsbcDept().getHsbcDeptName()) || StringUtils.isNotBlank(demand.getHsbcDept().getHsbcSubDeptName())){
			//查询满足条件的所有部门id
			List<String> hsbcSubDeptIdList = hsbcDeptMapper.queryHSBCSubDeptId(demand);
			params.put("hsbcSubDeptIdList", hsbcSubDeptIdList);
		}
		if(StringUtils.isNotBlank(csBuName)){
			//查询满足事业部的所有交付部
			List<CSDept> csSubDeptList = csDeptMapper.queryCSSubDeptNameByCsBuName(csBuName);
			params.put("csSubDeptList", csSubDeptList);
		}
		params.put("csBuName", csBuName);
		params.put("demand", demand);
		params.put("pageCondition", pageCondition);
		//计算查询起始行号
		int num = (pageCondition.getCurrPage() - 1)*pageCondition.getPageSize();
		params.put("num", num);
		//查询所有满足条件的需求信息
		List<Demand> list = demandMapper.queryDemandList(params);
		//设置总页数
		int queryDemandCount = demandMapper.queryDemandCount(params);
		pageCondition.setTotalPage((int) Math.ceil(queryDemandCount*1.0 / pageCondition.getPageSize()));
		//将部门信息设置到需求信息中
		for (Demand demands : list) {
			HSBCDept hsbcDept = hsbcDeptMapper.queryDemandHSBCSubDeptById(demands.getHsbcSubDeptId());
			demands.setHsbcDept(hsbcDept);
		}
		return list;
	}

}
