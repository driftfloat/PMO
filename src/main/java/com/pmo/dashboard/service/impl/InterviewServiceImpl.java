package com.pmo.dashboard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.CandidateMapper;
import com.pmo.dashboard.entity.CandidateInfo;
import com.pom.dashboard.service.InterviewService;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Resource
	private CandidateMapper candidateMapper;

	@Override
	public Map<String, List<CandidateInfo>> getInterviewRecordByCandId(String candidateId) {
		List<CandidateInfo> list = candidateMapper.getInterviewRecordByCandId(candidateId);
		Iterator<CandidateInfo> it = list.iterator();
		CandidateInfo candidate = null;
		// Map<String, Object> map1 = new HashMap<>();
		// map1.put("", value)
		Map<String, List<CandidateInfo>> map = new LinkedHashMap<String, List<CandidateInfo>>(10);
		List<CandidateInfo> listInfo = null;
		while (it.hasNext()) {
			candidate = it.next();
			String fatherInterviewId = candidate.getFatherInterviewId();
			if (map.containsKey(candidate.getFatherInterviewId())) {
				listInfo = map.get(fatherInterviewId);
			} else {
				listInfo = new LinkedList<CandidateInfo>();
				map.put(fatherInterviewId, listInfo);
			}
			if ("0".equals(candidate.getInterviewStatus())) {
				candidate.setInterviewStatus("通过");
			} else {
				candidate.setInterviewStatus("未通过");
			}
			listInfo.add(candidate);
		}
		return map;
	}

	@Override
	public List<CandidateInfo> getCandidateList(CandidateInfo candidate) {
		return candidateMapper.getCandidateList(candidate);
	}

	@Override
	public int getCandidateListCount() {
		return candidateMapper.getCandidateListCount();
	}

}
