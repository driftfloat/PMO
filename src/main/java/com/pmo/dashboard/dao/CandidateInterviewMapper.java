package com.pmo.dashboard.dao;

import com.pmo.dashboard.entity.CandidateInterview;

public interface CandidateInterviewMapper {
	CandidateInterview queryCandidateInterviewById(String interviewId);
	
	boolean updateCandidateInterviewMark(CandidateInterview candidateInterview);
	
	boolean updateConfirmStatus(CandidateInterview candidateInterview);

}
