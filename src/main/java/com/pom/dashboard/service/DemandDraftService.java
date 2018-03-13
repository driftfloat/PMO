package com.pom.dashboard.service;

import com.pmo.dashboard.entity.DemandDraft;

/**
 * 需求草稿
 * @author Devin
 * @since 2018-3-13
 *
 */
public interface DemandDraftService {
	
	boolean add(DemandDraft record);
	
	boolean update(DemandDraft record);
	
	boolean delete(String id);
	
	DemandDraft getByID(String id);

}
