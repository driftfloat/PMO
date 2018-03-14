package com.pmo.dashboard.dao;

import java.util.Map;

import com.pmo.dashboard.entity.DemandDraft;

public interface DemandDraftMapper {
    int delete(Map<String,String> param);

    int add(DemandDraft record);

    DemandDraft getByID(Map<String,String> param);

    int update(DemandDraft record);
}