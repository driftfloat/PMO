package com.pmo.dashboard.dao;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.CandidatePush;

/**
 * RM候选人数据层接口
 * @author blkmk7
 *
 */
public interface RmCandidateMapper {

	public List<CandidatePush> queryPushedCandidate(Map<String, Object> params);
	public int queryCandidateCount(Map<String, Object> params);
	public void updateCandidateStatus(Map<String, Object> params);
	//public void updateInterviewId(Map<String, Object> params1);
}
