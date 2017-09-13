package com.pom.dashboard.service;

import java.util.List;
import java.util.Map;

import com.pmo.dashboard.entity.CandidateInfo;

public interface InterviewService {

	int getCandidateListCount();

	List<CandidateInfo> getCandidateList(CandidateInfo candidate);

	Map<String, List<CandidateInfo>> getInterviewRecordByCandId(String candidateId);

}
