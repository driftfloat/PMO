package com.pmo.dashboard.dao;

import com.pmo.dashboard.entity.DemandDraft;

public interface DemandDraftMapper {
    int delete(String demandId);

    int add(DemandDraft record);

    DemandDraft getByID(String demandId);

    int update(DemandDraft record);
}