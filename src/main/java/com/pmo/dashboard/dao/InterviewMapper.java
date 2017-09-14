package com.pmo.dashboard.dao;

import java.util.Map;

import com.pmo.dashboard.entity.CandidateInterview;

public interface InterviewMapper {

	public void addInterview(CandidateInterview candidateInterview);
	
	public CandidateInterview queryNewInterviewByCandidateId(Map<String, Object> params);
		
}
