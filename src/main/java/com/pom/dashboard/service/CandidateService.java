package com.pom.dashboard.service;

import java.util.LinkedHashMap;
import java.util.List;
import com.pmo.dashboard.entity.CandidateInfo;

public interface CandidateService
{
    List<CandidateInfo> queryCandidateList(CandidateInfo candidate);

	int queryCandidateCount(CandidateInfo candidate);
	
    List<LinkedHashMap<String, String>> queryExportData(CandidateInfo candidate);
}
