package com.pom.dashboard.service;

import java.util.List;
import com.pmo.dashboard.entity.CandidateInfo;

public interface CandidateService
{
    List<CandidateInfo> queryCandidateList(CandidateInfo candidate);

	int queryCandidateCount(CandidateInfo candidate);
}
